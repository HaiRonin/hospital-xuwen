
import {getOrganList, getBodyListPart} from '@/apis';

const obj = {
    async getOrganList (item: IOBJ, fn: (data: IOBJ) => void) {
        console.log(item);
        const res = await getOrganList(item, {isLoad: true}).catch(() => {
            return {data: []};
        });
        fn(res);
    },
    async getBodyListPart (fn: (data: IOBJ) => void) {
        const res = await getBodyListPart({}, {isLoad: true}).catch(() => {
            return {data: []};
        });
        fn(res);
    },
    link (item: IOBJ) {
        // console.log(item);
        utils.link(`/pages/registration/diagnosis?${utils.serialize(item)}`);
    }
};

// export default obj;
if (typeof window !== 'undefined') {
    // 提供给 webview 的api
    (window as any).intelligentGuidance = obj;
}

