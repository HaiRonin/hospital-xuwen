// 1.1基本信息


// 1.1.1身份证号码查看患者信息
export const queryPatientInfoByIdNO: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryPatientInfoByIdNO';
    return ajax.apiGet(url, params, options);
};

// 1.1.2社保、银行、就诊卡号查看患者信息
export const queryPatientInfoBySocialsecurityNO: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryPatientInfoBySocialsecurityNO';
    return ajax.apiGet(url, params, options);
};

// 1.1.3患者档案建立
export const doCreatCardInfo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/doCreatCardInfo';
    return ajax.apiGet(url, params, options);
};

// 1.1.4查询检查,检验数据打印状态状态
export const queryExaminationRecordStatus: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryExaminationRecordStatus';
    return ajax.apiGet(url, params, options);
};

// 1.1.5更新检查,检验数据打印状态
export const updateExaminationRecordStatus: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/updateExaminationRecordStatus';
    return ajax.apiGet(url, params, options);
};

// 1.1.6获取检查,检验数据
export const queryExaminationRecordList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryExaminationRecordList';
    return ajax.apiGet(url, params, options);
};

// 1.1.7病历记录查询
export const queryHisUserRecordList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/queryHisUserRecordList';
    return ajax.apiGet(url, params, options);
};

// 1.1.8更新患者信息
export const updatePatientInfo: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/updatePatientInfo';
    return ajax.apiGet(url, params, options);
};

// 3.1诊疗卡充值
export const rechargeRefund: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/RechargeRefund';
    return ajax.apiGet(url, params, options);
};

// 3.2自助绑定
export const selfBinding: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/SelfBinding';
    return ajax.apiGet(url, params, options);
};

// 3.3密码校验
export const checkPassword: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/CheckPassword';
    return ajax.apiGet(url, params, options);
};

// 3.11注册用户
export const register: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/Register';
    return ajax.apiGet(url, params, options);
};

// 3.12添加就诊人
export const addPatients: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/AddPatients';
    return ajax.apiGet(url, params, options);
};

// 3.15删除就诊人
export const deletePatients: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/DeletePatients';
    return ajax.apiGet(url, params, options);
};

// 3.13查询就诊人列表
export const queryPatients: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/QueryPatients';
    return ajax.apiGet(url, params, options);
};

// 3.14登录验证
export const validateLogon: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/ValidateLogon';
    return ajax.apiGet(url, params, options);
};

// 3.16修改密码
export const ModifyPassword: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/ModifyPassword';
    return ajax.apiGet(url, params, options);
};
