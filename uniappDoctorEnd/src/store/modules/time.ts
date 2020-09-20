import {MutationTree, ActionTree, Module} from 'vuex';

const state: IOBJ = {
    yearDay: [],
    yearDayTime: [],

    monthDay: [],
    monthDayTime: [],

    sevenDay: [],
    sevenDayTime: [],

    day: [],
    dayTime: [],

    systemTime: '',
};

const mutations: MutationTree<IOBJ> = {
    setData (state, data) {
        state.day = data.day;
        state.dayTime = data.dayTime;

        state.sevenDay = data.sevenDay;
        state.sevenDayTime = data.sevenDayTime;

        state.monthDay = data.monthDay;
        state.monthDayTime = data.monthDayTime;

        state.yearDay = data.yearDay;
        state.yearDayTime = data.yearDayTime;

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

        res.setMonth(res.getMonth() - 1);
        const prevMonth = utils.dateData(res);
        res.setMonth(res.getMonth() + 1);

        res.setDate(res.getDate() + 1);
        const one = utils.dateData(res);
        res.setDate(res.getDate() + 6);
        const two = utils.dateData(res);

        res.setDate(res.getDate() - 6);
        const prevEnd = utils.dateData(res);
        res.setFullYear(res.getFullYear() - 1);
        const prevStart = utils.dateData(res);

        const systemTime = dayObj.time;

        const day = [dayObj.text, dayObj.text];
        const dayTime = [`${dayObj.text} 00:00:00`, `${dayObj.text} 23:59:59`];

        const sevenDay = [one.text, two.text];
        const sevenDayTime = [`${one.text} 00:00:00`, `${two.text} 23:59:59`];

        const monthDay = [prevMonth.text, dayObj.text];
        const monthDayTime = [`${prevMonth.text} 00:00:00`, `${dayObj.text} 23:59:59`];

        const yearDay = [prevStart.text, prevEnd.text];
        const yearDayTime = [`${prevStart.text} 00:00:00`, `${prevEnd.text} 23:59:59`];

        const o = {
            systemTime,

            day,
            dayTime,

            sevenDay,
            sevenDayTime,

            monthDay,
            monthDayTime,

            yearDay,
            yearDayTime
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

