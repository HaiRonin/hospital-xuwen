

let apiServer: IApiServer | null = null;


let urlEnv: any = /env=(\w{1,})/.exec(window.location.hash);
urlEnv && urlEnv.length && (urlEnv = urlEnv[1]);

console.log(window.location.hash);
console.log(urlEnv);

if (process.env.NODE_ENV === 'development' || urlEnv === 'development') {

    apiServer = {
        // 正式
        // 测试
        // baseApi: 'http://120.76.246.196',

        // vueconfig proxy 代理
        baseApi: '',
    };

} else if (process.env.NODE_ENV === 'test' || urlEnv === 'test') {

    apiServer = {
        baseApi: 'http://120.76.246.196:9090',
    };

} else if (process.env.NODE_ENV === 'production' || urlEnv === 'production') {

    apiServer = {
        baseApi: 'http://120.76.246.196',
    };

}

// const win: any = window;
// if (win.uni) {
//     apiServer = win.uni.apiServer;
//     alert(1);
// }

export default apiServer as IApiServer;
