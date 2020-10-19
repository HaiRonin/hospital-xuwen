

let apiServer: IApiServer | null = null;

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        baseApi: 'http://www.xuwenxianrenminyiyuan.cn',
        // baseApi: 'http://219.132.61.42',
        webUrl: 'http://www.xuwenxianrenminyiyuan.cn',
        // 测试

        // #ifdef H5
        // vueconfig proxy 代理
        // baseApi: '/test',
        // baseApi: '/yh',
        // baseApi: 'http://120.76.246.196:8082',
        // webUrl: '',
        // #endif
    };

} else if (process.env.NODE_ENV === 'production') {

    apiServer = globalConfig.BUILD_DOMAIN;

}

globalConfig.domain = apiServer as IApiServer;
export default apiServer as IApiServer;
