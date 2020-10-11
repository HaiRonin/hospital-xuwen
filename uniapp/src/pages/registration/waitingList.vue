<template>
    <view class="box">

        <view class="common-block" v-for="(item, index) in list" :key="index">
            <view class="flex-box align-center item">
                <view class="text-1">就诊地址:</view>
                <view class="text-2 main-color">{{item.departmentAddress}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">就诊医生:</view>
                <view class="text-2">{{item.doctorName}}</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">前面还有:</view>
                <view class="text-2">{{item.waitNum === '-1' ? '0' :item.waitNum}}人</view>
            </view>
            <view class="flex-box align-center item">
                <view class="text-1">叫号号码:</view>
                <view class="text-2 main-color">{{item.takeNo}}号</view>
            </view>
        </view>

        <u-empty v-if="!list.length && !oneLoad" text="暂无数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryRegWaitNum} from '@/apis';

    @Component
    export default class Name extends Vue {

        options: IOBJ = {};
        params: IOBJ = {};
        list: IOBJ[] = [];
        oneLoad = true;

        async getList () {
            const res = await queryRegWaitNum(this.params, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.list = res.data;
            this.oneLoad = false;
        }

        onPullDownRefresh () {
            utils.pullDown(() => this.getList());
        }

        onLoad (options: IOBJ) {
            this.options = options;
            this.params.patientNo = options.patientNo;
        }

        created () {
            this.getList();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box {
        // background: #fff;
        padding-bottom: 20rpx;
        min-height: 100%;
        padding-top: 32rpx;
    }

    .item{
        line-height: 60rpx;
        color: $color-grey;
    }

    .text-1{
        width: 130rpx;
        // text-align: right;
        margin-right: 20rpx;
    }
</style>
