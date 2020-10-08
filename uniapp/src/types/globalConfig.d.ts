/**
 * vue.config.js 全局引入
 * 这里的内容都是只读的
 */

declare module globalConfig {
    /**
     * 首页3个标签的url
     */
    const homeUrl: string[];
    /**
     * 使用中的域名地址
     */
    let domain: IApiServer;
    const TEST_DOMAIN: IApiServer;
    const BUILD_DOMAIN: IApiServer;

    /**
     * 强制跳转白名单
     */
    const whiteList: string[];

    /**
     * 挂号的时间段
     */
    const sourceTimeType: {text: string, value: number}[];

    /**
     * 禁止微信授权的页面
     */
    const banAuth: string[];

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
    const APPID: string;
}
