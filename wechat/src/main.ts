import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import {Icon} from 'vant';
import '@/assets/style/common.scss';
import 'vant/lib/index.css';

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

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');
