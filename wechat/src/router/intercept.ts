import {NavigationGuard} from 'vue-router';

export const beforeEach: NavigationGuard = (to, from, next) => {
    // console.log('原始拦截器');
    // console.log(to);
    document.title = (to.meta && to.meta.title) || '医院';
    next();
};



