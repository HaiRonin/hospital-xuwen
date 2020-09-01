<template>
    <u-modal
        v-model="show"
        ref="modal"
        title="条形码"
    >
        <view class="content">
            <view v-if="error" class="error-text">出错了</view>
            <u-loadmore v-else-if="load" status="loading" bg-color="transparent"/>
            <!-- <image v-else class="bar-code" mode="widthFix" :src="imgUrl"/> -->
            <img v-else class="bar-code" mode="widthFix" :src="imgUrl"/>
        </view>
    </u-modal>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {createdBarCode} from '@/apis';

    @Component
    export default class LookBarCode extends Vue {

        show = false;
        curItem: IOBJ = {};
        imgUrl = '';
        load = true;
        error = false;

        async getBarCode () {
            const CardNo = this.curItem.CardNo;

            const res = await createdBarCode({CardNo}, {closeErrorTips: true}).catch(() => {
                this.error = true;
                return Promise.reject();
            });
            // console.log(res.msg.replace(/\n/g, ''));
            // this.imgUrl = 'data:image/png;base64,' + res.msg.replace(/\n/g, '');
            this.imgUrl = 'data:image/png;base64,' + res.msg;
            // console.log(this.imgUrl);
            this.load = false;
        }

        openFun (item: IOBJ) {
            this.curItem = item;
            this.imgUrl = '';
            this.load = true;
            this.getBarCode();
            this.show = true;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content{
        padding: 46rpx;
    }
    .error-text{
        color: rgb(96, 98, 102);
        font-size: 28rpx;
        text-align: center;
    }
    .bar-code{
        width: 100%;
        // object-fit: none;
    }
</style>
