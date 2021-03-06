import '@/assets/style/vantIndex.css';
import '@/assets/style/common.scss';

import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import {Icon} from 'vant';
import pageList from '@/components/pageList.vue';


Vue.config.productionTip = false;
utils.winSize();

// console.log(ajax);
// console.log(utils);
// global.utils = utils;

// Vue.mixin({
//     data () {
//         return {
//             loadCount: 0
//         };
//     }
// });

Vue.use(Icon);
Vue.component('pageList', pageList);

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');
