<template>
    <view class="z-box">
        <view class="common-block" v-for="(item, index) in list" :key="index">
            <view class="flex-box align-center row">
                <view class="text-1">记录信息:</view>
                <view class="text-2 main-color">{{item.msg}}</view>
            </view>
        </view>


        <view style="height:1px;"></view>
        <u-empty v-if="!list.length && !oneLoad" text="暂无信息" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {getDocReferralList} from '@/apis';

    @Component
    export default class ReferralCenterRecordList extends Vue {

        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};

        async getData () {
            const data = this.params;
            const res = await getDocReferralList(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.list = res.data;
            this.oneLoad = false;
        }

        onLoad (options: IOBJ) {
            this.options = options;
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
