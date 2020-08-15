

let apiServer: IApiServer | null = null;

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        // 测试
        // baseApi: 'http://120.76.246.196',

        // vueconfig proxy 代理
        baseApi: '',
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

export default apiServer as IApiServer;
