<template>
    <view class="">
        <view class="flex-box align-center top-box">
            <view
                v-for="(item, index) in sort"
                :key="index"
                class="flex-1 tb-text"
                :class="{cur: index === sortIndex}"
                @tap="change(index)"
            >{{item.name}}</view>
        </view>

        <view class="common-block rel" v-for="(item, index) in list" :key="index" @tap="orderDetail.openFun(item)">
            <view class="flex-box align-center justify-s-b item">
                <view class="text-1">下订单时间:</view>
                <view class="text-2 flex-1">{{item.visitDate}}</view>
                <view class="text-2 main-color">{{item.status === '1' ? '已缴费' : '未缴费'}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">就诊科室:</view><view class="text-2">{{item.organName}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">总金额:</view><view class="text-2">{{item.patientAmount}}元</view>
            </view>
            <view class="abs text-3">查看详情</view>
        </view>

        <view style="height:100rpx;"></view>

        <view class="z-btn-box rel" v-if="list.length && sortIndex === 0">
            <view class="red-color zbb-text-1">医院门诊缴费</view>
            <view>合计: <text class="red-color zbb-text-2">{{allAmount}}元</text></view>
            <view class="zbb-btn abs flex-box align-center justify-center" @tap="playPay">支付</view>
        </view>

        <u-empty text="暂无缴纳信息" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !list.length"></u-empty>

        <orderDetail ref="orderDetail"/>
        <pay ref="pay" :request="payRequest" @paySuccess="paySuccess"/>

    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Provide} from 'vue-property-decorator';
    import {queryToPayRecipeInfoList, queryPaymentRecordList, ordeNewPayment} from '@/apis';
    import pay from '@/components/pay.vue';
    import orderDetail from '@/components/orderDetail.vue';

    @Component({
        components: {
            orderDetail,
            pay
        }
    })
    export default class PaymentPrescriptionRecord extends Vue {
        @Ref('orderDetail') readonly orderDetail!: IOBJ;
        @Ref('pay') readonly pay!: IOBJ;
        @Provide('ppr') ppr = this;

        sortIndex = 0;
        oneLoad = true;
        payRequest = ordeNewPayment;
        allAmount: string | number = 0;
        list: IOBJ[] = [];
        options: IOBJ = {};
        sort = [
            {name: '待缴费'},
            {name: '已缴费'},
        ];

        change (index?: number) {
            const sortIndex = this.sortIndex = (utils.zEmpty(index) ? this.sortIndex : index) as number;
            sortIndex === 0 ? this.getList1() : this.getList2();
        }

        paySuccess () {
            console.log('支付成功');
        }

        playPay () {
            // const curItem = this.curItem as IOBJ;
            const data = {
                // payMoney: this.allAmount,
                medicareType: 1,
                patientNo: this.options.patientNo,
                // hiFeeNos: curItem.hiFeeNo,
            };
            this.pay.startPay(data);
        }

        async getList1 () {
            this.list = [];
            this.oneLoad = true;
            const data = {
                patientNo: this.options.patientNo,
                medicareType: 1
            };
            const res = await queryToPayRecipeInfoList(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.allAmount = utils.toFixed(res.data.reduce((total: number, item: IOBJ) => {
                // debugger;
                return +utils.toFixed(total + +item.settleAmount);
            }, 0));
            this.list = res.data;
            this.oneLoad = false;
        }

        async getList2 () {
            this.list = [];
            this.oneLoad = true;

            const [startDate, endDate] = this.$store.getters.monthDay;
            const data = {
                patientNo: this.options.patientNo,
                startDate,
                endDate
            };
            const res = await queryPaymentRecordList(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.list = res.data;
            this.oneLoad = false;
        }

        onLoad (options: IOBJ) {
            this.options = options;
        }

        created () {
            this.change(0);
            // this.getList1();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .top-box {
        background: #fff;
        line-height: 80rpx;
        font-size: 31rpx;
        text-align: center;
        position:sticky;
        top:0;
        left: 0;
        right: 0;
        box-shadow: 0 2rpx 6rpx 0px $color-weak-grey;
        margin-bottom: 32rpx;
        z-index: 2;
    }
    .top-box .tb-text.cur {
        color: $main-color;
        font-weight: bold;
        background: -webkit-linear-gradient(left, $main-color, $main-color) center
            bottom / 50rpx 5rpx no-repeat;
    }

    .item{
        line-height: 60rpx;
        font-size: 26rpx;
    }
    .text-1{
        color:$color-grey;
        min-width: 180rpx;
        text-align: right;
        padding-right: 30rpx;
    }
    .text-3{
        right: 16rpx;
        bottom: 24rpx;
        color: $color-grey;
        // background: $main-color;
        background: #fff;
        padding: 8rpx 12rpx;
        border-radius: 10rpx;
        border:$border-line;
    }

    .z-btn-box{
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        font-size: 32rpx;
        line-height: 40rpx;
        height: 100rpx;
        box-shadow: 0 -2rpx 6rpx 0px $color-weak-grey;
        padding: 10rpx 30rpx;
        background: #fff;
        z-index: 2;
    }

    .zbb-text-1{
        font-size: 28rpx;
    }

    .zbb-text-2{
        margin-left: 10rpx;
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
</style>
