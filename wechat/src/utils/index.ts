import {Toast} from 'vant';
import throttleFun from './throttle';

export const winSize = () => {
    var scale = 1 / (window.devicePixelRatio || 1);
    document!.querySelector('meta[name="viewport"]')!.setAttribute('content', 'initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale + ', user-scalable=no');

    var deviceWidth = document.documentElement.clientWidth;
    document.documentElement.style.fontSize = (deviceWidth * 16 / 375) + 'px';
};

export const jsCopyObj: TJsCopyObj = (data: any, cache = []) => {
    // debugger
    if (data === null || typeof data !== 'object') {
        return data;
    }

    // 循环引用
    const find = cache.find((i) => (i.old === data));
    if (find) {
        return find.obj;
    }

    const obj: any = Array.isArray(data) ? [] : {};

    cache.push({
        obj,
        old: data
    });

    Object.keys(data).forEach((key: string) => {
        obj[key] = jsCopyObj(data[key], cache);
    });

    return obj;
};

export const toast: TToast = (message, duration = 2000, forbidClick = false) => {
    return new Promise((rel, rej) => {
        Toast({
            message,
            duration,
            forbidClick,
            onClose: rel,
        });
    });
};

export const showLoad: TShowToast = (message = '请求中', forbidClick = true) => {
    return Toast.loading({
        message,
        forbidClick,
        duration: 0
    });
};

export const hideLoad = () => {
    Toast.clear();
};

export const serialize: ISerialize = (data) => {
    const arr = [];
    for (const i in data) {
        const str = data[i];
        // item && (item = `${item}`.replace(/ /g, '%20'));
        arr.push(`${i}=${str || ''}`);
    }
    return arr.join('&');
};

export const throttle = throttleFun;

export const dateData: TDateData = (() => {
    const WEEK_ARR = ['日', '一', '二', '三', '四', '五', '六'];

    const addZero = (val: number) => {
        return (val >= 10 ? val : `0${val}`);
    };

    return (date: Date) => {
        return {
            y: date.getFullYear(),
            m: addZero(date.getMonth() + 1),
            d: addZero(date.getDate()),
            h: addZero(date.getHours()),
            mm: addZero(date.getMinutes()),
            s: addZero(date.getSeconds()),
            time: date.getTime(),
            week: WEEK_ARR[date.getDay()],
        };
    };
})();
