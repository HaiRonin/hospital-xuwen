

let apiServer: IApiServer | null = null;

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        // baseApi: 'http://120.76.246.196:8080',
        baseApi: 'http://apptest.gdsnkzxyy.cn',
        // 测试

        // #ifdef H5
        // vueconfig proxy 代理
        // baseApi: '/test',
        // baseApi: '/yh',
        // baseApi: '',
        // #endif
    };

} else if (process.env.NODE_ENV === 'test') {

    apiServer = {
        baseApi: 'http://apptest.gdsnkzxyy.cn',
    };

} else if (process.env.NODE_ENV === 'production') {

    apiServer = {
        baseApi: 'http://apptest.gdsnkzxyy.cn',
    };

}


globalConfig.domain = apiServer as IApiServer;
export default apiServer as IApiServer;
