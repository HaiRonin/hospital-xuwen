<template>
    <view class>
        <template v-if="info">
            <view style="height:1px;"></view>

            <view class="common-block">
                <view class="flex-box align-center justify-s-b" @tap="linkPatient">
                    <view class="text-1">就诊人</view>
                    <view class="text-2 flex-box align-center">
                        <view v-if="params.patientName">{{params.patientName}}</view>
                        <view v-else class="text-3">请选择</view>
                        <u-icon name="arrow-right" class="item-icon"></u-icon>
                    </view>
                </view>
            </view>

            <view class="common-block flex-box align-center flex-wrap" v-if="info.ItemList">
                <view class="title-1">就诊项目</view>
                <view class="flex-100 flex-box item" v-for="(item, index) in info.ItemList" :key="index">
                    <text class="text-3">{{item.ItemName}}</text>
                    <text class="text-4">{{item.ItemAccount}}元</text>
                </view>
            </view>

            <view style="height:100rpx"></view>
            <view class="z-btn-box rel">
                <view class="red-color zbb-text-1">套餐总金额</view>
                <view>
                    合计:
                    <text class="red-color zbb-text-2">{{info.packageAccount}}元</text>
                </view>
                <view class="zbb-btn abs flex-box align-center justify-center" @tap="commit">支付</view>
            </view>
        </template>

        <u-empty
            v-else-if="!oneLoad && !info"
            text="暂无核酸检测"
            mode="list"
            margin-top="150"
            icon-size="200"
            font-size="36"
        ></u-empty>

        <u-modal
            v-model="paySuccessTips"
            :show-title="false"
            @confirm="paySuccess"
        >
            <view class="title-tips">
                <!-- <view>支付成功</view> -->
                <!-- <view>请到门诊一楼后大厅核酸采样点进行采样，7天内有效</view> -->
                <view>支付成功，请到门诊一楼后大厅核酸采样点进行采样，7天内有效</view>
                <view class="tt-text-2">核酸采样点上班时间：</view>
                <view class="tt-text-2">上午08:00--12：00 </view>
                <view class="tt-text-2">下午14:30--17:30</view>
            </view>
        </u-modal>

        <u-modal
            v-model="titleShow"
            :show-title="false"
            mask-close-able
        >
            <view class="title-tips">
                <view>请注意：</view>
                <view class="tt-text-1">一、门诊核酸检测，面对人群为“愿检尽检”的自费人群，线上完成核算检测缴费。如是非自费人群的，请到现场开单，进行核酸检测。</view>
                <view class="tt-text-1">二、发热人员，健康码为红色或者黄色人员以及来自疫情中、高风险地区人员，请到发热门诊就诊，不要在线上进行核酸检测缴费。</view>
            </view>
        </u-modal>

        <pay ref="pay" :request="payRequest" @paySuccess="paySuccessTips = true" />
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {Action} from 'vuex-class';
    import {searchCovPackage, nucleicAcidPayment} from '@/apis';
    import pay from '@/components/pay.vue';

    @Component({
        components: {
            pay
        }
    })
    export default class NucleicAcid extends Vue {
        @Ref('pay') readonly pay!: IOBJ;
        @Action('actionsIsUse') actionsIsUse!: TActionsIsUse;

        info: IOBJ | null = null;
        params: IOBJ = {};
        oneLoad = true;
        titleShow = false;
        paySuccessTips = false;
        payRequest = nucleicAcidPayment;

        async playQuestionnaire (data: IOBJ) {
            // const actionIsUse = this.actionIsUse;
            // debugger
            const actionIsUse = await this.actionsIsUse('questionnaire');

            // 限制性功能
            if (!actionIsUse) return false;
            // return true;

            const val = utils.getStorage(`questionnaire-${data.patientNo}`);
            const time = Date.now();
            let flag = true;

            // 判断1小时内是否填过，不重复填
            if (!utils.zEmpty(val) && time - +val >= 3600000) {
                flag = true;
            } else if (!utils.zEmpty(val)) {
                flag = false;
            }

            if (flag) {
                const str = utils.serialize({
                    patientName: data.patientName,
                    patientNo: data.patientNo,
                    cardNo: data.cardNo,
                    Mobile: data.Mobile,
                });
                utils.link(`/pages/registration/questionnaire/index?${str}`);
            }

            return flag;
        }

        async commit () {
            const data = utils.jsCopyObj(this.params);
            const info = this.info as IOBJ;

            if (utils.zEmpty(data.patientNo)) {
                utils.toast('请选择就诊人');
                return;
            } else if (await this.playQuestionnaire(data)) {
                return;
            }

            this.pay.startPay({
                name: data.patientName,
                cardNo: data.patientNo,
                payAmount: info.packageAccount
            });
        }

        paySuccess () {
            const {patientNo, patientName, cardNo} = this.params;
            const strData = utils.serialize({
                patientNo,
                cardNo,
                name: patientName,
                type: 1
            });
            utils.link(`/pages/registration/paymentPrescriptionRecord?${strData}`, 1);
        }

        upDataPatient (item: IOBJ) {
            const params = this.params;
            // console.log(item);
            this.$set(params, 'patientNo', item.patientNo);
            this.$set(params, 'patientName', item.patientName);
            this.$set(params, 'cardNo', item.cardNo);
            this.$set(params, 'Mobile', item.oldData.Mobile);
        }

        // 选择就就诊人
        linkPatient () {
            utils.link('/pages/outpatient/index?sel=1');
        }

        async getDetail () {

            const res = await searchCovPackage({}, {isLoad: true}).catch(() => ({data: null}));
            // console.log(res);

            // res.data = JSON.parse(res.data);
            res.data && (this.titleShow = true);
            this.oneLoad = false;
            this.info = res.data;
        }

        // 权限判断
        async init () {
            const isUse = await this.actionsIsUse('nucleicAcid');

            isUse ? this.getDetail() : (this.oneLoad = false);
        }

        onLoad () {
            uni.$on('upDataPatient', this.upDataPatient);
            uni.$on('payCommit', this.commit);
        }

        created () {
            this.commit = utils.throttle(this.commit, 300, 300, true);
            // this.getDetail();
            this.init();
            // console.log(this);
        }

        mounted () {}

        activated () {}

        beforeDestroy () {
            uni.$off('upDataPatient', this.upDataPatient);
            uni.$off('payCommit', this.commit);
        }


    }
</script>

<style lang="scss" scoped>
    .z-btn-box {
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        font-size: 32rpx;
        line-height: 46rpx;
        box-shadow: 0 -2rpx 6rpx 0px $color-weak-grey;
        padding: 10rpx 30rpx;
        background: #fff;
    }

    .zbb-text-1 {
        font-size: 28rpx;
    }

    .zbb-btn {
        top: 0rpx;
        right: 0;
        bottom: 0;
        background: $main-color;
        width: 280rpx;
        font-size: 40rpx;
        color: #fff;
        box-shadow: 0 -2rpx 6rpx 0px $color-weak-grey;
    }


    .flex-100 {
        flex-basis: 100%;
    }

    .common-block{
        margin-top: 34rpx;
    }

    .text-1 {
        color: $color-grey;
        width: 200rpx;
        display: inline-block;
        text-align: right;
        padding-right: 20rpx;
    }
    .text-2{
        padding-left: 20rpx;
    }

    .text-3{
        color: $color-grey;
        width: 80%;
    }
    .text-4{
        text-align: right;
        width: 20%;
        padding-right: 20rpx;
    }


    .title-1{
        font-size: 32rpx;
        padding-left: 30rpx;
    }

    .item{
        margin-top: 40rpx;
    }

    .title-tips{
        padding: 40rpx;
        font-size: 30rpx;
        line-height: 1.6;
    }

    .tt-text-1{
        padding-left: 0rpx;
    }
    .tt-text-2{
        padding-left: 40rpx;
    }
</style>
