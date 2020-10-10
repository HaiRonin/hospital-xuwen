<template>
    <u-modal
        v-model="show"
        ref="modal"
        title="处方缴费成功"
    >
        <view class="content">
            <view class="text-1">请到</view>
            <view class="text-2" v-for="(item, index) in list" :key="index">{{item.dispensaryWin}}</view>
            <!-- <view class="text-2">xxx窗口</view> -->
            <!-- <view class="text-2">xxx窗口</view> -->
            <view class="text-3">取药</view>
        </view>
    </u-modal>
</template>

<script lang="ts">

    import {Component, Vue, Prop} from 'vue-property-decorator';

    @Component
    export default class TipsModal extends Vue {

        show = false;
        list: IOBJ[] = [];

        openFun (list: IOBJ[]) {
            this.list = list;
            this.show = true;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content{
        padding: 46rpx 80rpx;
    }
    .text-1{margin-bottom: 30rpx;color: $color-grey;font-size: 30rpx;}
    .text-2{text-align: center;color: $main-color;font-size: 32rpx;line-height: 2;}
    .text-3{text-align: right;margin-top: 30rpx;color: $color-grey;font-size: 30rpx;}
</style>
