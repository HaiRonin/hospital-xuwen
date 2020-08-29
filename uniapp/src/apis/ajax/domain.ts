

let apiServer: IApiServer | null = null;

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        // 测试
        baseApi: 'http://120.76.246.196',

        // #ifdef H5
        // vueconfig proxy 代理
        baseApi: '',
        // #endif
    };

} else if (process.env.NODE_ENV === 'test') {

    apiServer = {
        baseApi: '',
    };

} else if (process.env.NODE_ENV === 'production') {

    apiServer = {
        baseApi: '',
    };

}


globalConfig.domain = apiServer as IApiServer;
export default apiServer as IApiServer;
