<template>
    <view class="box common-block rel" @tap="upLoad">
        <!-- <image :src="imgSrc || defaultImg" mode="scaleToFill" class="upload"/> -->
        <img :src="imgSrc || defaultImg" class="upload"/>
        <view class="abs reset" v-show="isLoadSucess">点击重新上传身份证人像面</view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import wxUtils from '@/assets/js/wxUtils';
    import {healthCardOcrInfo} from '@/apis';

    @Component
    export default class UpDataView extends Vue {

        isLoadSucess = false;
        defaultImg: string = require('@/assets/image/healthCard/upload.png');
        imgSrc = '';
        base64 = '';
        params: IOBJ = {};

        init () {
            this.isLoadSucess = false;
            this.imgSrc = '';
            this.base64 = '';
            this.params = {};
        }

        async upLoad () {
            this.init();
            let imgItem: any = null;
            let base64: any = null;
            try {
                imgItem = await wxUtils.cChooseImage();
                imgItem = imgItem.localIds[0];

                utils.showLoad('获取信息中');
                base64 = await wxUtils.cGetLocalImgData(imgItem);
                base64 = base64.localData;
                base64 = base64.replace(/(data:image\/png;base64,)|(data:image\/jpg;base64,)/, '');

                const res = await healthCardOcrInfo({imageContent: base64});
                // console.log(res);
                const {address, birth, gender, id, name, nation} = res.data;

                // this.imgSrc = imgItem;
                this.imgSrc = `data:image/png;base64,${base64}`;
                this.base64 = base64;
                this.isLoadSucess = true;
                this.params = {
                    name,
                    gender,
                    nation,
                    address,
                    birthday: birth.replace(/\//g, '-'),
                    idNumber: id,
                    idType: '01',
                };

                utils.hideLoad();
                utils.toast('获取成功');
            } catch (error) {
                console.error(error);
                utils.toast('获取失败');
            }

        }

        // getParams () {
        //     return this.params;
        // }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .common-block{
        padding: 0;
        border-radius: 20rpx;
        overflow: hidden;
    }
    .upload{
        width: 100%;
        height: 360rpx;
        object-fit: initial;
    }

    .reset{
        bottom: 0;
        left: 0;
        right: 0;
        text-align: center;
        line-height: 2.5;
        background: rgba(0, 0,0,0.5);
        //  background-color: $main-next-color;
        color: #fff;
    }
</style>
