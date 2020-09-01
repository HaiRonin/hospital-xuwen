// import {queryHISTime} from '@/apis';
import {MutationTree, ActionTree, Module} from 'vuex';

const state: IOBJ = {
    userInfo: {}
};

const mutations: MutationTree<IOBJ> = {
    setState (state, data) {
        state.userInfo = data;
    },
    clearState (state) {
        state.userInfo = {};
    }
};

export default {
    namespaced: true,
    state,
    mutations,
} as Module<IOBJ, IOBJ>;

