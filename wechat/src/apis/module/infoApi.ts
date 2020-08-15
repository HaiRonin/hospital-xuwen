// 信息相关的借口

// 查询药品/非药品信息
export const getSeachList: TApi = (params = {}, options = {}) => {
    const url = '/his/request';
    params.api = '/Seachitem';
    return ajax.apiGet(url, params, options);
};

