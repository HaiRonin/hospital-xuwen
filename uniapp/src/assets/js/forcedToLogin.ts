
import store from '@/store';

const {homeUrl, whiteList} = globalConfig;

const getCurUrl = () => {
    const pages = getCurrentPages();
    const page = pages[pages.length - 1];
    return page;
};

// 处理跳转逻辑
const handle = utils.throttle(() => {
    const page: IOBJ = getCurUrl();
    const {route, options} = page;
    const url = '/pages/login';
    // 重定向 或 跳新页面
    let index = 1;

    // console.log(page);
    // console.log(page.route);
    // console.log(page.options);

    if (whiteList.some((url) => !!~url.indexOf(route))) return;

    if (homeUrl.some((url) => !!~url.indexOf(route))) {
        index = 0;
    }

    store.commit('redirect/setData', {
        beforeUrl: route,
        isSwitchTab: index === 0,
        options: utils.jsCopyObj(options)
    });

    utils.link(url, index);
}, 400);

// 处理首页面的 onshow 登录处理
export const handleHomeShow = () => {
    if (store.getters.isLogin) return;

    const page: IOBJ = getCurUrl();
    if (utils.zEmpty(page)) return;

    const route = page.route;
    const beforeUrl = store.getters['redirect/getBeforeUrl'];

    if (route === beforeUrl) {
        store.commit('redirect/clearData');
        return;
    }

    if (homeUrl.some((url) => !!~url.indexOf(route))) {
        handle();
    }
};

export default function () {
    if (store.getters.isLogin) return;
    handle();
}
