/**
 * vue.config.js 全局引入
 * 这里的内容都是只读的
 */

declare module globalConfig {
    /**
     * 首页3个标签的url，没用的
     */
    const homeUrl: string[];
    /**
     * 从domain 文件进行赋值
     */
    let domain: IApiServer;

    /**
     * 强制跳转白名单
     */
    const whiteList: string[];

    /**
     * 挂号的时间段
     */
    const sourceTimeType: {text: string, value: number}[];

    /**
     * 以为支付的原因，需要刷新页面（ios上）
     */
    const refreshUrl: string[];

    const gFilter: (val: any, list: IOBJ[], key?: string | 'value') => string;
    const reportType: IOBJ[];
    const reportStatus: IOBJ[];
    const regStatus: IOBJ[];
    const payType: IOBJ[];
    const inHosState: IOBJ[];
    const sexState: IOBJ[];
}
