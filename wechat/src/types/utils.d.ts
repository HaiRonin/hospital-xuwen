
// import {VanToast} from 'vant/types/toast';

/**
 * 使用 webpack.ProvidePlugin 引入文件
 * 配置在根目录 vue.config.js
 */


type TJsCopyObj = <T>(data: T, cache?: any[]) => T;

type TToast = (message: string, duration?: number, forbidClick?: boolean) => Promise<void>;

type TShowToast = (message?: string, forbidClick?: boolean) => IOBJ;

type ISerialize = (data: IOBJ) => 'a=1&b=2&c=3' | string;

interface IDateData {
    // 年
    y: number | string;
    // 月
    m: number | string;
    // 日
    d: number | string;
    // 小时
    h: number | string;
    // 分
    mm: number | string;
    // 秒
    s: number | string;
    // 时间戳
    time: number | string;
    // 周几
    week: number | string;
}


type TFunCallBack = (...arr: any) => any;
type TFun = (...arr: []) => TFunCallBack;
type TImmediateFun = (fn: TFunCallBack, interval: number, fakeInterval: number) => TFunCallBack;
type TThrottle = (fn: TFunCallBack, interval?: 200 | number, fakeInterval?: 300 | number, immediate?: false | boolean) => TFunCallBack;
type TDateData = (date: Date) => IDateData;

declare module utils {

    /**
     * js 递归拷贝
     */
    const jsCopyObj: TJsCopyObj;

    /**
     * 提示框
     * message 提示内容
     * duration 展示时长(ms)，值为 0 时，toast 不会消失
     * forbidClick 是否禁止背景点击
     */
    const toast: TToast;

    /**
     * 弹出加载框
     * message 提示内容
     * forbidClick 是否禁止背景点击
     */
    const showLoad: TShowToast;

    /**
     * 隐藏toast
     */
    const hideLoad: () => void;

    /**
     * 参数序列化
     */
    const serialize: ISerialize;

    /**
     * 函数节流
     * @param {*} fn 函数
     * @param {*} interval 解锁
     * @param {*} fakeInterval 真正的解锁 在前面的基础上延迟（ms）解锁
     * @param {*} immediate 是否立即执行
     */
    const throttle: TThrottle;

    const winSize: () => void;

    const dateData: TDateData;
}

