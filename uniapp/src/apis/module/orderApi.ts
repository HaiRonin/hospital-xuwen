// 1.4缴费列表


// 1.4.1待交费列表
export const queryToPayRecipeInfoList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryToPayRecipeInfoList';
    return ajax.apiPost(url, params, options);
};

// 1.4.2已交费列表
export const queryPaymentRecordList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryPaymentRecordList';
    return ajax.apiPost(url, params, options);
};

// 1.4.3状态查询
export const queryPaymentRecordStatus: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryPaymentRecordStatus';
    return ajax.apiPost(url, params, options);
};

// 1.4.4更新打票状态
export const updateRecipeStatusStatus: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/updateRecipeStatusStatus';
    return ajax.apiPost(url, params, options);
};

// 1.4.5支付
export const doPay: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/doPay';
    return ajax.apiPost(url, params, options);
};

// 3.10查询充值记录
export const rechargerecord: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/rechargerecord';
    return ajax.apiPost(url, params, options);
};

// 3.18门诊清单
export const printlist: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/printlist';
    return ajax.apiPost(url, params, options);
};

