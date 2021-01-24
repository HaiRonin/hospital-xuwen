// 1.2公共信息

// 预约状态查询
export const queryPeregistrationStatus: TApi = (params = {}, options = {}) => {
    const idCard = params.idCard;
    const url = `/his/appointment/getDetailByIdCard/${idCard}`;
    return ajax.apiGet(url, params, options);
};

// 预约登记接口配置
export const getPreregistrationConfig: TApi = (params = {}, options = {}) => {
    const url = '/his/appointment/getConfig';
    return ajax.apiGet(url, params, options);
};

// 预约登记接口保存
export const savePreregistrationInfo: TApi = (params = {}, options = {}) => {
    const url = '/his/appointment/save';
    return ajax.apiPost(url, params, options);
};

// 获取app最新版本号 type = 1患者, 2是医生
export const getLastAppVersion: TApi = (params = {}, options = {}) => {
    const url = '/his/app/version/1';
    return ajax.apiGet(url, params, options);
};

// 获取app最新版本号 type = 1患者, 2是医生
export const getLastDoctorAppVersion: TApi = (params = {}, options = {}) => {
    const url = '/his/app/version/2';
    return ajax.apiGet(url, params, options);
};

// 1.2.1查询医生列表信息
export const queryDoctorList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDoctorList';
    return ajax.apiPost(url, params, options);
};

// 1.2.2根据科室Id查询科室信息
export const queryDepartmentById: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDepartmentById';
    return ajax.apiPost(url, params, options);
};

// 1.2.3查询部门列表信息
export const queryDepartmentList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDepartmentList';
    return ajax.apiPost(url, params, Object.assign({isLoad: true, isRCache: true, validityTime: 10}, options));
};

// 1.2.4查询医生信息
export const queryDoctorById: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDoctorById';
    return ajax.apiPost(url, params, Object.assign({closeErrorTips: true}, options));
};

// 3.9 查询药品/非药品信息
export const seachitem: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/seachitem';
    return ajax.apiPost(url, params, options);
};

// 3.17根据名字查询医生
export const seachDoctorForName: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/SeachDoctorForName';
    return ajax.apiPost(url, params, options);
};

// 3.19获取HIS服务器时间
export const queryHISTime: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/QueryHISTime';
    return ajax.apiPost(url, params, Object.assign({closeErrorTips: true}, options));
};

// 3.20查询号源医生列表信息
export const queryDoctorlistSource: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDoctorList';
    return ajax.apiPost(url, params, Object.assign({closeErrorTips: true, isLoad: true}, options));
};

// 6.30 新冠流感调查表保存
export const addCovSurvey: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/AddCovSurvey';
    return ajax.apiPost(url, params, Object.assign({closeErrorTips: true, isLoad: true}, options));
};

// 获取内侧功能白名单
export const getInnerWhitelist: TApi = (params = {}, options = {}) => {
    const url = '/his/questionnaire/getConfig';
    return ajax.apiGet(url, params, options);
};



