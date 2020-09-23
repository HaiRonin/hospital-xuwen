

let apiServer: IApiServer | null = null;

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        // baseApi: 'http://120.76.246.196:8080',
        baseApi: 'http://apptest.gdsnkzxyy.cn',
        webUrl: 'http://apptest.gdsnkzxyy.cn',
        // 测试

        // #ifdef H5
        // vueconfig proxy 代理
        // baseApi: '/test',
        // baseApi: '/yh',
        // baseApi: '',
        // #endif
    };

} else if (process.env.NODE_ENV === 'test') {

    apiServer = globalConfig.TEST_DOMAIN;

} else if (process.env.NODE_ENV === 'production') {

    apiServer = globalConfig.BUILD_DOMAIN;

}

globalConfig.domain = apiServer as IApiServer;
export default apiServer as IApiServer;
