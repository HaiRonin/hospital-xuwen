import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
    },
    mutations: {
        upDataTitle (state, title: string) {
            // debugger;
            document.title = title;
        }
    },
    actions: {
    },
    modules: {
    }
});
