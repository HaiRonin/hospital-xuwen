
<template>
    <view>
        <view class="flex-box f-v align-center justify-center user-info u-skeleton" :style="{backgroundImage: `url(${require('@/assets/image/memb-head.jpg')})`}">
            <image class="u-i-headimg u-skeleton-circle" :src="require('@/assets/image/d-male.png')" mode="aspectFit"/>
            <view class="text-1 u-skeleton-fillet">zkp xxx</view>
            <view class="text-2 u-skeleton-fillet">xxx</view>
        </view>
        <u-cell-group>
            <u-cell-item v-for="(item, index) in quickEntry2" :key="index" :title="item.text" :arrow="true">
                <template #icon>
                    <image class="list-img" :src="item.imgUrl" mode="aspectFit"/>
                </template>
            </u-cell-item>
        </u-cell-group>

        <u-skeleton :loading="loading" :animation="true" bgColor="#FFF"></u-skeleton>
    </view>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';

    @Component
    export default class My extends Vue {
        loading: boolean = false;
        quickEntry2: IOBJ[] = [
            {
                text: '就诊人管理',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_39.png'),
            },
            {
                text: '预约挂号记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_41.png'),
            },
            {
                text: '待缴费处方记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_43.png'),
            },
            {
                text: '已缴费处方记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_43.png'),
            },
        ];

        created () {
        }
    }
</script>

<style lang="scss" scoped>
    .list-img{width: 32rpx;height: 32rpx;margin-right: 16rpx;display: inline-block;vertical-align: middle;}
    .list-text{display: inline-block;vertical-align: middle;font-size: 28.8rpx;}

    .user-info{
        background: no-repeat center/cover;
        height:384rpx;
        margin-bottom: 32rpx;
        color: #fff;
    }

    .u-i-headimg{
        width: 112rpx;height: 112rpx;object-fit: cover;
        border: 6rpx solid #fff;
        border-radius: 50%;
    }

    .text-1{font-size: 30rpx;margin-top: 16rpx;}
    .text-2{font-size: 28rpx;margin-top: 8rpx;}
</style>

