<template>
    <view class="box">
        <topSort v-model="modalShow" @commit="getData" ref="topSort" class="top-sort" />

        <view class="common-block" v-for="(item, index) in list" :key="index">
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">门诊流水号:</view>
                <view class="text-2">{{item.serialNumber}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">缴费编号(发票号):</view>
                <view class="text-2">{{item.hiFeeNo}}</view>
            </view>
            <view class="flex-box justify-s-b">
                <view class="text-1">医生名字:</view>
                <view class="text-2">{{item.doctorName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">就诊科室名:</view>
                <view class="text-2">{{item.organName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">挂号编号:</view>
                <view class="text-2">{{item.reservation}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">就诊日期:</view>
                <view class="text-2">{{item.visitDate}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">患者类型:</view>
                <view class="text-2">{{item.patientType}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">患者姓名:</view>
                <view class="text-2">{{item.patientName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">处方分类:</view>
                <view class="text-2">{{item.prescriptions}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">收费方式:</view>
                <view class="text-2">{{item.payName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">自费金额:</view>
                <view class="text-2">{{item.patientAmount}}元</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">结算金额:</view>
                <view class="text-2">{{item.settleAmount}}元</view>
            </view>
            <!-- <div v-for="(child, cindex) in item.hiFeeItem" class="child-block" :key="index + '' + cindex + '' + 1">
                <view class="flex-box justify-s-b" >
                    <view class="text-1">项目编号:</view>
                    <view class="text-2">{{child.feeItemCode}}</view>
                </view>
                <view class="flex-box justify-s-b">
                    <view class="text-1">名称:</view>
                    <view class="text-2">{{child.feeItemName}}</view>
                </view>
                <view class="flex-box justify-s-b">
                    <view class="text-1">规格:</view>
                    <view class="text-2">{{child.feeItemStandard}}</view>
                </view>
                <view class="flex-box justify-s-b">
                    <view class="text-1">数量:</view>
                    <view class="text-2">{{child.feeItemNum}}{{child.feeItemUnit}}</view>
                </view>
                <view class="flex-box justify-s-b">
                    <view class="text-1">总价:</view>
                    <view class="text-2">{{child.feeItemAllAmount || 0}}元</view>
                </view>
            </div> -->
            <view class="scroll-box">
                <scroll-view scroll-x class="th-content-box" :scroll-left="item.headLeft" scroll-anchoring>
                    <view  class="th-item flex-box">
                        <view class="th-col th-col-1">医院服务项目编号</view>
                        <view class="th-col th-col-2">医疗服务项目、药品或一次性医用耗材名称</view>
                        <view class="th-col">规格</view>
                        <view class="th-col">计价单位</view>
                        <view class="th-col">单价</view>
                        <view class="th-col">数量</view>
                        <view class="th-col">金额</view>
                    </view>
                </scroll-view>
                <scroll-view scroll-y scroll-x class="td-content-box" scroll-anchoring @scroll="contentBox($event, item)">
                    <view class="td-item flex-box" v-for="(child, cindex) in item.hiFeeItem" :key="cindex">
                        <view class="td-col td-col-1">{{child.feeItemCode}}</view>
                        <view class="td-col td-col-2">{{child.feeItemName}}</view>
                        <view class="td-col">{{child.feeItemStandard}}</view>
                        <view class="td-col">{{child.feeItemUnit}}</view>
                        <view class="td-col">{{child.feeItemAmoun}}</view>
                        <view class="td-col">{{child.feeItemNum}}</view>
                        <view class="td-col">{{child.feeItemAllAmount}}</view>
                    </view>
                </scroll-view>
            </view>
            <view class="text-right">合计：{{item.settleAmount}}</view>
        </view>

        <u-empty v-if="!modalShow && !list.length && !oneLoad" text="暂无门诊清单数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import topSort from './topSort.vue';
    import {printlist} from '@/apis';

    @Component({
        components: {
            topSort
        },
        filters: {
            f_reportStatus: (val: any) => globalConfig.gFilter(val, globalConfig.reportStatus),
            f_reportType: (val: any) => globalConfig.gFilter(val, globalConfig.reportType),
        }
    })
    export default class Index extends Vue {
        @Ref('topSort') readonly topSort!: IOBJ;

        modalShow = true;
        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};

        contentBox (e: IOBJ, item: IOBJ) {
            // console.log(e.detail.scrollLeft)
            // item.headLeft = e.detail.scrollLeft;
            this.$set(item, 'headLeft', e.detail.scrollLeft);
        }

        async getData (data: IOBJ) {
            console.log(data);
            if (!data) return;
            Object.assign(data, this.params);
            const res = await printlist(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            // res.data.forEach((item: IOBJ) => {
            //     item.reportDate = (item.reportDate || '').replace(/\//g, '-');
            //     if (item.contentpicsrc) {
            //         const arr = item.contentpicsrc.split('/');
            //         item.contentpicsrcText = arr[arr.length - 1];
            //     }
            // });

            this.oneLoad = false;
            this.list = res.data;
        }

        onLoad (options: IOBJ) {
            this.params.patientNo = options.patientNo;
            this.options = options;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    $newBorder:2rpx solid #333;

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
        z-index: 2;
    }

    .common-block{
        line-height: 60rpx;
    }

    .text-1{
        color:$color-grey;
        padding-right: 20rpx;
        min-width: 180rpx;
    }

    .text-2{text-align: right;}

    .check-name{line-height: 40rpx;padding-bottom: 10rpx;}

    .line{
        border-top:$border-line;
        margin-top: 26rpx;
    }

    .child-block{
        border:$border-line;
        margin: 10rpx;
        padding: 10rpx;
        border-radius: 6rpx;
        line-height: 1.5;
        font-size: 24rpx;
    }

    .th-item, .td-item{
        // width: 1260rpx;
        // border-left:$newBorder;
        white-space: nowrap;
        font-size: 24rpx;
    }

    .th-col, .td-col{
        // width: 180rpx;
        // display: inline-block;
        white-space: initial;
        min-width: 180rpx;
        border-bottom:$newBorder;
        border-right:$newBorder;
        line-height: 1.2;
        padding: 6rpx 8rpx;
        text-align: center;
        background: #e5e5e5;
        padding-top: 22rpx;
        font-weight: bold;
    }

    .th-col-1, .td-col-1{
        min-width: 240rpx;
    }

    .th-col-2, .td-col-2{
        min-width: 280rpx;
        padding-top: 6rpx;
    }

    .th-item:first-child .th-col{
        border-top:$newBorder;
    }

    .td-col{
        background: #fff;
        font-weight: initial;
        padding: 10rpx 6rpx;
    }

    .td-content-box{
        max-height: 500rpx;
    }

    .th-content-box::v-deep .uni-scroll-view{
        overflow: hidden !important;
    }

    .td-item:last-child .td-col{
        border-bottom:none;
    }

    .td-item .td-col:last-child, .th-item .th-col:last-child{
        border-right:none;
    }

    .td-item .td-col:first-child, .th-item .th-col:first-child{
        border-left:none;
    }

    .scroll-box{
        border-right:$newBorder;
        border-bottom:$newBorder;
        border-left:$newBorder;
    }

    .text-right{text-align: right;}
</style>
