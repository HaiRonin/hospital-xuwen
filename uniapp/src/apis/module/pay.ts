
// payType 2-支付宝   5-公众号  7-H5  （暂定微信ap）

// 新增预约挂号的记录
export const orderOutpatientPayment: TApi = (params = {}, options = {}) => {
    const url = '/his/order/outpatientPayment';
    return ajax.apiPost(url, params, options);
};

// 新增缴费支付的记录
export const ordeNewPayment: TApi = (params = {}, options = {}) => {
    const url = '/his/order/newPayment';
    return ajax.apiPost(url, params, options);
};


// 新增押金补缴的记录
export const ordePayment: TApi = (params = {}, options = {}) => {
    const url = '/his/order/payment';
    return ajax.apiPost(url, params, options);
};

// 新增出院结算记录
export const ordeLeaveHosPay: TApi = (params = {}, options = {}) => {
    const url = '/his/order/leaveHosPay';
    return ajax.apiPost(url, params, options);
};

// 取消预约并退款
export const ordeDoRegCancel: TApi = (params = {}, options = {}) => {
    const url = '/his/order/doRegCancel';
    return ajax.apiPost(url, params, options);
};

// 免费预约
export const freeAppointment: TApi = (params = {}, options = {}) => {
    const url = '/his/order/freeAppointment';
    return ajax.apiPost(url, params, options);
};

// 核酸支付
export const nucleicAcidPayment: TApi = (params = {}, options = {}) => {
    const url = '/his/order/payCovPackage';
    return ajax.apiPost(url, params, options);
};
