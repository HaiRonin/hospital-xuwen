<template>
    <div class="flex-box f-v justify-center align-center box">
        <image :src="require('@/assets/image/logo.png')" class="logo" mode="" />
        <view class="title">{{ title }}</view>
        <view class="text">版本号: {{ version }}</view>
        <!-- <view class="btn" @tap="down">患者端下载</view> -->
        <view class="btn" @tap="down2">下载</view>

        <view class="mask" v-if="maskShow" @tap="maskShow = false">
            <image
                class="mask-image abs"
                mode="heightFix"
                :src="require('@/assets/image/right-tips.png')"
            />
        </view>
    </div>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';


    // 下载地址：
    // 患者端 http://apptest.gdsnkzxyy.cn/his/download/app/1
    // 医生端 http://apptest.gdsnkzxyy.cn/his/download/app/2

    @Component
    export default class Download extends Vue {

        title = '农垦医院app-医生端';
        version = '1.0.0';
        fileName2 = '/his/download/app/2';
        maskShow = false;

        isWeixin () {
            let wx = navigator.userAgent.toLowerCase();
            return !!~wx.indexOf('micromessenger');
        }

        down2 () {
            if (this.isWeixin()) {
                this.maskShow = true;
                return;
            }
            window.open(`${globalConfig.domain.webUrl}/${this.fileName2}`, '_blank');
        }

        created () {
            // console.log(plus.runtime.version);
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box {
        min-height: 100%;
        background: #fff;
    }

    .logo {
        width: 250rpx;
        height: 250rpx;
        margin-top: -300rpx;
        margin-bottom: 50rpx;
    }

    .title {
        font-size: 52rpx;
    }

    .text {
        font-size: 28rpx;
        color: $color-grey;
        margin-bottom: 100rpx;
        margin-top: 20rpx;
    }

    .btn {
        background-color: $main-color;
        color: #fff;
        border: none;
        font-size: 40rpx;
        padding: 24rpx 0;
        border-radius: 10rpx;
        width: 50%;
        text-align: center;
        margin-top: 50rpx;
    }

    .mask {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.8);
    }

    .mask-image {
        right: 0;
        top: 0;
        height: 400rpx;
    }
</style>
