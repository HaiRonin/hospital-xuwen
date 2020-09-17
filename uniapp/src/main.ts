import Vue from 'vue';
import App from './App.vue';
import store from './store/index';
import uView from 'uview-ui';
import forcedToLogin, {handleHomeShow} from '@/assets/js/forcedToLogin';
import '@/assets/js/wxUtils';
// import '@/assets/js/intelligentGuidanceApi';


Vue.config.productionTip = false;

Vue.use(uView as any);
Vue.prototype.$store = store;

Vue.mixin({
    onLoad (options: IOBJ) {
        for (const key in options) {
            typeof options[key] === 'number' && (options[key] = `${options[key]}`);
        }
        // console.log(store.getters.isLogin);
        store.dispatch('time/ajaxGetTime');
        forcedToLogin();
    },
    onShow () {
        handleHomeShow();
    }
});

new App({
    store
}).$mount();

// console.log(utils.serialize);
// console.log(ajax.apiGet);
// console.log(uni.showToast('13'));
// console.log(queryDepartmentList());


// #ifdef H5
if (process.env.NODE_ENV === 'development') {
    (global as any).globalConfig = globalConfig;
    (global as any).utils = utils;
    (global as any).ajax = ajax;
    (global as any).store = store;
}
// #endif
