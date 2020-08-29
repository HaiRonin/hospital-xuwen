// 1.3预约挂号

// 1.3.1取消预约
export const doRegCancel: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/doRegCancel';
    return ajax.apiGet(url, params, options);
};

// 1.3.2预约挂号（可兼容支付和未支付）
export const doReg: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/doReg';
    return ajax.apiGet(url, params, options);
};

// 1.3.3查询号源
export const queryToRegDoctorList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryToRegDoctorList';
    return ajax.apiGet(url, params, options);
};

// 1.3.4根据医生查询号源
export const queryToRegDoctorListByDoctorId: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryToRegDoctorListByDoctorId';
    return ajax.apiGet(url, params, Object.assign({isLoad: true}, options));
};

// 1.3.5排取人数
export const queryRegWaitNum: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryRegWaitNum';
    return ajax.apiGet(url, params, options);
};

// 1.3.6取号查询接口
export const queryTaketheNo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryTaketheNo';
    return ajax.apiGet(url, params, options);
};

// 1.3.7取号
export const doTaketheNo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/doTaketheNo';
    return ajax.apiGet(url, params, options);
};

// 1.3.8预约当天并取号
export const doRegAndTaketheNo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/doRegAndTaketheNo';
    return ajax.apiGet(url, params, options);
};

// 1.3.9查询未取号的预约挂号
export const queryRegBypatientNo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryRegBypatientNo';
    return ajax.apiGet(url, params, options);
};

// 1.3.10查询医生号源时间段
export const queryToRegDoctorTimes: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryToRegDoctorTimes';
    return ajax.apiGet(url, params, options);
};

// 1.3.11查询挂号支付状态
export const queryAppointmentPayStatus: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryAppointmentPayStatus';
    return ajax.apiGet(url, params, options);
};

// 1.3.12医生停诊
export const doctorStopped: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/doctorStopped';
    return ajax.apiGet(url, params, options);
};

// 3.4作废挂号
export const cancelRegister: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/CancelRegister';
    return ajax.apiGet(url, params, options);
};

// 3.5查询挂号记录
export const queryReg: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/QueryReg';
    return ajax.apiGet(url, params, options);
};

// 3.6自助挂号发票打印
// 3.8自助缴费发票打印
export const printRegister: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/PrintRegister';
    return ajax.apiGet(url, params, options);
};

// 3.7购买病历本
export const purchaseregister: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/purchaseregister';
    return ajax.apiGet(url, params, options);
};

