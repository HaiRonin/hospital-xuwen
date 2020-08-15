// 第二步
import CacheAjax from './cacheAjax';
import domain from './domain';
import get from './axiosPack';
// import {Toast} from 'vant';

const cacheData: IOBJ = {};
const ajaxErr = [
    {key: 'timeout of', text: '请求超时，请稍后...'},
    {key: '404', text: '请求地址有误'},
];

// 特殊处理
const specialHandl = (url: string, params: IOBJ) => {
    let newParams = params;
    if (url === '/his/request') {
        const {api, ...o} = params;
        newParams = {
            api,
            dataParam: {
                ...o,
                synUserName: '',
                synKey: ''
            }
        };
    }
    return newParams;
};

const handleError = (err: IMyRejectObj) => {
    // console.log(err);
    const {type, data} = err;
    let errorText = '服务器异常';
    let item = null;
    // console.log(data.msg);
    switch (true) {
        case type === 'catchError':
            item = ajaxErr.find((ii) => ~data.message.indexOf(ii.key));
            item && (errorText = item.text);
            break;
        case type === 'thenError' && !!data.resultMsg:
            errorText = data.resultMsg;
            break;
    }

    return errorText;
};

const ajax1 = (url: string, params: IOBJ, options: IMyOptions) => {
    const {isLoad, closeErrorTips} = options;

    let toast: IOBJ | null = null;
    if (isLoad) {
        toast = utils.showLoad();
    }

    return get(url, params, options).finally(() => {
        if (toast) toast.clear();
    }).then((res) => {
        return res;
    }).catch((err) => {
        console.error(err);
        if (!closeErrorTips) {
            // 提示
            utils.toast(handleError(err));
        }
        return Promise.reject(err);
    });
};

// 加上缓存功能
export default function ajax2 (url: string, params: IOBJ, options: IMyOptions) {
    const {targetDomain} = options;
    const baseUrl = domain[targetDomain!];

    params = specialHandl(url, params);
    url = baseUrl + url;

    const cacheKey = url;
    // const cacheKey = `${url}${JSON.stringify(params)}`;
    let cacheObj = cacheData[cacheKey];
    if (!cacheObj) {
        cacheObj = new CacheAjax((params, options) => {
            delete params._URL;
            return ajax1(url, params, options);
        });
    }

    params._URL = url;
    cacheData[cacheKey] = cacheObj;
    return cacheObj.run(params, options);
}
