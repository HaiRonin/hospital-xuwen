
// 获取OPENID
export const getOpenId: TApi = (params = {}, options = {}) => {
    const url = '/his/wechat/getOpenid';
    return ajax.apiGet(url, params, options);
};

// 支付宝预支付
// export const aliApppay: TApi = (params = {}, options = {}) => {
//     const url = '/his/wechat/ali_apppay';
//     return ajax.apiPost(url, params, options);
// };

// 微信公众号预支付
export const wechatPay: TApi = (params = {}, options = {}) => {
    const url = '/his/wechat/pay';
    return ajax.apiPost(url, params, options);
};

// 微信config 参数
export const wechatConfig: TApi = (params = {}, options = {}) => {
    const url = '/his/wechat/weixinImgSign';
    return ajax.apiPost(url, params, Object.assign({action: 'formData'}, options));
};

