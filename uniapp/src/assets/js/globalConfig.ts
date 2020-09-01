
// 首页的几个路径
export const homeUrl = [
    '/pages/home/outpatient',
    '/pages/home/inHospital',
    '/pages/home/my',
];

// 强制登录，白名单
export const whiteList = [
    '/pages/login',
    '/pages/home/outpatient',

    '/pages/info/index',
    '/pages/info/drugSearch',
    '/pages/info/expertIntroduction',
    '/pages/info/hospitalntroduction',
    '/pages/info/departmentIsIntroduced',

    '/pages/registration/index',
    '/pages/registration/departmentDoctorList/index',
    '/pages/registration/doctor'
];

// 挂号的时间段
export const sourceTimeType = [
    {text: '上午', value: 1},
    {text: '中午', value: 2},
    {text: '下午', value: 3},
    {text: '晚上', value: 4},
    {text: '凌晨', value: 5},
];
