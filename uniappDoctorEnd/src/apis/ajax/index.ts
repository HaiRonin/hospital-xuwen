/**
 * vue.config.js 全局引入
 */

import ajax from './customPack';
// import importDomain from './domain';


export const apiGet: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'GET';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

export const apiPost: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'POST';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

export const apiPut: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'PUT';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

export const apiDel: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'DELETE';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

// export const domain = importDomain;
