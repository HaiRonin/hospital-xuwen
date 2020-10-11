<template>
    <view class="box">
        <view class="price-box" v-if="payList && payList.length">
            <view class="flex-box align-center justify-s-b p-item">
                <view class="p-text-1">总费用:</view>
                <view class="p-text-2">{{totalPrice}}元</view>
            </view>
        </view>

        <view style="height:40rpx;"></view>
        <template v-if="payList && payList.length">
            <view class="common-block" v-for="(item, index) in payList" :key="index">
                <view class="flex-box align-center item">
                    <view class="text-1">住院流水号:</view><view class="text-2 main-color">{{item.inHosSerialNo}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">预缴金金额:</view><view class="text-2">{{item.payMoney}}元</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">发票号:</view><view class="text-2">{{item.invoiceNo}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">支付方式:</view><view class="text-2">{{item.payType | f_payType}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">缴费时间:</view><view class="text-2">{{item.payDate}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">操作员:</view><view class="text-2">{{item.operatorNo}}</view>
                </view>
            </view>
        </template>

        <view style="height:1px;"></view>
        <u-empty text="暂无缴纳信息" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !payList.length"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryPatientInHosInfo, payDetail} from '@/apis';

    @Component({
        filters: {
            f_payType: (val: any) => globalConfig.gFilter(val, globalConfig.payType),
        }
    })
    export default class DepositHospitalList extends Vue {

        options: IOBJ = {};
        payList: IOBJ[] = [];
        totalPrice = 0;
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

            const [startDate, endDate] = this.$store.getters.yearDay;
            // 获取清单
            const res = await payDetail(
                {
                    inHosNo: hosInfoItem.inHosNo,
                    startDate,
                    endDate
                },
                {closeErrorTips: true}
            ).catch(() => ({data: []}));

            // 实验性的
            const createDate = (str: string) => {
                const reg1 = /^(\d{4})[-|/](\d{1,2})[-|/](\d{1,2})\s(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
                const reg2 = /^(\d{4})[-|/](\d{1,2})[-|/](\d{1,2})$/;
                let arr = reg1.exec(str);
                !arr && (arr = reg2.exec(str));
                if (arr === null) {
                    throw `参数时间：${str}，正则匹配不上`;
                }

                const newArr = arr.slice(1);
                newArr.forEach((val, index) => {
                    newArr[index] = val.padStart(2, '0');
                });
                newArr[1] = (+newArr[1] - 1) + '';
                // console.log(newArr);
                return new Date(...newArr as []);
            };

            let totalPrice = 0;
            res.data.forEach((item: IOBJ) => {
                totalPrice = utils.toFixed(totalPrice + item.payMoney, true) as number;

                item.payDate = item.payDate.replace(/\\\//g, '-');
                item.payDate = utils.dateData(createDate(item.payDate)).textTime;
            });

            this.totalPrice = totalPrice;
            this.payList = res.data;
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
        // background: #fff;
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
        min-width: 180rpx;
        text-align: right;
        padding-right: 30rpx;
    }
</style>
