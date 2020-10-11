

// 获取app最新版本号 type = 1患者, 2是医生
export const getLastDoctorAppVersion: TApi = (params = {}, options = {}) => {
    const url = '/his/app/version/2';
    return ajax.apiGet(url, params, options);
};

// 5.22查询挂号患者
export const getDocFInfo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/GetDocFInfo';
    return ajax.apiPost(url, params, options);
};

// 5.23统计分析 (貌似是转诊接口)
export const getDocAnalysis: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/GetDocAnalysis';
    return ajax.apiPost(url, params, options);
};

// 5.24统计分析2
export const getDocWork: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/GetDocWork';
    return ajax.apiPost(url, params, options);
};

// 5.25查看患者门诊病历
export const getOuClinicFirst: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/GetOuClinicFirst';
    return ajax.apiPost(url, params, options);
};

// 5.26门诊医生转诊
export const toDocReferral: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/ToDocReferral';
    return ajax.apiPost(url, params, options);
};

// 5.27门诊医生转诊记录查询
export const getDocReferralList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/GetDocReferralList';
    return ajax.apiPost(url, params, options);
};

// 5.21注册医生用户（同时也是登录接口）
export const registerDocotr: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/RegisterDocotr';
    return ajax.apiPost(url, params, options);
};

// 3.1.9获取检查、检验数据（微信公众号，app）
export const uspGetPacsApp: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/uspGetPacsApp';
    return ajax.apiPost(url, params, options);
};


