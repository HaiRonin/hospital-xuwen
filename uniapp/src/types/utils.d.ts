
// import {VanToast} from 'vant/types/toast';

/**
 * 使用 webpack.ProvidePlugin 引入文件
 * 配置在根目录 vue.config.js
 */


interface IDateData extends IOBJ{
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
    time: number;
    // 周几
    week: number | string;
    // 是否周末
    weekend: boolean;
    // 2002-01-01
    text: string;
    // 2002-01-01 00:00:00
    textTime: string;
}
interface IConfirmData {
    content?: string;
    title?: string | '提示'
    cancelText?: string | '取消';
    confirmText?: string | '确认';
    showCancel?: boolean | true;
}
type TLinkMethods = 'navigateTo' | 'redirectTo' | 'reLaunch' | 'switchTab' | 'navigateBack';


interface IRenderData {
    /**
     * 存放数据的数组，
     */
    list: IOBJ[];
    /**
     * 要被渲染的数据源，
     */
    data: IOBJ[];
    /**
     * 每次渲染条数
     */
    num?: number | 10;
    /**
     * 间隔时间
     */
    interval?: number | 200;
    /**
     * 处理数据的函数
     * item 单条数据
     * index 循环的下标
     * count 累计循环的次数
     */
    cb?: (item: IOBJ, index: number, count: number) => IOBJ;
}

type TClearRenderList = () => void;
type TRenderList = (data: IRenderData) => TClearRenderList;
type TJsCopyObj = <T>(data: T, cache?: any[]) => T;
type TToast = (message: string, duration?: number | 2000, forbidClick?: boolean) => Promise<void>;
type TShowToast = (message?: string, forbidClick?: boolean) => IOBJ;
type ISerialize = (data: IOBJ) => 'a=1&b=2&c=3' | string;
type TFunCallBack = (...arr: any) => any;
type TFun = (...arr: []) => TFunCallBack;
type TImmediateFun = (fn: TFunCallBack, interval: number, fakeInterval: number) => TFunCallBack;
type TThrottle = (fn: TFunCallBack, interval?: 200 | number, fakeInterval?: 300 | number, immediate?: false | boolean) => TFunCallBack;
type TDateData = (date: Date, format?: string) => IDateData;
type TToFixed = (val: string | number, toNum?: boolean | false, retain?: number | 2) => string | number;
type TLink = (url: string | number, index?: number) => Promise<void>;
type TSleep = (duration?: number | 500) => Promise<void>;
type THideLoad = () => void;
type TPullDown = (fn: () => Promise<void>) => void;
type TSetPageTitle = (title: string) => void;
type TZEmpty = (str: any) => boolean;
type TGetStorage = (str: string) => any;
type TSetStorage = (str: string, val: any) => void;
type TRemoveStorage = (str: string) => void;
type TClearStorage = (key?: string) => void;
type TConfirm = (duration: IConfirmData) => Promise<boolean>;
type IGetBirthdayFromIdCard = (idCard: string) => string;
type IGetSexIdCard = (idCard: string) => string;
type IGetAgeIdCard = (idCard: string) => number;

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
     * sleepMs 多少ms后再执行
     */
    const hideLoad: THideLoad;

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

    /**
     * date 时间对象
     * format text 输出格式，默认 y-m-d
     */
    const dateData: TDateData;

    /**
     * 保留小数点后两位
     * val 需要处理的值
     * toNum 是否转为数值
     * retain 保留位数
     */
    const toFixed: TToFixed;

    /**
     * 跳转 url:String | Number, index：['navigateTo', 'redirectTo', 'reLaunch', 'switchTab', 'navigateBack']
     * @param {*} url
     * @param {*} index
     */
    const link: TLink;

    /**
     * async/await 阻断执行，默认500ms
     */
    const sleep: TSleep;

    /**
     * 配合 onPullDownRefresh 使用
     * 主要为了调用 下拉刷新还原
     */
    const pullDown: TPullDown;

    /**
     * 修改页面标题
     */
    const setPageTitle: TSetPageTitle;

    /**
     * 判断是否为空
     */
    const zEmpty: TZEmpty;

    /**
     * 实例化
     * 检验数据
     * emptyKey 需要校验的key，以及为空的提示
     */
    const CheckVal: ICheckVal;

    /**
     * 本地储存一系列
     */
    const getStorage: TGetStorage;
    const setStorage: TSetStorage;
    const removeStorage: TRemoveStorage;
    const clearStorage: TClearStorage;

    /**
     * 系统自带弹窗
     */
    const confirm: TConfirm;

    /**
     * 数据列表渲染（断续渲染）
     * list 要插入数据的列表
     * data 数据源
     * num 每次插入数目
     * interval 间隔时间
     * cb 每次插入时执行的函数
     */
    const renderList: TRenderList;

    /**
     * 通过身份证，获取出生日期
     */
    const getBirthdayFromIdCard: IGetBirthdayFromIdCard;

    /**
     * 通过身份证，获取性别
     */
    const getSexIdCard: IGetSexIdCard;

    /**
     * 通过身份证，获取年龄
     */
    const getAgeIdCard: IGetAgeIdCard;

}

