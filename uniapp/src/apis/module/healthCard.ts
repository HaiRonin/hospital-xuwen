
// 注册健康码
export const healthCardRegisterHealthCard: TApi = (params = {}, options = {}) => {
    const url = '/his/healthCard/registerHealthCard';
    return ajax.apiPost(url, params, options);
};

// 获取健康码
export const healthCardGetHealthCard: TApi = (params = {}, options = {}) => {
    const url = '/his/healthCard/getHealthCard';
    return ajax.apiGet(url, params, options);
};

// 获取健康卡二维码接口
export const healthCardGetDynamicQRCode: TApi = (params = {}, options = {}) => {
    const url = '/his/healthCard/getDynamicQRCode';
    return ajax.apiPost(url, params, options);
};

// 身份证照片OCR接口
export const healthCardOcrInfo: TApi = (params = {}, options = {}) => {
    const url = '/his/healthCard/ocrInfo';
    return ajax.apiPost(url, params, Object.assign({action: 'formData'}, options));
};

// 获取验证码短信
export const healthCardSendCode: TApi = (params = {}, options = {}) => {
    const url = '/his/healthCard/sendCode';
    return ajax.apiGet(url, params, Object.assign({action: 'formData'}, options));
};

// 获取卡包订单ID接口
export const healthCardGetOrderIdByOutAppId: TApi = (params = {}, options = {}) => {
    const url = '/his/healthCard/getOrderIdByOutAppId';
    return ajax.apiPost(url, params, Object.assign({action: 'formData'}, options));
};

