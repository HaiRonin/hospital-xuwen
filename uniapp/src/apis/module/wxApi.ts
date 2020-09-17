
// 获取OPENID
export const getOpenId: TApi = (params = {}, options = {}) => {
    const url = '/hospital/wechat/getOpenid';
    return ajax.apiGet(url, params, options);
};

