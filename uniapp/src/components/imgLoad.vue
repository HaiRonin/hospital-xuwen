<template>
    <view class="qr-box rel">
        <view v-if="errText" class="err-box abs flex-box align-center justify-center" @tap.stop="getData">{{errText}}</view>
        <template v-else>
            <image class="qr" :src="qrCodeImg || require('@/assets/image/healthCard/fake.png')" />
            <image class="logo abs" :src="require('@/assets/image/healthCard/logo_.png')" />
            <view class="abs load-box flex-box align-center justify-center" v-if="load">
                <u-loading class="load" mode="circle"></u-loading>
            </view>
        </template>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Prop, Emit} from 'vue-property-decorator';
    import {healthCardGetDynamicQRCode} from '@/apis';

    @Component
    export default class ImgLoad extends Vue {
        @Prop({type: String, required: true}) readonly idType!: string;
        @Prop({type: String, required: true}) readonly healthCardId!: string;
        @Prop({type: String, required: true}) readonly idNumber!: string;
        // 0动态 1静态
        @Prop({type: Number, default: 1}) readonly codeType!: string;

        load = true;
        errText = '';
        qrCodeImg = '';

        @Emit('getImgLoadData')
        getImgLoadData (item: IOBJ) {
            return item;
        }

        async getData () {
            // if (utils.zEmpty(this.errText)) await utils.sleep(1000);

            this.errText = '';
            this.qrCodeImg = '';
            this.load = true;

            const params = {
                idType: this.idType,
                healthCardId: this.healthCardId,
                codeType: this.codeType,
                idNumber: this.idNumber,
                mobile: this.$store.getters.userInfo.userName
            };
            const res = await healthCardGetDynamicQRCode(params, {isRCache: true}).catch(() => {
                this.errText = '出错了，点击重新获取';
                return Promise.reject();
            });
            const data = JSON.parse(res.msg);
            let qrCodeImg: string = data.qrCodeImg;
            qrCodeImg = qrCodeImg.replace(/(data:image\/png;base64,)|(data:image\/jpg;base64,)/, '');
            // console.log(res);
            // console.log(data);
            // console.log(data);

            this.getImgLoadData(data);
            this.qrCodeImg = `data:image/png;base64,${qrCodeImg}`;
            this.load = false;
        }

        created () {
            this.getData();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .qr-box {
        height: 400rpx;
        width: 400rpx;
        margin: auto;
    }

    .qr {
        width: 100%;
        height: 100%;
    }

    .logo {
        width: 100rpx;
        height: 100rpx;
        top: 50%;
        left: 50%;
        margin-left: -50rpx;
        margin-top: -50rpx;
    }

    .load-box {
        z-index: 2;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: rgba(0, 0, 0, 0.9);
    }

    .load {
        width: 100rpx !important;
        height: 100rpx !important;
        border-color: $main-next-color $main-next-color $main-next-color $main-color !important;
    }

    .err-box{
        z-index: 2;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: rgba(0, 0, 0, 0.9);
        color:#fff;
    }
</style>
