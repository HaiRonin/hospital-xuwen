<template>
    <view class="context">
        <template v-if="info">
            <view class="flex-box align-center justify-s-b item" v-for="(item, index) in list" :key="index">
                <text class="text-1">{{item.text}}</text>
                <text class="text-2">{{item.value}}</text>
            </view>
        </template>

        <view style="padding-top:1px;"></view>
        <u-empty v-if="!oneLoad && !info" text="暂无住院信息" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryPatientInHosInfo} from '@/apis';

    @Component
    export default class HospitalInfo extends Vue {

        oneLoad = true;
        info: IOBJ | null = null;
        options: IOBJ = {};
        list = [
            {text: '患者姓名', value: '', key: 'patientName'},
            {text: '性别', value: '', key: 'patientSex'},
            {text: '身份证号', value: '', key: 'cardNo'},
            {text: '住院日期', value: '', key: 'inHosDate'},
            {text: '科室名称', value: '', key: 'departmentName'},
            {text: '是否住院', value: '', key: 'inHosState'},
            {text: '床号', value: '', key: 'bedNo'},
            {text: '医保类型代码', value: '', key: 'medicalTypeCode'},
            {text: '医保类型名称', value: '', key: 'medicalTypeName'},
            {text: '医保统筹金额', value: '', key: 'overMoney'},
            {text: '医保记账自费金额', value: '', key: 'payMoney'},
            {text: '自费金额', value: '', key: 'cashMoney'},
            {text: '押金余额', value: '', key: 'depositMoney'},
            {text: '总费用', value: '', key: 'totalMoney'},
        ];

        async getData () {
            const {patientNo, idCardno} = this.options;

            const res1 = await queryPatientInHosInfo(
                {cardNo: idCardno, visitCardNo: patientNo},
                {closeErrorTips: true, isLoad: true}
            ).catch(() => ({data: []}));

            const hosInfoList = res1.data || [];
            const list = this.list;
            const info = hosInfoList[hosInfoList.length - 1];

            if (info) {
                const {inHosState, sexState, gFilter} = globalConfig;
                list.forEach((item) => {
                    if (item.key === 'patientSex') {
                        info[item.key] = gFilter(info[item.key], sexState);
                    } else if (item.key === 'inHosState') {
                        info[item.key] = gFilter(info[item.key], inHosState);
                    } else if (['totalMoney', 'depositMoney', 'cashMoney', 'payMoney', 'overMoney'].includes(item.key)) {
                        info[item.key] = utils.toFixed(info[item.key]) + '元';
                    }
                    item.value = info[item.key];
                });
            }

            this.info = info;
            this.oneLoad = false;
        }

        onLoad (options: IOBJ) {
            this.options = options;
        }

        created () {
            this.getData();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .context{
        padding: 0 24rpx;
        min-height: 100%;
        background: #fff;
    }

    .item{font-size: 30rpx;line-height: 90rpx;padding: 0 10rpx;}

    .text-1{color: $color-grey;font-size: 30rpx;}

    .item + .item{border-top: $border-line;}
</style>
