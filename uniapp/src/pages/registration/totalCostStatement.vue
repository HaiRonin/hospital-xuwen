<template>
    <view class="box">
        <view class="price-box" v-if="info">
            <view class="flex-box align-center justify-s-b p-item">
                <view class="p-text-1">总费用:</view>
                <view class="p-text-2">{{info.totalMoney}}元</view>
            </view>
            <view class="flex-box align-center justify-s-b p-item">
                <view class="p-text-1">住院流水号:</view>
                <view class="p-text-2">{{info.inHosSerialNo}}</view>
            </view>
            <view class="flex-box align-center justify-s-b p-item">
                <view class="p-text-1">医保类型(代码-名称):</view>
                <view class="p-text-2">{{info.medicalTypeCode}}-{{info.medicalTypeName}}</view>
            </view>
        </view>

        <view style="height:40rpx;"></view>
        <template v-if="inHosList && inHosList.length">
            <view class="common-block" v-for="(item, index) in inHosList" :key="index">
                <view class="flex-box align-center item">
                    <view class="text-1">项目编号:</view><view class="text-2">{{item.projectCode}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">项目名称:</view><view class="text-2">{{item.projectName}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">药品分类:</view><view class="text-2">{{item.drugType}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">规格:</view><view class="text-2 main-color">{{item.feeItemNum}}{{item.feeItemUnit}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">单价:</view><view class="text-2">{{item.feeItemAmount}}元</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">总价:</view><view class="text-2">{{item.feeItemAllAmount}}元</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">比例:</view><view class="text-2">{{item.amountMoney}}</view>
                </view>
            </view>
        </template>

        <view style="height:1px;"></view>
        <u-empty text="暂无住院信息" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !info"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryPatientInHosInfo, queryLeaveHosDetail} from '@/apis';

    @Component
    export default class TotalCostStatement extends Vue {

        options: IOBJ = {};
        inHosList: IOBJ[] = [];
        info: IOBJ | null = null;
        oneLoad = true;
        clearRenderList: TClearRenderList | null = null;

        async getData () {
            // 查信息
            utils.showLoad();
            const {patientNo, idCardno} = this.options;
            const res1 = await queryPatientInHosInfo(
                {cardNo: idCardno, visitCardNo: patientNo},
                {closeErrorTips: true}
            ).catch(() => ({data: []}));

            const hosInfoList = res1.data || [];
            const hosInfoItem = hosInfoList[hosInfoList.length - 1];
            if (utils.zEmpty(hosInfoItem)) {
                this.oneLoad = false;
                utils.hideLoad();
                return;
            }

            // hosInfoItem.inHosNo = '0058014';

            // 获取清单
            const statement = await queryLeaveHosDetail({inHosNo: hosInfoItem.inHosNo}, {closeErrorTips: true}).catch(() => null);

            statement && (statement.totalMoney = utils.toFixed(statement.totalMoney));

            this.info = statement as IOBJ;
            // this.inHosList = [];
            this.clearRenderList = utils.renderList({
                list: this.inHosList,
                // data: [],
                data: statement ? statement.inHosList : [],
                // num: 1,
                // interval: 5000
            });
            this.oneLoad = false;
            utils.hideLoad();
        }

        onLoad (options: IOBJ) {
            this.options = options;
        }

        created () {
            this.getData();
        }

        mounted () {}

        activated () {}

        beforeDestroy () {
            this.clearRenderList && this.clearRenderList();
        }

    }
</script>

<style lang="scss" scoped>
    .box{
        min-height: 100%;
        background: #fff;
    }

    .price-box{
        font-size: 30rpx;
        box-shadow: 0 2rpx 12rpx rgba(100, 101, 102, 0.12);
        padding:0 24rpx;
        position:sticky;
        top: 0;
        left: 0;
        right: 0;
        z-index: 2;
        background: #fff;
    }

    .p-item{
        line-height: 90rpx;
        padding:0 10rpx;
    }

    .p-item + .p-item{
        border-top:$border-line;
    }

    .p-text-1{
        color:$color-grey;
    }

    .item{
        line-height: 46rpx;
        font-size: 26rpx;
    }
    .text-1{
        color:$color-grey;
        min-width: 160rpx;
        text-align: right;
        padding-right: 30rpx;
    }
</style>
