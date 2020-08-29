// 1.2公共信息


// 1.2.1查询医生列表信息
export const queryDoctorList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDoctorList';
    return ajax.apiGet(url, params, options);
};

// 1.2.2根据科室Id查询科室信息
export const queryDepartmentById: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDepartmentById';
    return ajax.apiGet(url, params, options);
};

// 1.2.3查询部门列表信息
export const queryDepartmentList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDepartmentList';
    return ajax.apiGet(url, params, Object.assign({isLoad: true, isRCache: true, validityTime: 10}, options));
};

// 1.2.4查询医生信息
export const queryDoctorById: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDoctorById';
    return ajax.apiGet(url, params, Object.assign({closeErrorTips: true}, options));
};

// 3.9 查询药品/非药品信息
export const seachitem: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/seachitem';
    return ajax.apiGet(url, params, options);
};

// 3.17根据名字查询医生
export const seachDoctorForName: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/SeachDoctorForName';
    return ajax.apiGet(url, params, options);
};

// 3.19获取HIS服务器时间
export const queryHISTime: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/QueryHISTime';
    return ajax.apiGet(url, params, Object.assign({closeErrorTips: true}, options));
};

// 3.20查询号源医生列表信息
export const queryDoctorlistSource: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryDoctorList';
    return ajax.apiGet(url, params, Object.assign({closeErrorTips: true, isLoad: true}, options));
};

