import axios, {Canceler, AxiosResponse} from 'axios';
import qs from 'qs';


// 执行中的ajax
const executionAjax: {[key: string]: Canceler | null} = {};

/**
 * 通过请求接口参数 获取对应的请求头 以及请求参数处理函数
 * @param action 一个标志
 */
const action = (action?: string) => {
    let contentType = null;
    let handleRequest = null;
    switch (action) {
        case 'json':
            contentType = 'application/json;charset=UTF-8';
            handleRequest = (data: IOBJ) => {
                return JSON.stringify(data);
            };
            break;
        case 'formData':
            contentType = 'multipart/form-data';
            handleRequest = (data: IOBJ) => {
                return data;
            };
            break;
        default:
            contentType = 'application/x-www-form-urlencoded;charset=UTF-8';
            handleRequest = (data: IOBJ) => {
                return qs.stringify(data);
            };
            break;
    }

    return {contentType, handleRequest};
};

/**
 * 初次封装，只处理请求头和参数回调，以及处理是否打断上一次请求
 * @param url 路径
 * @param params 请求参数
 * @param options 其它参数
 */
const get: TMyGet = (url, params, options) => {
    const {method, noCloseBeforeAjax = false} = options;
    const {contentType, handleRequest} = action(options.action);

    return new Promise((resolve, reject) => {
        if (!noCloseBeforeAjax && typeof executionAjax[url] === 'function') {
            executionAjax[url]!();
        }
        executionAjax[url] = null;

        axios({
            headers: {
                'Content-Type': contentType,
            },
            method,
            url,
            params: options.params || (method === 'get' ? params : ''),
            data: params || {},
            timeout: 15000,
            transformRequest: [(data) => {
                return handleRequest(data);
            }],
            transformResponse: [(res) => {
                executionAjax[url] = null;
                try {
                    return (typeof res === 'string' ? JSON.parse(res) : res);
                } catch (error) {
                    return res;
                }
            }],
            cancelToken: new axios.CancelToken((c) => {
                executionAjax[url] = c;
            })

        }).finally(() => {
            executionAjax[url] = null;
        }).then((res) => {
            const data: IMyResponse = res.data;

            if (data.code === 0) {
                resolve(data);
            } else {
                reject({type: 'thenError', data, oldRes: res} as IMyRejectObj);
            }
        }).catch((error) => {
            if (!axios.isCancel(error)) {
                reject({type: 'catchError', data: error} as IMyRejectObj);
            }
        });
    });
};

export default get;
