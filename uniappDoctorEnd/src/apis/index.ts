// import {apiGet, apiPost, apiPut, apiDel} from '@/apis/ajax';

// console.log(apiGet, apiPost, apiPut, apiDel);

// 日期和时间统一格式为yyyy-MM-dd HH:mm:SS

// #ifdef H5
import * as baseInfo from './module/baseInfo';
// #endif

export * from './module/baseInfo';
// #ifdef H5
const api: IOBJ = {};
[
    ...Object.entries(baseInfo),
].forEach(([key, fn]: [string, TApi]) => {
    const item = api[key];
    if (!item) {
        api[key] = fn;
    } else {
        console.error(`${key} 该接口命名重复`);
    }
});

console.log(api);
// #endif
