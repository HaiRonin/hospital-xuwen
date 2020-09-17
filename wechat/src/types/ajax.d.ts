// import {AxiosResponse} from 'axios';
declare module '*.vue' {}

interface IApiServer extends IOBJ {
    baseApi: string;
}

interface IMyResponse extends IOBJ {
    /**
     * 状态码
     */
    resultCode: string;
    resultMsg: string;
}

interface IMyOptions {
    method?: string;
    action?: 'json' | 'formData';
    params?: IOBJ;
    /**
     * 不取消之前同接口的请求
     * true 不取消， false 取消
     */
    noCloseBeforeAjax?: boolean | false;
    /**
     * 是否读取缓存
     * 默认 false
     */
    isRCache?: boolean | false;
    /**
     * 缓存有效时间
     */
    validityTime?: number | 5,
    /**
     * 目标域名
     */
    targetDomain?: string;
    /**
     * 加载框
     */
    isLoad?: boolean | false;
    /**
     * 关闭错误提示
     */
    closeErrorTips?: boolean | false;
}

interface IMyRejectObj {
    /**
     * 失败回调类型
     */
    type: 'thenError' | 'catchError';
    /**
     * 错误的相关信息
     */
    data: IOBJ;
    /**
     * thenError 回调里才有这参数
     */
    oldRes?: IOBJ
    // oldRes?: AxiosResponse
}

interface IMyCacheData {
    [key: string]: IOBJ | number;
    count: number;
}

type IMyPromiseAjax = (params: IOBJ, options: IMyOptions) => Promise<IOBJ>;
type TApi = (params?: IOBJ, options?: IMyOptions) => Promise<IOBJ>;

type TMyGet = (url: string, params: IOBJ, options: IMyOptions) => Promise<IOBJ>;
type TMyAjax = (url: string, params?: IOBJ, options?: IMyOptions) => Promise<IMyResponse>;

declare module ajax {
    const apiPost: TMyAjax;
    const apiPut: TMyAjax;
    const apiDel: TMyAjax;
    const apiGet: TMyAjax;
    const domain: IApiServer;
}
