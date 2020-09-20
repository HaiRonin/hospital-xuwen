// import {queryHISTime} from '@/apis';
import {MutationTree, ActionTree, Module} from 'vuex';

// 处理路径前斜杆
const handleUrl = (url: string) => {
    const s0 = url[0];
    return s0 === '/' ? url : `/${url}`;
};

const state: IOBJ = {
    // 重定向前路径
    beforeUrl: null,
    // 路径类型，switchTab | 其它
    isSwitchTab: false,
    // 参数
    options: null,
};

const mutations: MutationTree<IOBJ> = {
    setData (state, {beforeUrl, isSwitchTab, options}) {
        state.beforeUrl = beforeUrl;
        state.isSwitchTab = isSwitchTab;
        state.options = options;
    },
    clearData (state) {
        state.beforeUrl = null;
        state.isSwitchTab = false;
        state.options = null;
    }
};

const getters = {
    getRedirectUrl (state: IOBJ) {
        const {beforeUrl, isSwitchTab, options} = state;

        if (utils.zEmpty(beforeUrl)) return null;

        const strData = utils.serialize(options);

        let obj: IOBJ = {
            index: 1,
            url: `${handleUrl(beforeUrl)}?${strData}`
        };

        isSwitchTab && (obj = {url: 1});

        return obj;
    },
    getBeforeUrl (state: IOBJ) {
        return state.beforeUrl;
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    getters,
} as Module<IOBJ, IOBJ>;

