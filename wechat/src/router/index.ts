import Vue from 'vue';
import VueRouter, {RouteConfig, NavigationGuard} from 'vue-router';
import {beforeEach} from './intercept';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
    {
        path: '/',
        component: () => import('@pages/home/index.vue'),
        name: 'homeIndex',
        redirect: '/outpatient',
        children: [
            {
                path: '/outpatient',
                component: () => import('@pages/home/outpatient.vue'),
                name: 'homeOutpatient',
                meta: {title: '门诊'}
            },
            {
                path: '/inHospital',
                component: () => import('@pages/home/inHospital.vue'),
                name: 'homeInHospital',
                meta: {title: '住院'}
            },
            {
                path: '/my',
                component: () => import('@pages/home/my.vue'),
                name: 'homeMy',
                meta: {title: '我的'}
            }
        ]
    },
    {
        path: '/outpatient/intelligentGuidance/index',
        component: () => import('@pages/outpatient/intelligentGuidance/index.vue'),
        name: 'outpatientIntelligentGuidanceIndex',
        meta: {title: '智能导诊'},
    },
    {
        path: '/registration/diagnosis',
        component: () => import('@pages/registration/diagnosis.vue'),
        name: 'registrationDiagnosis',
        meta: {title: '诊断'},
    },
    {
        path: '/registration/departmentDoctorList',
        component: () => import('@pages/registration/departmentDoctorList.vue'),
        name: 'registrationDepartmentDoctorList',
        meta: {title: '医生列表'},
    },
    {
        path: '/registration/doctor/:departmentorganId',
        component: () => import('@pages/registration/doctor.vue'),
        name: 'registrationDoctor',
        meta: {title: '列表'},
        props: true
    },
    {
        path: '/info/index',
        component: () => import('@pages/info/index.vue'),
        name: 'infoIndex',
        meta: {title: '信息查询'},
    },
    {
        path: '/info/drugSearch',
        component: () => import('@pages/info/drugSearch.vue'),
        name: 'infoDrugSearch',
        meta: {title: '药品/非药品查询'},
    },
    {
        path: '/info/expertIntroduction',
        component: () => import('@pages/info/expertIntroduction.vue'),
        name: 'infoExpertIntroduction',
        meta: {title: '专家介绍'},
    },
    {
        path: '/info/hospitalntroduction',
        component: () => import('@pages/info/hospitalntroduction.vue'),
        name: 'infoHospitalntroduction',
        meta: {title: '医院简介'},
    },
    {
        path: '/info/departmentIsIntroduced',
        component: () => import('@pages/info/departmentIsIntroduced.vue'),
        name: 'infoDepartmentIsIntroduced',
        meta: {title: '科室介绍'},
    },
];

const router = new VueRouter({routes});

router.beforeEach(beforeEach);

export default router;
