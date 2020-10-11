<template>
    <view class="box">

        <view style="height:40rpx;"></view>
        <template v-if="payList && payList.length">
            <view class="common-block rel" v-for="(item, index) in payList" :key="index">
                <view class="flex-box align-center item">
                    <view class="text-1">患者姓名:</view><view class="text-2 main-color">{{item.patientName}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">科室名称:</view><view class="text-2">{{item.departmentName}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">床号:</view><view class="text-2">{{item.bedNo}}</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">押金余额:</view><view class="text-2">{{item.depositMoney}}元</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">补缴上限:</view><view class="text-2">{{item.uppeLimit}}元</view>
                </view>
                <view class="flex-box align-center item">
                    <view class="text-1">补缴下限:</view><view class="text-2">{{item.lowerLimit}}元</view>
                </view>
                <view class="text-3 abs">待缴费</view>
                <view class="btn abs" @tap="payFees">缴费</view>
            </view>
        </template>

        <view style="height:1px;"></view>
        <u-empty text="暂无缴纳数据" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !payList.length"></u-empty>

        <pay ref="pay" :request="payRequest" @paySuccess="paySuccess"/>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import pay from '@/components/pay.vue';
    import {queryPatientInHosInfo, queryArrears, ordeNewPayment} from '@/apis';

    @Component({
        // filters: {
        //     f_payType: (val: any) => globalConfig.gFilter(val, globalConfig.payType),
        // }
        components: {
            pay
        }
    })
    export default class DepositHospitalList extends Vue {
        @Ref('pay') readonly pay!: IOBJ;

        options: IOBJ = {};
        payList: IOBJ[] = [];
        oneLoad = true;
        payRequest = ordeNewPayment;

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

            const res = await queryArrears(
                {
                    inHosNo: hosInfoItem.inHosNo,
                    bedNo: '',
                    departmentorganId: '',
                },
                {closeErrorTips: true}
            ).catch(() => ({data: []}));


            this.payList = res.data;
            this.oneLoad = false;
            utils.hideLoad();
        }

        payFees () {
            const data = {};
            this.pay.startPay(data);
        }

        paySuccess () {
            this.getData();
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

    .text-3{
        right: 24rpx;
        top: 24rpx;
        color: $color-grey;
    }

    .btn{
        bottom: 24rpx;
        right: 24rpx;
        // border:$border-line;
        color: #fff;
        border-radius: 10rpx;
        background: $main-color;
    }
</style>
