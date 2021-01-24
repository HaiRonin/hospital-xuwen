<template>
    <view class="z-box">
        <view class="common-block z-tips">
            <view>温馨提示：</view>
            <view>1.挂号后如不就诊，请于当天申请取消挂号并申请退费。</view>
            <view>2.就诊当日请先到自助机取号，再就诊。取号后不能退号退款。</view>
        </view>
        <view class="common-block common-block-2">
            <view class="flex-box align-center justify-s-b item">
                <view class="text-1">费用</view>
                <view class="text-2">{{params.payAmount}}元</view>
            </view>
            <view class="flex-box align-center justify-s-b item">
                <view class="text-1">科室</view>
                <view class="text-2">{{params.departmentorganName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b item">
                <view class="text-1">医生</view>
                <view class="text-2">{{params.doctorName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b item">
                <view class="text-1">就诊时间</view>
                <view class="text-2">{{params.sourceDateText}}</view>
            </view>
        </view>
        <view class="common-block common-block-2">
            <view class="flex-box align-center justify-s-b item" @tap="linkPatient">
                <view class="text-1">就诊人</view>
                <view class="text-2 flex-box align-center">
                    <view v-if="params.patientName">{{params.patientName}}</view>
                    <view v-else class="text-3">请选择</view>
                    <u-icon name="arrow-right" class="item-icon"></u-icon>
                </view>
            </view>
        </view>

        <view class="fake-view"></view>
        <view class="bottom-btn" @tap="commit">确认支付</view>

        <pay ref="pay" :request="payRequest" @paySuccess="paySuccess"/>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {Getter} from 'vuex-class';
    import pay from '@/components/pay.vue';
    import {orderOutpatientPayment} from '@/apis';
    import {healthCardRD} from '@/assets/js/reportedData';

    @Component({
        components: {
            pay
        }
    })
    export default class ConfirmOrder extends Vue {
        @Ref('pay') readonly pay!: IOBJ;
        @Getter('isUse') actionIsUse!: boolean;

        params: IOBJ = {};
        options: IOBJ = {};
        payRequest = orderOutpatientPayment;

        upDataPatient (item: IOBJ) {
            const params = this.params;
            // console.log(item);
            this.$set(params, 'patientNo', item.patientNo);
            this.$set(params, 'patientName', item.patientName);
            this.$set(params, 'cardNo', item.cardNo);
            this.$set(params, 'Mobile', item.oldData.Mobile);
        }

        paySuccess () {
            const {patientNo, patientName, cardNo} = this.params;
            const strData = utils.serialize({
                patientNo,
                cardNo,
                name: patientName
            });
            utils.link(`/pages/registration/reportOrderList/index?${strData}`, 1);
        }

        // 选择就就诊人
        linkPatient () {
            utils.link('/pages/outpatient/index?sel=1');
        }

        playQuestionnaire (data: IOBJ) {
            const actionIsUse = this.actionIsUse;
            // debugger

            // 限制性功能
            if (!actionIsUse) return false;

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

            if (utils.zEmpty(data.patientNo)) {
                utils.toast('请选择就诊人');
                return;
            } else if (this.playQuestionnaire(data)) {
                return;
            }


            healthCardRD({
                scene: this.$store.getters.dayTime[0] === data.sourceDate ? '0101012' : '0101011',
                department: data.departmentorganName,
            });
            this.pay.startPay(data);
            // const res = await orderOutpatientPayment(data, {isLoad: true});
            // this.pay.startPay(res.data);
        }

        onLoad (options: IOBJ) {
            this.options = options;
            this.params = utils.jsCopyObj(options);
            uni.$on('upDataPatient', this.upDataPatient);
            uni.$on('payCommit', this.commit);
        }

        created () {
            this.commit = utils.throttle(this.commit, 300, 300, true);
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
    .z-box {
        overflow: initial;
        // background: #f5f5f5;
    }

    .z-tips {
        font-size: 30rpx;
        line-height: 1.8;
        color: $main-error-color;
    }

    .common-block-2 {
        padding-top: 0;
        padding-bottom: 0;
    }

    .item {
        line-height: 3;
    }

    .item + .item {
        border-top: $border-line;
    }

    .item-icon {
        color: $color-grey;
        margin-left: 10rpx;
    }

    .text-3 {
        color: $color-grey;
    }

    .fake-view {
        height: 100rpx;
    }

    .bottom-btn {
        position: fixed;
        left: 0;
        bottom: 0;
        right: 0;
        width: 100%;
        line-height: 90rpx;
        font-size: 34rpx;
        letter-spacing: 2rpx;
        background: $main-cur-color;
        color: #fff;
        text-align: center;
        z-index: 2;
    }
</style>
