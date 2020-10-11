import Vue from 'vue';
import App from './App.vue';
import store from './store/index';
import uView from 'uview-ui';
// import forcedToLogin, {handleHomeShow} from '@/assets/js/forcedToLogin';
// import '@/assets/js/wxUtils';


Vue.config.productionTip = false;

Vue.use(uView as any);
Vue.prototype.$store = store;

Vue.mixin({
    onLoad (options: IOBJ) {
        for (const key in options) {
            typeof options[key] === 'number' && (options[key] = `${options[key]}`);
        }
        store.dispatch('time/ajaxGetTime');
        // forcedToLogin();
    },
    onShow () {
        // handleHomeShow();
    }
});

new App({
    store
}).$mount();

// console.log(utils.serialize);
// console.log(ajax.apiGet);
// console.log(uni.showToast('13'));
// console.log(queryDepartmentList());


store.commit('user/setState', {openId: '123', synUserName: '', synKey: '', id: '1'});

// #ifdef H5
if (process.env.NODE_ENV === 'development') {
    (global as any).globalConfig = globalConfig;
    (global as any).utils = utils;
    (global as any).ajax = ajax;
    (global as any).store = store;
}
// #endif
