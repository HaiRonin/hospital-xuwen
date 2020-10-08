

let apiServer: IApiServer | null = null;

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        // baseApi: 'http://120.76.246.196:8080',
        baseApi: 'http://apptest.gdsnkzxyy.cn',
        // baseApi: '/old',
        // 测试

        // #ifdef H5
        // vueconfig proxy 代理
        // baseApi: '/test',
        // baseApi: '/yh',
        // baseApi: 'http://app.gdsnkzxyy.cn:8088/hospitaldoctor/',
        // #endif
    };

} else if (process.env.NODE_ENV === 'production') {

    apiServer = {
        // baseApi: 'http://apptest.gdsnkzxyy.cn',
        baseApi: 'http://app.gdsnkzxyy.cn',
    };

}


globalConfig.domain = apiServer as IApiServer;
export default apiServer as IApiServer;
