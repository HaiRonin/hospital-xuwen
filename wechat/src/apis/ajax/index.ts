import ajax from './customPack';
import importDomain from './domain';


export const apiGet: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'get';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

export const apiPost: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'post';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

export const apiPut: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'put';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

export const apiDel: TMyAjax = (url, params, options) => {
    params = params || {};
    options = options || {};
    options.method = 'delete';
    options.targetDomain = 'baseApi';
    return ajax(url, params, options);
};

export const domain = importDomain;
