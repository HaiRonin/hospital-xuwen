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
                <view class="text-1">类型:</view><view class="text-2">{{item.organName}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">总金额:</view><view class="text-2">{{item.patientAmount}}元</view>
            </view>
            <view class="abs text-3">查看详情</view>
        </view>

        <u-empty text="暂无缴纳信息" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !list.length"></u-empty>

        <orderDetail ref="orderDetail"/>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Provide} from 'vue-property-decorator';
    import {queryToPayRecipeInfoList, queryPaymentRecordList} from '@/apis';
    import orderDetail from '@/components/orderDetail.vue';

    @Component({
        components: {
            orderDetail
        }
    })
    export default class PaymentPrescriptionRecord extends Vue {
        @Ref('orderDetail') readonly orderDetail!: IOBJ;
        @Provide('ppr') ppr = this;

        sortIndex = 0;
        oneLoad = true;
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

        async getList1 () {
            this.list = [];
            this.oneLoad = true;
            const data = {
                patientNo: this.options.patientNo,
                medicareType: 1
            };
            const res = await queryToPayRecipeInfoList(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

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
</style>
