
// 公众号id
export const APPID = 'wx433d96c6e8746383';

export const BUILD_DOMAIN = {
    baseApi: 'http://www.xuwenxianrenminyiyuan.cn',
    webUrl: 'http://www.xuwenxianrenminyiyuan.cn',
};

// 首页的几个路径
export const homeUrl = [
    '/pages/home/outpatient',
    '/pages/home/inHospital',
    '/pages/home/my',
];

// 进入这几个路径需要刷新
export const refreshUrl = [
    '/pages/registration/confirmOrder',
    '/pages/registration/paymentPrescriptionRecord',
    '/pages/registration/depositHospitalPay',
];

// 强制登录，白名单
export const whiteList = [
    '/pages/login',
    '/pages/wv',
    '/pages/wvCallBack',
    '/pages/home/outpatient',

    '/pages/outpatient/intelligentGuidance',
    '/pages/outpatient/intelligentGuidance/index',

    '/pages/info/index',
    '/pages/info/drugSearch',
    '/pages/info/expertIntroduction',
    '/pages/info/hospitalntroduction',
    '/pages/info/departmentIsIntroduced',
    '/pages/info/doctorTimeing',

    '/pages/registration/diagnosis',
    '/pages/registration/index',
    '/pages/registration/departmentDoctorList/index',
    '/pages/registration/doctor',
    '/pages/registration/searchDoctor',
    '/pages/registration/confirmOrder',
];

// 过滤数据用
export const gFilter = (val: string, list: any[], key = 'value') => {
    const item = list.find((ii: IOBJ) => ii[key].toString() === `${val}`);
    return (item && item.text) || '';
};

// 挂号的时间段
export const sourceTimeType = [
    {text: '上午', value: '1'},
    {text: '中午', value: '2'},
    {text: '下午', value: '3'},
    {text: '晚上', value: '4'},
    {text: '凌晨', value: '5'},
];

// 报告的各种类型
export const reportType = [
    {value: '0', text: '检验'},
    {value: '1', text: 'CT'},
    {value: '2', text: 'DR'},
    {value: '3', text: 'MR'},
    {value: '4', text: 'CR'},
    {value: '5', text: 'RF'},
    {value: '6', text: 'US'},
    {value: '7', text: '脑电图'},
    {value: '8', text: '心电图'},
    {value: '9', text: '镜检查'},
    {value: '10', text: 'MG'},
    {value: '11', text: 'DSA'},
    {value: '12', text: '普放'},
    {value: '13', text: '病理'},
    {value: '14', text: '其它'},
];

// 报告的各种类型2
export const reportType2 = [
    {value: '1', text: '检验'},
    {value: '2', text: 'B超'},
    {value: '3', text: '放射影像'},
    {value: '4', text: '内镜'},
    {value: '5', text: '病理'},
    {value: '6', text: '心电图'},
];

// 报告的各种状态
export const reportStatus = [
    {value: '0', text: '未出报告'},
    {value: '1', text: '已出报告'},
];

// 挂号状态
export const regStatus = [
    {value: '1', text: '预约中'},
    {value: '2', text: '已取消'},
    {value: '3', text: '已就诊'},
    {value: '4', text: '已过期'},
];

// 支付状态
export const payType = [
    {value: '1', text: '银联'},
    {value: '2', text: '支付宝'},
    {value: '3', text: '现场支付'},
    {value: '4', text: '医保账户'},
    {value: '5', text: '微信'},
    {value: '6', text: '云医院微信'},
    {value: '7', text: '云医院支付宝'},
    {value: '8', text: '诊疗卡'},
    {value: '10', text: '微信公众号'},
];

export const inHosState = [
    {value: '0', text: '无住院'},
    {value: '1', text: '申请中'},
    {value: '2', text: '入院中'},
    {value: '3', text: '已出院'},
];

export const sexState = [
    {value: '1', text: '男'},
    {value: '0', text: '女'},
    // {value: '2', text: '其他'},
];
