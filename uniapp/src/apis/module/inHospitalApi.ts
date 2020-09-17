// 1.5住院信息


// 1.5.1查询患者住院信息
export const queryPatientInHosInfo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryPatientInHosInfo';
    return ajax.apiPost(url, params, options);
};

// 1.5.2自助入院申请查询
export const queryToInHos: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryToInHos';
    return ajax.apiPost(url, params, options);
};

// 1.5.3自助入院申请办理
export const applyToInHos: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/applyToInHos';
    return ajax.apiPost(url, params, options);
};

// 1.5.4住院押金补缴查询
export const queryArrears: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryArrears';
    return ajax.apiPost(url, params, options);
};

// 1.5.5住院押金补缴
export const inpatientPayment: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/inpatientPayment';
    return ajax.apiPost(url, params, options);
};

// 1.5.6押金明细查询
export const payDetail: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/payDetail';
    return ajax.apiPost(url, params, options);
};

// 1.5.7住院清单明细
export const inHosDetail: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/inHosDetail';
    return ajax.apiPost(url, params, options);
};

// 1.5.8住院医保信息查询
export const inHosMedicalInfo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/inHosMedicalInfo';
    return ajax.apiPost(url, params, options);
};

// 1.5.9出院结算申请
export const applyLeaveHos: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/applyLeaveHos';
    return ajax.apiPost(url, params, options);
};

// 1.5.10查询出院清单
export const queryLeaveHosDetail: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryLeaveHosDetail';
    return ajax.apiPost(url, params, options);
};

// 1.5.11出院结算
export const leaveHosPay: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/leaveHosPay';
    return ajax.apiPost(url, params, options);
};

