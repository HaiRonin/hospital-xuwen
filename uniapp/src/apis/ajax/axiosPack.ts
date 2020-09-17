// 第一步
// import axios, {Canceler, AxiosResponse} from 'axios';
// import qs from 'qs';

/**
 * 有个想法，页面中请求的借口，随页面销毁，一起中断请求
 * 大概思路
 * 收集页面请求的接口，以键值对的方式存储(key 为页面路径，value 为页面上所有触发请求的接口) 'pages/info/index': []
 * 可能需要 executionAjax 暴露这个出去，主要为了和上面进行匹配，判断哪些接口是请求完成的，以请求路径做对比
 * 最后通过 Vue.mixin 混入 beforeDestroy，再这进行中断处理
 */

// 执行中的ajax
const executionAjax: {[key: string]: null | UniApp.RequestTask} = {};

/**
 * 通过请求接口参数 获取对应的请求头 以及请求参数处理函数
 * @param action 一个标志
 */
const action = (action?: string) => {
    let contentType = null;
    let handleRequest = null;
    switch (action) {
        case 'formData':
            contentType = 'application/x-www-form-urlencoded;charset=UTF-8';
            handleRequest = (data: IOBJ) => {
                return data;
            };
            break;
        // case 'formData':
        //     contentType = 'multipart/form-data';
        //     handleRequest = (data: IOBJ) => {
        //         return data;
        //     };
        //     break;
        default:
            contentType = 'application/json;charset=UTF-8';
            handleRequest = (data: IOBJ) => {
                return JSON.stringify(data);
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
        // debugger
        if (!noCloseBeforeAjax && !!executionAjax[url]) {
            executionAjax[url]!.abort();
        }
        executionAjax[url] = null;

        executionAjax[url] = uni.request({
            url,
            method,
            data: params || {},
            timeout: 15000,
            header: {
                'Content-Type': contentType,
            },
            success (res) {
                const data = res.data as IMyResponse;

                // data.resultMsg === '00' 部分接口有问题，出现这种情况
                if (data.resultCode === '00' || data.resultMsg === '00') {
                    // 这里主要把处理成统一的输出方式
                    const dataKey = Object.keys(data).find((key: string) => !['resultCode', 'resultMsg', 'countnum'].includes(key));
                    data.data = data[dataKey || ''];
                    resolve(data);
                } else if (data.code === 0) {
                    resolve(data);
                } else {
                    reject({type: 'thenError', data, oldRes: res} as IMyRejectObj);
                }
            },
            fail (error) {
                // debugger;
                if (error && error.errMsg && !!~error.errMsg.indexOf('abort')) {
                    return;
                }
                reject({type: 'catchError', data: error} as IMyRejectObj);
            },
        });
    });
};

export default get;
