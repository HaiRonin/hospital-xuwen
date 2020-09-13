/**
 * vue.config.js 全局引入
 */
import throttleFun from './throttle';
import renderListFun from './renderList';
// export * from './module/renderList';

// export const winSize = () => {
//     var scale = 1 / (window.devicePixelRatio || 1);
//     document!.querySelector('meta[name="viewport"]')!.setAttribute('content', 'width=device-width,initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale + ', user-scalable=no');

//     var deviceWidth = document.documentElement.clientWidth;
//     // const ua = navigator.userAgent.toLowerCase();
//     // alert(ua);
//     document.documentElement.style.fontSize = deviceWidth * 16 / 375 + 'px';
// };

export const throttle = throttleFun;
export const renderList = renderListFun;

export const jsCopyObj: TJsCopyObj = (data: any, cache = []) => {
    // debugger
    if (data === null || typeof data !== 'object') {
        return data;
    }

    // 循环引用
    const find = cache.find((i) => i.old === data);
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

export const toast: TToast = (() => {
    let t: number | null = null;

    return ((message, duration = 2000, forbidClick = false) => {
        return new Promise((rel, rej) => {
            uni.showToast({
                title: message,
                duration,
                mask: forbidClick,
                position: 'center',
                icon: 'none',
                success: () => {
                    if (t) {
                        clearTimeout(t);
                        t = null;
                    }
                    t = setTimeout(() => {
                        rel();
                    }, duration);
                },
                fail: rej
            });
        });
    }) as TToast;
})();

export const showLoad: TShowToast = (message = '请求中', forbidClick = true) => {
    return uni.showLoading({
        title: message,
        mask: forbidClick
    }) as unknown as Promise<void>;
};

export const hideLoad: THideLoad = async (sleepMs) => {
    if (sleepMs && sleepMs > 0) {
        await utils.sleep(sleepMs);
    }
    uni.hideLoading();
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

export const dateData: TDateData = (() => {
    const WEEK_ARR = ['日', '一', '二', '三', '四', '五', '六'];

    const addZero = (val: number) => {
        return `${val}`.padStart(2, '0');
    };

    return (date: Date, format = 'y-m-d') => {
        const o: IDateData = {
            y: date.getFullYear(),
            m: addZero(date.getMonth() + 1),
            d: addZero(date.getDate()),
            h: addZero(date.getHours()),
            mm: addZero(date.getMinutes()),
            s: addZero(date.getSeconds()),
            time: date.getTime(),
            week: WEEK_ARR[date.getDay()],
            text: '',
            textTime: '',
        };

        const text = format.replace(/[ymd]/g, (str) => {
            return o[str || ''];
        });
        o.text = text;

        const textTime = `${format} h:M:s`.replace(/[ymdhMs]/g, (str) => {
            str === 'M' && (str = 'mm');
            return o[str || ''];
        });
        o.textTime = textTime;

        return o;
    };
})();

export const toFixed: TToFixed = (val, toNum = false, retain = 2) => {
    const newVal = (+val).toFixed(retain);
    return toNum ? +newVal : newVal;
};

export const link: TLink = (() => {
    const linkMethods: TLinkMethods[] = ['navigateTo', 'redirectTo', 'reLaunch', 'switchTab', 'navigateBack'];
    const linkFlag = false;

    return function (objOrStr, index = 0) {
        // if (linkFlag) {
        //     return Promise.resolve();
        // }
        // linkFlag = true;
        // setTimeout(() => {
        //     linkFlag = false;
        // }, 200);

        let [_url, _delta, _index]: [string | number | undefined, number | undefined, number | undefined] = [undefined, undefined, undefined];

        if (typeof objOrStr === 'string') {
            _url = objOrStr;
            _index = index;
        } else if (typeof objOrStr === 'number') {
            _delta = objOrStr;
            _index = 4;
        }

        // if (_index === 4) {
        //     linkFlag = false;
        // }

        const key = linkMethods[_index || 0];
        const linkFn = uni[key];
        return new Promise((rel, rej) => {
            linkFn({
                url: _url,
                delta: _delta,
                success () {
                    rel();
                },
                fail (err: IOBJ) {
                    console.error('````````````跳转出错`````````````');
                    console.error(err);
                    rej(err);
                    // wx.reLaunch({url: '/pages/index/index'});
                }
            } as any);
        });
    } as TLink;
})();

export const sleep = (duration = 500) => {
    return new Promise((rel) => {
        setTimeout(() => {
            rel();
        }, duration);
    });
};

export const pullDown: TPullDown = async (fn) => {
    try {
        await fn();
    } catch (error) {
    }
    uni.stopPullDownRefresh();
};

export const setPageTitle: TSetPageTitle = (title) => {
    uni.setNavigationBarTitle({
        title
    });
};

export const zEmpty: TZEmpty = (str) => {
    if (typeof str === 'undefined' || (str + '') === 'null') {
        return true;
    } else if (!`${str}`) {
        return true;
    }
    return false;
};

export class CheckVal {
    [key: string]: any;
    emptyKey: TCheckData;

    constructor (emptyKey = {}) {
        this.emptyKey = emptyKey;
    }

    run (data: IOBJ) {
        const emptyKey = this.emptyKey;
        const arr: TCheckData[] = [];
        // debugger;

        Object.keys(emptyKey).forEach((key) => {
            let text = '';

            if (emptyKey[key] && zEmpty(data[key])) {
                text = emptyKey[key];
            } else if (typeof this[key] === 'function') {
                text = this[key](data[key], data);
            }

            text && arr.push({key, text});
        });
        // console.log(arr);
        return this.handleInfo(arr);
    }

    handleInfo (arr: TCheckData[]) {
        const text = (arr[0] || {}).text || '';
        text && toast(text);
        return !!text;
    }

    _addRule (key: string, fn: TCheckAddRuleFn) {
        this[key] = fn;
    }

    bankCard (val: string) {
        return !/^([1-9]{1})(\d{15}|\d{18})$/.test(val) ? '请输入正确银行卡号' : '';
    }

    phone (val: string) {
        return !/^1[0-9]{10}$/.test(val) ? '请输入正确手机号' : '';
    }

    idCard (val: string) {
        return !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(val) ? '请输入正确的身份证号' : '';
    }
}

export const getStorage: TGetStorage = (key) => {
    return uni.getStorageSync(key);
};

export const setStorage: TSetStorage = (key, val) => {
    return uni.setStorageSync(key, val);
};

export const removeStorage: TRemoveStorage = (key) => {
    return uni.removeStorageSync(key);
};

export const clearStorage: TClearStorage = () => {
    return uni.clearStorageSync();
};

export const confirm: TConfirm = ({content = '', title = '提示', cancelText = '取消', confirmText = '确认', showCancel = true} = {}) => {
    return new Promise((rel, rej) => {
        uni.showModal({
            content,
            title,
            cancelText,
            confirmText,
            showCancel,
            success (res) {
                res.confirm ? rel(res.confirm) : rej(res.cancel);
            }
        });
    });

};

