<template>
    <view class="box">

        <view style="height:1px;"></view>
        <u-empty text="暂无住院信息" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !inHotInfo"></u-empty>

        <view class="z-n-box flex-box justify-center f-v align-center" v-if="inHotInfo">
            <view class="text-1">单笔转张金额最低为1元，最高为10000元</view>
            <u-number-box :min="1" :max="10000" :input-width="400" :input-height="80" :size="32" v-model="params.payMoney"></u-number-box>
            <view class="btn z-btn-default z-btn-primary" @tap="payFees">押金缴纳</view>
        </view>

        <pay ref="pay" :request="payRequest" @paySuccess="paySuccess"/>
        <u-modal
            v-model="tipsShow"
            title="温馨提示"
            :show-cancel-button="false"
            :mask-close-able="false"
            ref="modal"
        >
            <view class="tips">
                <view v-for="(str, index) in rule" :key="index">{{str}}</view>
            </view>
        </u-modal>

    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import pay from '@/components/pay.vue';
    import {queryPatientInHosInfo, ordePayment} from '@/apis';

    @Component({
        components: {
            pay
        }
    })
    export default class DepositHospitalList extends Vue {
        @Ref('pay') readonly pay!: IOBJ;

        options: IOBJ = {};
        params: IOBJ = {};
        inHotInfo: IOBJ | null = null;
        oneLoad = true;
        tipsShow = false;
        payRequest = ordePayment;
        rule = [
            '1）出院结账时，住院押金的结账余额请到医院人工窗口办理返还。',
            '2）信用卡恶意透支，将移交公检法机关，请勿以身试法。',
            '3）微信公众号上只能使用微信支付缴费，如需缴纳现金请到医院人工窗口办理。',
            '4）出院结账时，所退金额不能大于所支付的缴费金额。'
        ];

        async getData () {
            // 查信息
            utils.showLoad();
            const {patientNo, idCardno} = this.options;
            const res = await queryPatientInHosInfo(
                {cardNo: idCardno, visitCardNo: patientNo},
                {closeErrorTips: true}
            ).catch(() => ({data: []}));

            const data = res.data[0];

            if (data) {
                this.params = {
                    bedNo: data.bedNo,
                    departmentName: data.departmentName,
                    inHosNo: data.inHosNo,
                    patientName: data.patientName,
                    payMoney: 1,
                    payType: 5,
                };
            }


            this.tipsShow = !!data;
            this.inHotInfo = data;
            this.oneLoad = false;
            utils.hideLoad();
        }

        payFees () {
            this.pay.startPay(this.params);
        }

        paySuccess () {
            // this.getData();
            utils.link(`/pages/registration/depositHospitalList?${utils.serialize(this.options)}`, 1);
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

    .tips{
        line-height: 1.5;
        color: $color-grey;
        padding: 40rpx 20rpx;
        font-size: 32rpx;
        // height: 500rpx;
        // overflow: auto;
    }

    .z-n-box{
        margin:auto;
    }

    .z-n-box::v-deep .u-icon-minus{display: none;}
    .z-n-box::v-deep .u-icon-plus{display: none;}
    .z-n-box::v-deep .u-number-input{
        background: #fff !important;
        border-radius: 12rpx;
    }

    .text-1{
        color: $main-error-color;
        font-size: 30rpx;
        margin: 25% 0 50rpx;
    }

    .btn{
        margin-top: 50rpx;
        font-size: 30rpx;
        border-radius: 12rpx;
        padding: 0 100rpx;
    }
</style>
