import {queryHISTime} from '@/apis';
import {MutationTree, ActionTree, Module} from 'vuex';

const state: IOBJ = {
    day: [],
    dayTime: [],
    sevenDay: [],
    sevenDayTime: [],
    systemTime: '',
};

const mutations: MutationTree<IOBJ> = {
    setData (state, data) {
        state.day = data.day;
        state.dayTime = data.dayTime;
        state.sevenDay = data.sevenDay;
        state.sevenDayTime = data.sevenDayTime;
        state.systemTime = data.systemTime;
    }
};

const actions: ActionTree<IOBJ, IOBJ> = {
    ajaxGetTime ({commit}) {
        // 以为有获取系统时间接口。。。
        // let res;
        // try {
        //     res = queryHISTime();
        // } catch (error) {
        //     res = new Date();
        // }
        const res = new Date();
        const dayObj = utils.dateData(res);
        res.setDate(res.getDate() + 1);
        const one = utils.dateData(res);
        res.setDate(res.getDate() + 6);
        const two = utils.dateData(res);

        const systemTime = dayObj.time;
        const day = [dayObj.text, dayObj.text];
        const dayTime = [`${dayObj.text} 00:00:00`, `${dayObj.text} 23:59:59`];
        const sevenDay = [one.text, two.text];
        const sevenDayTime = [`${one.text} 00:00:00`, `${two.text} 23:59:59`];

        const o = {
            systemTime,
            day,
            dayTime,
            sevenDay,
            sevenDayTime
        };
        commit('setData', o);
        return o;
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions
} as Module<IOBJ, IOBJ>;

