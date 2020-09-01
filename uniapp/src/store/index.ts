import Vue from 'vue';
import Vuex from 'vuex';
import cachePlugin from './cachePlugin';
import time from './modules/time';
import user from './modules/user';
import redirect from './modules/redirect';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
    },
    mutations: {
    },
    actions: {
    },
    getters: {
        // 当天日期
        day (state: IOBJ) {
            return state.time.day;
        },
        // 未来七天日期
        sevenDay (state: IOBJ) {
            return state.time.sevenDay;
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
