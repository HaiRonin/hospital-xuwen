<template>
    <view class="box">
        <template v-if="info">
            <!-- <topSort v-model="modalShow" @commit="getData" ref="topSort" class="top-sort" /> -->
            <view class="flex-box align-center top-sort" @tap="timeShow = true">
                <view class="text-1 flex-1">
                    <text class="t-s-text">时间范围筛选:</text>
                    <text class="main-color">{{params.startDate}}~{{params.endDate}}</text>
                </view>
                <!-- <view class="text-btn">查询</view> -->
            </view>

            <view class="flex-box align-center flex-wrap common-block" v-for="(item, index) in list" :key="index">
                <view class="flex-100">
                    <text class="text-3">项目名称:</text>
                    <text class="text-4">{{item.projectName}}</text>
                </view>
                <view class="flex-50">
                    <text class="text-3">项目编号:</text>
                    <text class="text-4">{{item.projectCode}}</text>
                </view>
                <view class="flex-50">
                    <text class="text-3">药品分类:</text>
                    <text class="text-4">{{item.drugType}}</text>
                </view>
                <view class="flex-100">
                    <text class="text-3">数量:</text>
                    <text class="text-4 main-color">{{item.feeItemNum}}{{item.feeItemUnit}}</text>
                </view>
                <view class="flex-100">
                    <text class="text-3">规格:</text>
                    <text class="text-4">{{item.feeItemStandard}}</text>
                </view>
                <view class="flex-100">
                    <text class="text-3">单价:</text>
                    <text class="text-4">{{item.feeItemAmount}}元</text>
                </view>
                <view class="flex-100">
                    <text class="text-3">总价:</text>
                    <text class="text-4">{{item.feeItemAllAmount}}元</text>
                </view>
                <view class="flex-100">
                    <text class="text-3">记账日期:</text>
                    <text class="text-4">{{item.chargeDate}}</text>
                </view>
            </view>
        </template>
        <view style="padding-top:1px;"></view>
        <u-empty v-if="!oneLoad && !info" text="暂无住院信息" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
        <u-empty v-if="!oneLoad2 && info && !list.length" text="暂无数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>

        <u-calendar v-model="timeShow"  active-bg-color="#463f3a" range-color="#463f3a" range-bg-color="#bcb8b1" mode="date" @change="timeChange" class="z-calendar"></u-calendar>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryPatientInHosInfo, inHosDetail} from '@/apis';

    @Component
    export default class HospitalizationDayList extends Vue {
        @Ref('topSort') readonly topSort!: IOBJ;

        modalShow = false;
        timeShow = false;
        oneLoad = true;
        oneLoad2 = false;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};
        info: IOBJ | null = null;
        renderData: TClearRenderList | null = null;

        async getData () {
            const {patientNo, idCardno} = this.options;

            const res1 = await queryPatientInHosInfo(
                {cardNo: idCardno, visitCardNo: patientNo},
                {closeErrorTips: true, isLoad: true}
            ).catch(() => ({data: []}));

            const hosInfoList = res1.data || [];

            this.info = hosInfoList[hosInfoList.length - 1];
            this.oneLoad = false;
            this.info && this.getList();
        }

        async getList () {
            this.renderData && this.renderData();
            this.oneLoad2 = true;

            const params = this.params;
            params.inHosNo = this.info!.inHosNo;

            const res: IOBJ = await inHosDetail(params, {isLoad: true, closeErrorTips: true}).catch(() => ({inHosList: []}));

            const list = this.list = [];
            this.renderData = utils.renderList({
                list: list,
                data: res.inHosList
            });

            this.oneLoad2 = false;
        }

        timeChange (item: IOBJ) {
            this.params.startDate = item.result;
            this.params.endDate = item.result;
            this.getList();
        }

        onLoad (options: IOBJ) {
            this.options = options;
        }

        created () {
            const [startDate, endDate] = this.$store.getters.day;
            this.params = {startDate, endDate, patientNo: this.options.patientNo};
            this.getData();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box {
        // background: #fff;
        min-height: 100%;
    }

    .top-sort {
        position: sticky;
        top: 0;
        left: 0;
        right: 0;
        margin-bottom: 32rpx;
        background: #fff;
        font-size: 30rpx;
        z-index: 2;
        line-height: 80rpx;
        padding:0 24rpx;
        box-shadow: 0 2rpx 6rpx 0px $color-weak-grey;
    }

    .z-calendar {
        font-size: 28rpx;
    }

    .t-s-text{margin-right: 20rpx;}


    .common-block{line-height: 56rpx;}
    .text-3{color: $color-grey;width: 150rpx;display: inline-block;text-align: right;padding-right: 20rpx;}

</style>
