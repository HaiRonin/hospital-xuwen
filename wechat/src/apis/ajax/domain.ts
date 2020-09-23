

let apiServer: IApiServer | null = null;


// let urlEnv: any = /env=(\w{1,})/.exec(window.location.hash);
// urlEnv && urlEnv.length && (urlEnv = urlEnv[1]);

let parendDomain: any = /parendDomain=(\S{1,})/.exec(window.location.hash);
parendDomain && parendDomain.length && (parendDomain = parendDomain[1]);
parendDomain && (parendDomain = decodeURIComponent(parendDomain));
console.log(window.location.hash);
console.log(parendDomain);

if (process.env.NODE_ENV === 'development') {

    apiServer = {
        // 正式
        // 测试
        baseApi: 'http://apptest.gdsnkzxyy.cn',

        // vueconfig proxy 代理
        // baseApi: '',
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

parendDomain && apiServer && (apiServer.baseApi = parendDomain);

// const win: any = window;
// if (win.uni) {
//     apiServer = win.uni.apiServer;
//     alert(1);
// }

export default apiServer as IApiServer;
