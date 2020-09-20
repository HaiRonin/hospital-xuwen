<template>
    <view class="z-box">
        <view class="common-block" v-for="(item, index) in list" :key="index">
            <view class="flex-box align-center row">
                <view class="text-1">预约时间:</view>
                <view class="text-2 main-color">{{item.regtime}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">患者姓名:</view>
                <view class="text-2">{{item.name}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">门诊流水号:</view>
                <view class="text-2">{{item.mzregno}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">卡号:</view>
                <view class="text-2">{{item.cardno}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">性别:</view>
                <view class="text-2">{{item.sex}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">预约科室:</view>
                <view class="text-2">{{item.locname}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">是否报到:</view>
                <view class="text-2">{{item.iscall}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">联系方式:</view>
                <view class="text-2">{{item.phone}}</view>
            </view>
        </view>


        <view style="height:1px;"></view>
        <u-empty v-if="!list.length && !oneLoad" text="暂无挂号信息" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {getDocFInfo} from '@/apis';

    @Component
    export default class ReserveInfo extends Vue {

        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};

        async getData () {
            const data = this.params;
            const res = await getDocFInfo(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.list = res.data;
            this.oneLoad = false;
        }

        onLoad (options: IOBJ) {
            // isPreReg 0.非预约 1.预约
            const isPreReg = this.params.IsPreReg = options.isPreReg || '1';
            this.options = options;
            uni.setNavigationBarTitle({
                title: `${isPreReg === '1' ? '预约' : '当天'}挂号患者查询`
            });
        }

        created () {
            this.getData();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>

    .common-block{
        line-height: 60rpx;
    }
    .text-1{
        color:$color-grey;
        padding-right: 20rpx;
        min-width: 180rpx;
        text-align: right;
        padding-right: 20rpx;
    }
</style>
