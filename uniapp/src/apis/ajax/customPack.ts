// 第二步
import CacheAjax from './cacheAjax';
import domain from './domain';
import get from './axiosPack';
// import {Toast} from 'vant';

// 估计这里同步加载不到 store，所以弄成异步加载，看vue.config.js
let store: IOBJ | null = null;

const cacheData: IOBJ = {};
const ajaxErr = [
    {key: 'fail timeout', text: '请求超时，请稍后...'},
    {key: '404', text: '请求地址有误'},
    {key: 'request:fail', text: '请求发送失败'},
];

// 特殊处理
const specialHandl = (url: string, params: IOBJ, options: IMyOptions) => {
    let newParams = params;
    if (url === '/his/request') {
        const {api, ...o} = params;
        let {synUserName = '', synKey = '', UserName = '', id = ''}: IOBJ = {};
        options.noCloseBeforeAjax = true;

        // debugger;
        if (store && store.getters.isLogin) {
            const user = store.getters.userInfo;

            synUserName = user.userName || '';
            synKey = user.synKey || '';
            UserName = user.userName || '';
            id = user.id || '';
        }

        newParams = {
            // api: api.substr(1),
            api,
            dataParam: JSON.stringify({
                synUserName,
                synKey,
                UserName,
                id,
                ...o,
            })
        };
    }
    return newParams;
};

const handleError = (err: IMyRejectObj) => {
    // console.log(err);
    // debugger;
    const {type, data} = err;
    let errorText = '服务器异常';
    let item = null;
    // console.log(data.msg);
    switch (true) {
        case type === 'catchError':
            item = ajaxErr.find((ii) => ~(data.message || data.errMsg || '').indexOf(ii.key));
            item && (errorText = item.text);
            break;
        case type === 'thenError' && (!!data.resultMsg || !!data.msg):
            errorText = data.resultMsg || data.msg;
            break;
    }

    return errorText;
};

const ajax1 = (url: string, params: IOBJ, options: IMyOptions) => {
    const {isLoad, closeErrorTips} = options;

    if (isLoad) {
        utils.showLoad();
    }

    return get(url, params, options).finally(() => {
        if (isLoad) utils.hideLoad();
    }).then((res) => {
        return res;
    }).catch((err) => {
        console.error(err);
        // console.error(err.type !== 'catchError');
        // debugger;
        if (!closeErrorTips || err.type === 'catchError') {
            // 提示
            utils.toast(handleError(err));
        }
        return Promise.reject(err);
    });
};

// 加上缓存功能，还有特殊处理，还有异步加载store
export default async function ajax2 (url: string, params: IOBJ, options: IMyOptions) {
    const {targetDomain} = options;
    const baseUrl = domain[targetDomain!];

    if (!store) {
        store = await import('@/store');
        store = store.default;
        // console.log(store);
    }

    params = specialHandl(url, params, options);
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
