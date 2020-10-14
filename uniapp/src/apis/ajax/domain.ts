

let apiServer: IApiServer | null = null;

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        // baseApi: 'http://app.gdsnkzxyy.cn',
        baseApi: 'http://219.129.40.82',
        webUrl: 'http://app.gdsnkzxyy.cn',

        // 测试
        // baseApi: 'http://apptest.gdsnkzxyy.cn',
        // baseApi: 'http://120.76.246.196:8080',
        // webUrl: 'http://apptest.gdsnkzxyy.cn',


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
