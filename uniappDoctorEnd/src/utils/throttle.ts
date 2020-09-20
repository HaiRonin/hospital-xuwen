
const immediateFun: TImmediateFun = (fn, interval, fakeInterval) => {
    let look = false;
    let prev = 0;

    return function (this: any, ...arg) {
        if (look) return;

        if (Date.now() - prev > interval) {
            look = true;
            prev = Date.now();
            fn.apply(this, arg);
            setTimeout(() => {
                look = false;
            }, Math.max(fakeInterval - interval, 0));
        }
    };
};

const delayFun: TImmediateFun = (fn, interval, fakeInterval) => {
    let look = false;
    let time: any = null;

    return function (this: any, ...arg) {

        if (look) return;

        if (time) {
            clearTimeout(time);
            time = null;
        }

        time = setTimeout(() => {
            look = true;
            fn.apply(this, arg);

            setTimeout(() => {
                look = false;
            }, Math.max(fakeInterval - interval, 0));
        }, interval);
    };
};

const throttle: TThrottle = (fn, interval = 200, fakeInterval = 300, immediate = false) => {
    const thisFun = immediate ? immediateFun : delayFun;
    return thisFun(fn, interval, fakeInterval);
};

export default throttle;
