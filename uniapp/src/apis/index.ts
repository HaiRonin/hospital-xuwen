// import {apiGet, apiPost, apiPut, apiDel} from '@/apis/ajax';

// console.log(apiGet, apiPost, apiPut, apiDel);

// 日期和时间统一格式为yyyy-MM-dd HH:mm:SS

// #ifdef H5
import * as baseInfo from './module/baseInfo';
import * as publicInfo from './module/publicInfo';
import * as regApi from './module/regApi';
import * as orderApi from './module/orderApi';
import * as inHospitalApi from './module/inHospitalApi';
import * as pay from './module/pay';
import * as wxApi from './module/wxApi';
import * as healthCard from './module/healthCard';
// #endif

export * from './module/baseInfo';
export * from './module/publicInfo';
export * from './module/regApi';
export * from './module/orderApi';
export * from './module/inHospitalApi';
export * from './module/pay';
export * from './module/wxApi';
export * from './module/healthCard';

// #ifdef H5
const api: IOBJ = {};
[
    ...Object.entries(healthCard),
    ...Object.entries(wxApi),
    ...Object.entries(pay),
    ...Object.entries(baseInfo),
    ...Object.entries(publicInfo),
    ...Object.entries(regApi),
    ...Object.entries(orderApi),
    ...Object.entries(inHospitalApi),
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
