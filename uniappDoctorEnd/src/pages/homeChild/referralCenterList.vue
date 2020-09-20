<template>
    <view class="z-box">
        <topSort
            v-model="modalShow"
            @commit="getData"
            ref="topSort"
            class="top-sort"
            :showTime="false"
            inputLabel="医生工号"
            inputPlaceholder="请输入转诊医生工号"
        />

        <view class="common-block rel" v-for="(item, index) in list" :key="index" @tap="link(item)">
            <view class="flex-box align-center row">
                <view class="text-1">挂号时间:</view>
                <view class="text-2 main-color">{{item.regtime}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">患者姓名:</view>
                <view class="text-2">{{item.name}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">患者年龄:</view>
                <view class="text-2">{{item.age}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">门诊流水号:</view>
                <view class="text-2">{{item.mzregno}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">卡号:</view>
                <view class="text-2">{{item.cardno}}</view>
            </view>

            <u-icon name="arrow-right" class="z-icon abs"></u-icon>
        </view>

        <u-empty v-if="!modalShow && !list.length && !oneLoad" text="暂无数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import topSort from '@/components/topSort.vue';
    import {toDocReferral} from '@/apis';

    @Component({
        components: {
            topSort
        }
    })
    export default class ReferralCenterList extends Vue {
        @Ref('topSort') readonly topSort!: IOBJ;

        modalShow = true;
        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};

        link (item: IOBJ) {
            const {endDate, startDate} = this.topSort.params;
            const strData = utils.serialize({
                cardNo: item.cardno,
                endDate,
                startDate,
            });
            utils.link(`/pages/homeChild/caseHistoryDetail?${strData}`);
        }

        async getData (data: IOBJ) {
            const organdoctorId = data.cardno;
            if (utils.zEmpty(organdoctorId)) {
                utils.toast('请输入医生工号');
                return;
            }
            const params = Object.assign({organdoctorId}, this.params);
            const res = await toDocReferral(params, {isLoad: true}).catch(() => ({data: []}));


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
            this.options = options;
            this.params.cardNo = options.cardNo;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-box {
        padding-top: 0;
    }

    .top-sort {
        position: sticky;
        top: 0;
        left: 0;
        right: 0;
        margin-bottom: 32rpx;
        z-index: 2;
    }

    .common-block {
        line-height: 60rpx;
    }
    .text-1 {
        color: $color-grey;
        padding-right: 20rpx;
        min-width: 180rpx;
        text-align: right;
        padding-right: 20rpx;
    }

    .z-icon {
        right: 24rpx;
        top: 50%;
        transform: translateY(-50%);
        color: $color-grey;
    }
</style>
