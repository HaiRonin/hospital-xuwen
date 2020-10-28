<template>
    <u-popup
        v-model="show"
        mode="bottom"
        closeable
        border-radius="14"
    >
        <view class="" v-if="curItem">
            <scroll-view class="content" scroll-y>
                <view class="flex-box align-center flex-wrap">
                    <view class="flex-100">
                        <text class="text-1">缴费编号:</text>
                        <text class="text-2">{{curItem.hiFeeNo}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">收费员名称:</text>
                        <text class="text-2">{{curItem.organdoctorId}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">患者编号:</text>
                        <text class="text-2">{{curItem.patientNo}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">患者名称:</text>
                        <text class="text-2">{{curItem.patientName}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">就诊科室名:</text>
                        <text class="text-2">{{curItem.organName}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">挂号医生名称:</text>
                        <text class="text-2">{{curItem.doctorName}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">门诊流水号:</text>
                        <text class="text-2">{{curItem.serialNumber}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">打印状态:</text>
                        <text class="text-2">{{curItem.isPrint === '1' ? '已打印' : '未打印'}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">结算金额:</text>
                        <text class="text-2">{{curItem.settleAmount}}元</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">自费金额:</text>
                        <text class="text-2">{{curItem.patientAmount}}元</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">就诊日期:</text>
                        <text class="text-2">{{curItem.visitDate}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">订单创建时间:</text>
                        <text class="text-2">{{curItem.createTime}}</text>
                    </view>
                </view>
                <view class="z-title">清单</view>
                <view class="flex-box align-center flex-wrap child-box" v-for="(item, index) in curItem.hiFeeItem" :key="index">
                    <view class="flex-100">
                        <text class="text-3">名称:</text>
                        <text class="text-4">{{item.feeItemName}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-3">单价:</text>
                        <text class="text-4">{{item.feeItemAmount}}元</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-3">数量:</text>
                        <text class="text-4">{{item.feeItemNum}}{{item.feeItemUnit}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-3">规格:</text>
                        <text class="text-4">{{item.feeItemStandard}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-3">总价:</text>
                        <text class="text-4">{{item.feeItemAllAmount}}元</text>
                    </view>
                </view>
            </scroll-view>
            <view class="z-btn-box rel" v-if="showBtn">
                <view class="red-color zbb-text-1">医院门诊缴费</view>
                <view>合计: <text class="red-color zbb-text-2">{{curItem.settleAmount}}元</text></view>
                <view class="zbb-btn abs flex-box align-center justify-center" @tap="playPay">支付</view>
            </view>
        </view>

        <pay ref="pay" :request="payRequest" @paySuccess="paySuccess"/>
    </u-popup>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Inject} from 'vue-property-decorator';
    import pay from '@/components/pay.vue';
    import {ordeNewPayment} from '@/apis';

    @Component({
        components: {
            pay
        }
    })
    export default class OrderDetail extends Vue {
        @Ref('pay') readonly pay!: IOBJ;
        @Inject('ppr') readonly ppr!: IOBJ;

        show = false;
        showBtn = false;
        curItem: IOBJ | null = null;
        payRequest = ordeNewPayment;

        openFun (item: IOBJ) {
            this.curItem = item;
            // this.showBtn = this.curItem.status === '0';
            this.show = true;
        }

        playPay () {
            const curItem = this.curItem as IOBJ;
            const data = {
                payMoney: curItem.settleAmount,
                medicareType: 1,
                hiFeeNos: curItem.hiFeeNo,
            };
            this.pay.startPay(data);
        }

        paySuccess () {
            this.show = false;
            this.ppr.change();
        }

        created () {
            // console.log(this);
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content{
        // padding: 0 40rpx;
        height: 80vh;
        overflow: auto;
        font-size: 28rpx;
        line-height: 70rpx;
        padding: 30rpx 20rpx;
    }

    .text-1{color: $color-grey;width: 200rpx;display: inline-block;text-align: right;padding-right: 20rpx;}
    .text-2{}

    .z-title{
        border-bottom: 0;
        border-top: $border-line;
        padding: 0;
    }

    .child-box{border:$border-line;}

    .child-box + .child-box{margin-top: 32rpx;}

    .text-3{color: $color-grey;width: 100rpx;display: inline-block;text-align: right;padding-right: 20rpx;}

    .z-btn-box{
        position: sticky;
        bottom: 0;
        left: 0;
        right: 0;
        font-size: 32rpx;
        line-height: 46rpx;
        box-shadow: 0 -2rpx 6rpx 0px $color-weak-grey;
        padding: 10rpx 30rpx;
    }

    .zbb-text-1{
        font-size: 28rpx;
    }

    .zbb-btn{
        top: 0rpx;
        right: 0;
        bottom: 0;
        background: $main-color;
        width: 280rpx;
        font-size: 40rpx;
        color: #fff;
        box-shadow: 0 -2rpx 6rpx 0px $color-weak-grey;
    }

    .flex-50{flex-basis: 50%;}
    .flex-100{flex-basis: 100%;}
</style>
