
<template>
    <view>
        <image class="top-bg" :src="require('@/assets/image/br01.png')" alt />
        <u-grid :col="2" class="q-e-3">
            <u-grid-item v-for="(item, index) in quickEntry2" :key="index">
                <image class="q-e-img" :src="item.imgUrl" mode="aspectFit"/>
                <view class="q-e-text">{{item.text}}</view>
            </u-grid-item>
        </u-grid>
    </view>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';


    @Component
    export default class InHospital extends Vue {
        quickEntry2: IOBJ[] = [
            {
                text: '住院信息',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_95.png'),
            },
            {
                text: '押金缴纳',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '押金缴纳记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '住院清单',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_117.png'),
            },
            {
                text: '出院汇总清单',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_109.png'),
            },
        ];


        created () {
        }
    }
</script>

<style lang="scss" scoped>
    .top-bg {
        display: block;
        height: 400rpx;
        width: 100%;
    }

    .q-e-3{margin-top: 32rpx;}
    .q-e-img{width: 64rpx;height: 64rpx;}
    .q-e-text{color: #666;font-size: 24rpx;margin-top: 16rpx;}
</style>

