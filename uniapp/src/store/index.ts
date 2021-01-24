import Vue from 'vue';
import Vuex from 'vuex';
import cachePlugin from './cachePlugin';
import time from './modules/time';
import user from './modules/user';
import redirect from './modules/redirect';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        // 1-内测,白名单可访问,2-正式开放,用户可访问；3-关闭下架,任何人不可访问
        whiteListStatus: 3,
        whiteList: [],
    },
    mutations: {
        setWhiteData (state: IOBJ, data: IOBJ) {
            state.whiteListStatus = data.whiteListStatus;
            state.whiteList = data.whiteList;
        }
    },
    actions: {
    },
    getters: {
        // 当天日期
        day (state: IOBJ) {
            return state.time.day;
        },
        dayTime (state: IOBJ) {
            return state.time.dayTime;
        },
        // 未来七天日期
        sevenDay (state: IOBJ) {
            return state.time.sevenDay;
        },
        // 近一年时间范围
        yearDay (state: IOBJ) {
            return state.time.yearDay;
        },
        // 近一月时间范围
        monthDay (state: IOBJ) {
            return state.time.monthDay;
        },
        // 系统时间
        systemTime (state: IOBJ) {
            return state.time.systemTime;
        },
        // 是否登录了
        isLogin (state: IOBJ) {
            const len = Object.keys(state.user.userInfo).length;
            return !!len;
        },
        // 用户信息
        userInfo (state: IOBJ) {
            return state.user.userInfo;
        },
        // 是否可用
        isUse (state: IOBJ) {
            const userInfo = state.user.userInfo;
            const len = Object.keys(userInfo).length;
            const whiteListStatus = +state.whiteListStatus;
            const whiteList = state.whiteList;

            if (whiteListStatus === 2) return true;
            if (!len || whiteListStatus === 3) return false;

            if (whiteListStatus === 1 && whiteList.includes(userInfo.userName)) {
                return true;
            } else {
                return false;
            }
        }
    },
    modules: {
        time,
        user,
        redirect
    },
    plugins: [
        cachePlugin(),
    ]
});
