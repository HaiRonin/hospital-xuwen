<template>
    <view class="">
        <!-- <view style="height:1px;"></view>
        <u-empty v-if="!list.length && !oneLoad" text="暂无健康卡" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>

        <view class="item flex-box f-v" @tap="link()">
            <view class="flex-box align-center justify-s-b">
                <view class="text-2">XX省(市)卫生健康委员会</view>
                <view class="flex-box align-center">
                    <image class="logo-2" :src="require('@/assets/image/healthCard/icon2.png')"/>
                    <view class="text-5">电子健康卡</view>
                </view>
            </view>
            <view class="flex-box align-end justify-s-b content-box">
                <view>
                    <view class="text-3">姓名</view>
                    <view class="text-4">2222****2222</view>
                </view>
                <view class="qr-box rel">
                    <image class="qr" :src="require('@/assets/image/healthCard/fake.png')"/>
                    <image class="logo abs" :src="require('@/assets/image/healthCard/logo_.png')"/>
                </view>
            </view>

            <view class="text-6">中华人民共和国国家卫生健康委员会监制</view>
        </view>

        <view class="flex-box align-center justify-center btn">
            <u-icon name="plus" class="btn-icon"></u-icon>
            <view @tap="addCard">添加健康卡</view>
        </view>
        <view class="text-1">你还可办理{{len}}张卡</view> -->
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {healthCardOcrInfo} from '@/apis';

    @Component
    export default class List extends Vue {

        oneLoad = true;
        len = 5;
        list: IOBJ[] = [];
        params: IOBJ = {};

        addCard () {
            // https://health.tengmed.com/open/getUserCode?redirect_uri=${redirect_uri}
            // utils.link('/pages/healthCard/addCard/index');
            const redirectUri = encodeURIComponent(`${globalConfig.domain.webUrl}/pages/healthCard/addCard/index`);
            window.location.href = `https://health.tengmed.com/open/getUserCode?redirect_uri=${redirectUri}`;
        }

        link () {
            utils.link('/pages/healthCard/cardInfo');
        }

        async getList () {
            this.oneLoad = false;
        }

        onLoad () {
            utils.showLoad();
            // this.getList();
            const redirectUri = encodeURIComponent(`${globalConfig.domain.webUrl}/pages/healthCard/cardInfo`);
            window.location.replace(`https://health.tengmed.com/open/getHealthCardList?redirect_uri=${redirectUri}`);
        }

        created () {}

        mounted () {
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .btn{
        background: $main-color;
        color: #fff;
        line-height: 2.6;
        font-size: 32rpx;
        border-radius:10rpx;
        margin:60rpx 40rpx 0;
    }

    .btn-icon{
        margin-right: 10rpx;
        font-size: 36rpx;
    }

    .text-1{
        text-align: center;
        color: $color-grey;
        font-size: 28rpx;
        margin-top: 30rpx;
    }

    .item{
        width: 620rpx;
        height: 350rpx;
        background: url('@/assets/image/healthCard/bg2.png') no-repeat center/contain;
        margin: 0 auto;
        padding:30rpx 15rpx 16rpx 40rpx;
        font-family: PingFangSC-Medium, 'PINGFANG MEDIUM';
        font-weight: bold;
        box-shadow:  0 0rpx 40rpx 2rpx rgba(100, 101, 102, 0.12);
    }

    .text-2{ font-size: 24rpx; }
    .text-3{ font-size: 36rpx; }
    .text-4{ font-size: 30rpx;margin-top: 20rpx;line-height: 0.9; }
    .text-5{font-size: 30rpx;margin-left: 12rpx;margin-right: 4rpx;}
    .text-6{font-size: 24rpx;text-align: center;margin-top: 25rpx;}

    .qr-box{
        height: 162rpx;
        width: 162rpx;
    }

    .qr{
        width: 100%;
        height: 100%;
    }

    .logo{
        width: 44rpx;
        height: 44rpx;
        top: 50%;
        left: 50%;
        margin-left: -22rpx;
        margin-top: -22rpx;
    }

    .logo-2{
        width: 55rpx;
        height: 55rpx;
    }

    .content-box{
        margin-top: auto;
    }
</style>
