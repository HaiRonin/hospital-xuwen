// import {apiGet, apiPost, apiPut, apiDel} from '@/apis/ajax';

// console.log(apiGet, apiPost, apiPut, apiDel);

// 日期和时间统一格式为yyyy-MM-dd HH:mm:SS
import * as baseInfo from './module/baseInfo';
import * as publicInfo from './module/publicInfo';
import * as regApi from './module/regApi';
import * as orderApi from './module/orderApi';
import * as inHospitalApi from './module/inHospitalApi';

export * from './module/baseInfo';
export * from './module/publicInfo';
export * from './module/regApi';
export * from './module/orderApi';
export * from './module/inHospitalApi';

const api: IOBJ = {};
[
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

// console.log(api);

// export default {...api};
// module.exports = api;
