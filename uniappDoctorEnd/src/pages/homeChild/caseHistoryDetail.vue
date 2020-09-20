<template>
    <view class="z-box">
        <view class="common-block" v-for="(item, index) in list" :key="index">
            <view class="flex-box  row">
                <view class="text-1">挂号时间:</view>
                <view class="text-2 main-color">{{item.regtime}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">门诊流水号:</view>
                <view class="text-2">{{item.mzregno}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">患者姓名:</view>
                <view class="text-2">{{item.name}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">性别:</view>
                <view class="text-2">{{item.sex}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">患者年龄:</view>
                <view class="text-2">{{item.age}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">主诉:</view>
                <view class="text-2">{{item.maininform}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">现病史:</view>
                <view class="text-2">{{item.sickhist}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">体格检查:</view>
                <view class="text-2">{{item.checkup}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">辅助检查:</view>
                <view class="text-2">{{item.checkct}}</view>
            </view>
            <view class="flex-box  row">
                <view class="text-1">处理及建议:</view>
                <view class="text-2">{{item.disposal}}</view>
            </view>
        </view>


        <view style="height:1px;"></view>
        <u-empty v-if="!list.length && !oneLoad" text="暂无挂号信息" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {getOuClinicFirst} from '@/apis';

    @Component
    export default class ReserveInfo extends Vue {

        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};

        async getData () {
            const data = this.params;
            const res = await getOuClinicFirst(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.list = res.data;
            this.oneLoad = false;
        }

        onLoad (options: IOBJ) {
            this.options = options;
            this.params = utils.jsCopyObj(options);
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
