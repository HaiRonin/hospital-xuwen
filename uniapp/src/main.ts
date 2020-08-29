import Vue from 'vue';
import App from './App.vue';
import store from './store/index';
import uView from 'uview-ui';
import forcedToLogin, {handleHomeShow} from '@/assets/js/forcedToLogin';


Vue.config.productionTip = false;

Vue.use(uView as any);
Vue.prototype.$store = store;

Vue.mixin({
    onLoad () {
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

if (process.env.NODE_ENV === 'development') {
    global.globalConfig = globalConfig;
    global.utils = utils;
    global.ajax = ajax;
    global.store = store;
}
