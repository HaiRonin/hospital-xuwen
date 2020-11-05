<template>
    <view class="box" v-if="show">
        <view class="common-block top-1">
            <!-- <view class="qr-box rel">
                <image class="qr" :src="require('@/assets/image/healthCard/fake.png')"/>
                <image class="logo abs" :src="require('@/assets/image/healthCard/logo_.png')"/>
            </view> -->
            <imgLoad
                @getImgLoadData="getImgLoadData"
                v-bind="{
                    idType: '1',
                    healthCardId: userInfo.healthCardId,
                    idNumber: userInfo.idNumber,
                }"
            />
        </view>
        <view class="common-block">
            <view class="item flex-box justify-s-b align-center">
                <view class="text-1">姓名</view>
                <view class="text-2">{{userInfo.name}}</view>
            </view>
            <view class="item flex-box justify-s-b align-center">
                <view class="text-1">身份证号</view>
                <view class="text-2">{{userInfo.newIdNumber}}</view>
            </view>
            <!-- <view class="item flex-box justify-s-b align-center">
                <view class="text-1">就诊卡号</view>
                <view class="text-2">{{userInfo.name}}</view>
            </view> -->
            <view class="item flex-box justify-s-b align-center">
                <view class="text-1">手机号</view>
                <view class="text-2">{{userInfo.newPhone1}}</view>
            </view>
        </view>

        <view class="flex-box align-center justify-center btn" v-if="isH5 && qrCodeText" @tap="linkWxCard">
            <view>进入卡包</view>
        </view>
        <!-- <view class="text-3">解绑电子卡</view> -->
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {healthCardGetHealthCard, healthCardGetOrderIdByOutAppId} from '@/apis';
    import imgLoad from '@/components/imgLoad.vue';

    @Component({
        components: {
            imgLoad
        }
    })
    export default class CardInfo extends Vue {

        show = false;
        isH5 = false;
        qrCodeText = '';
        userInfo: IOBJ = {};
        options: IOBJ = {};


        async linkWxCard () {
            const qrCodeText = this.qrCodeText;
            const res = await healthCardGetOrderIdByOutAppId({imageContent: qrCodeText}, {isLoad: true});

            const redirectUri = encodeURIComponent(`${globalConfig.domain.webUrl}/pages/healthCard/list`);
            const orderId = res.msg;
            // console.log(res);
            window.location.replace(`https://health.tengmed.com/open/takeMsCard?order_id=${orderId}&redirect_uri=${redirectUri}`);
        }

        async getData (healthCode: string) {
            const res = await healthCardGetHealthCard({healthCode}, {isLoad: true});
            let data = JSON.parse(res.msg);
            data = data.card;
            // console.log(res);
            // console.log(data);

            this.setData(data);
        }

        getImgLoadData (data: IOBJ) {
            console.log(data);
            this.qrCodeText = data.qrCodeText;
        }

        setData (data: IOBJ) {
            data.newIdNumber = `${data.idNumber.substr(0, 4)}***********${data.idNumber.substr(-4)}`;
            data.newPhone1 = `${data.phone1.substr(0, 2)}*******${data.phone1.substr(-2)}`;

            this.userInfo = data;
            this.show = true;
            utils.hideLoad();
        }

        async onLoad (options: IOBJ) {
            utils.showLoad();
            this.options = options;

            const healthCode = typeof options.healthCode === 'undefined' ? '-1' : `${options.healthCode}`;

            // https://open.tengmed.com/doc/#41
            if (options.healthCardId) {
                this.setData(options);
            } else if (healthCode === '0') {
                const patientItem = options.patientItem;
                const redirectUri = encodeURIComponent(`${globalConfig.domain.webUrl}/pages/healthCard/addCard/index?patientItem=${patientItem}`);
                window.location.replace(`https://health.tengmed.com/open/getUserCode?redirect_uri=${redirectUri}`);
            } else if (healthCode === '-1') {
                utils.hideLoad();
                await utils.confirm({content: '请进行授权', showCancel: false});
                const redirectUri = encodeURIComponent(`${globalConfig.domain.webUrl}/pages/healthCard/cardInfo`);
                window.location.replace(`https://health.tengmed.com/open/getHealthCardList?redirect_uri=${redirectUri}`);
            } else {
                this.getData(healthCode);
            }
        }

        created () {
            // #ifdef H5
            this.isH5 = true;
            // #endif
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box{
        padding-top: 32rpx;
    }

    .common-block{
        padding:0 30rpx;
    }

    .top-1{
        padding: 80rpx 0;
    }

    .item{
        font-size: 32rpx;
        line-height: 3;
    }

    .item + .item{border-top:$border-line;}

    .text-3{
        text-align: center;
        color: $color-grey;
        font-size: 28rpx;
        margin-top: 30rpx;
        line-height: 3;
    }

    .qr-box{
        height: 400rpx;
        width: 400rpx;
        margin: auto;
    }

    .qr{
        width: 100%;
        height: 100%;
    }

    .logo{
        width: 100rpx;
        height:100rpx;
        top: 50%;
        left: 50%;
        margin-left: -50rpx;
        margin-top: -50rpx;
    }

    .btn{
        background: $main-color;
        color: #fff;
        line-height: 2.6;
        font-size: 32rpx;
        border-radius:10rpx;
        margin:60rpx 40rpx 0;
    }
</style>
