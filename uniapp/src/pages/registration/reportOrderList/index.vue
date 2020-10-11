<template>
    <view class="box">
        <!-- <view class="top-line"></view> -->
        <view class="common-block" v-for="(item, index) in list" :key="index">
            <view class="flex-box align-center justify-s-b item title-line">
                <view class="">科室:{{item.LocationName}}</view>
                <view class="">{{item.regStatus | f_regStatus}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">预约日期:</view>
                <view class="text-2 main-color">{{item.sourceDate}} {{item.timestypeNo}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">医生:</view>
                <view class="text-2">{{item.DoctorName}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">支付:</view>
                <view class="text-2">{{item.payType | f_payType}}-{{item.consultationFee}}元</view>
            </view>
            <!-- <view class="flex-box align-center item">
                <view class="text-1">费用:</view>
                <view class="text-2">{{item.consultationFee}}元</view>
            </view> -->
            <view class="flex-box align-center item">
                <view class="text-1">流水号:</view>
                <view class="text-2">{{item.sourceMark}}</view>
            </view>
            <view class="flex-box align-center justify-s-b item btn-line" v-if="item.IsPreReg !== '非预约号' || item.regStatus === '1'">
                <view></view>
                <view class="close z-btn flex-box align-center" @tap="closeReport.openFun(item)">
                    <u-icon name="close-circle" class="item-icon"></u-icon>
                    取消预约
                </view>
                <!-- <view class="query z-btn flex-box align-center" @tap="lookQueue.openFun(item)">
                    <u-icon name="account" class="item-icon"></u-icon>
                    排队候诊查询
                </view> -->
            </view>
        </view>

        <u-empty v-if="!list.length && !oneLoad" text="暂无挂号数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>

        <closeReport ref="closeReport"/>
        <lookQueue ref="lookQueue"/>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Provide} from 'vue-property-decorator';
    import {queryReg} from '@/apis';
    import closeReport from './childAction/closeReport.vue';
    import lookQueue from './childAction/lookQueue.vue';

    @Component({
        components: {
            closeReport,
            lookQueue
        },
        filters: {
            f_regStatus: (val: any) => globalConfig.gFilter(val, globalConfig.regStatus),
            f_payType: (val: any) => globalConfig.gFilter(val, globalConfig.payType),

        }
    })
    export default class ReportOrderList extends Vue {
        @Ref('closeReport') readonly closeReport!: IOBJ;
        @Ref('lookQueue') readonly lookQueue!: IOBJ;
        @Provide('reportOrderList') reportOrderList = this;

        options: IOBJ = {};
        params: IOBJ = {};
        list: IOBJ[] = [];
        oneLoad = true;

        async getList () {
            const data = this.params;
            const res = await queryReg(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.list = res.data;
            this.oneLoad = false;
        }

        onPullDownRefresh () {
            utils.pullDown(() => this.getList());
        }

        onLoad (options: IOBJ) {
            this.options = options;
            this.params.CardNo = options.patientNo;
        }

        created () {
            this.getList();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box {
        // background: #fff;
        padding-bottom: 20rpx;
        min-height: 100%;
        padding-top: 32rpx;
    }

    .title-line.item{
        border-bottom: $border-line;
        padding-bottom: 10rpx;
        margin-bottom: 10rpx;
        color: $default-color;
    }

    .item{
        line-height: 60rpx;
        color: $color-grey;
    }

    .text-1{
        width: 130rpx;
        // text-align: right;
        margin-right: 20rpx;
    }

    .btn-line{
        border-top: $border-line;
        // padding-top: 10rpx;
        margin-top: 10rpx;
    }

    .z-btn{
        padding-top: 20rpx;
        font-size: 30rpx;
    }

    .item-icon{
        margin-right: 10rpx;
    }

    .close{
        color:$main-error-color;
    }

    .query{
        color:$main-color;
    }
</style>
