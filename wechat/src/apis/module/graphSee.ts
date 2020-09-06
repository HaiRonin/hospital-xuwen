
// 获取所有的身体部位
export const getBodyListPart: TApi = (params = {}, options = {}) => {
    const url = '/his/getBodyListPart';
    return ajax.apiPost(url, params, options);
};

// 根据身体部位获取对应的病症
export const getOrganList: TApi = (params = {}, options = {}) => {
    const url = '/his/getOrganList';
    return ajax.apiPost(url, params, Object.assign({isLoad: true}, options));
};
