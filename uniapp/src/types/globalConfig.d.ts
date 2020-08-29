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
     * 从domain 文件进行赋值
     */
    let domain: IApiServer;

    /**
     * 强制跳转白名单
     */
    const whiteList: string[];
}
