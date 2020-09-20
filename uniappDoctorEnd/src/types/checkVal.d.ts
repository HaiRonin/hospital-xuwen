
type TCheckData = {[key: string]: string};
type TCheckAddRuleFn = (val: any, data: IOBJ) => string;

type TCheckReturn = boolean | string | string[];
type TCheckHandleInfo = (arr: TCheckData[]) => TCheckReturn;
type TCheckAddRule = (key: string, fn: TCheckAddRuleFn) => void;

// declare class CheckVal {
//     emptyKey: TCheckData;
//     // new(emptyKey: TCheckData);
//     run(data: IOBJ): TCheckReturn;
//     _addRule: TCheckAddRule;
// }


interface ICheckVal {
    /**
     * 实例化
     *
     * emptyKey 需要校验的key，以及为空的提示
     */
    new(emptyKey?: TCheckData): ICheckValObj;

}

// absoluter ICheckVal {
//     /**
//      * 实例化
//      *
//      * emptyKey 需要校验的key，以及为空的提示
//      */
//     new(emptyKey?: TCheckData): ICheckValObj;

// }

interface ICheckValObj extends IOBJ {
    emptyKey: TCheckData;
    constructor(emptyKey?: TCheckData): ICheckValObj;
    /**
     * 执行校验, 返回提示信息或空字符串
     * @param data 校验对象
     */
    run(data: IOBJ): TCheckReturn;

    /**
     * 通过改写该函数可以返回不同值
     */
    handleInfo: TCheckHandleInfo;

    /**
     * 添加检验规则
     * @param key 要检验的key
     * @param fn 执行的函数，传入该key 的值，返回错误文本
     */
    _addRule: TCheckAddRule;

    /**
     * 检验银行卡
     * 可以重写该函数
     *      check.bankCard = (...) => ...
     *      check.bankCard2 = check.bankCard;
     * @param val 卡号
     */
    bankCard(val: string): string;

    /**
     * 检验手机号
     * @param val 卡号
     */
    phone(val: string): string;

    /**
     * 检验身份证
     * @param val 身份证号
     */
    idCard(val: string): string;
}


