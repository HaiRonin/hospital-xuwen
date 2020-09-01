import {Plugin} from 'vuex';

const cacheStore = [
    'user/setState',
    'user/clearState'
];

export default function () {
    // let initModule: any = null;

    const cachePlugin: Plugin<IOBJ> = (store) => {
        const cache = utils.getStorage('cache') || {};

        Object.keys(cache).forEach((key: string) => {
            store.commit(`${key}/setState`, cache[key] || {});
        });

        const storageFn = utils.throttle((payload: any) => {
            cache.user = payload;
            utils.setStorage('cache', cache);
        }, 400);

        store.subscribe((mutation, state) => {
            // 每次 mutation 之后调用
            // mutation 的格式为 { type, payload }

            // console.log(mutation, state);
            const {type, payload} = mutation;
            if (!~cacheStore.indexOf(type)) return;
            storageFn(payload || {});
        });
    };

    return cachePlugin;
}
