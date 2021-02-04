import Vue from 'vue';
import Vuex from 'vuex';
import cachePlugin from './cachePlugin';
import time from './modules/time';
import user from './modules/user';
import redirect from './modules/redirect';
import {getQuestionnaireConfig, getVersionConfig} from '@/apis';

Vue.use(Vuex);

const apiMap: TActionsApiMap = {
    questionnaire: getQuestionnaireConfig,
    nucleicAcid: getVersionConfig,
};

export default new Vuex.Store({
    state: {
    },
    mutations: {
    },
    actions: {
        // 权限判断，传关键字
        actionsIsUse ({commit, state}: IOBJ, key: TApiMapKey) {
            const userInfo = state.user.userInfo;
            const len = Object.keys(userInfo).length;
            if (!len) return Promise.resolve(false);

            const fn = apiMap[key];
            return fn({}, {isLoad: true, closeErrorTips: true}).then((res) => {
                // console.log(res.data);
                const state = res.data.openState || res.data.state;
                const whiteList = res.data.whiteList;

                // 1-内测,白名单可访问,2-正式开放,用户可访问；3-关闭下架,任何人不可访问
                if (+state === 2) return true;
                if (+state === 3) return false;

                if (+state === 1 && whiteList.includes(userInfo.userName)) {
                    return true;
                } else {
                    return false;
                }

            }).catch((err) => {
                console.log(err);
                return false;
            });
        }
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
