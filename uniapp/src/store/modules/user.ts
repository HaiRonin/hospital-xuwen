// import {queryHISTime} from '@/apis';
import {MutationTree, ActionTree, Module} from 'vuex';

const state: IOBJ = {
    userInfo: null
};

const mutations: MutationTree<IOBJ> = {
    setState (state, data) {
        state.userInfo = data;
    }
};

export default {
    namespaced: true,
    state,
    mutations,
} as Module<IOBJ, IOBJ>;

