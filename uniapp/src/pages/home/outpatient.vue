
<template>
    <view>
        <image class="top-bg" :src="require('@/assets/image/br01.png')" />
        <view class="flex-box align-center box-1">
            <view
                class="flex-box align-center justify-center flex-1"
                v-for="(item, index) in quickEntry1"
                :key="index"
                @tap="link(item.url)"
            >
                <image :src="item.imgUrl" alt class="b-img" mode="aspectFit" />
                <view>
                    <view class="b-text-1">{{item.text}}</view>
                    <view class="b-text-2">{{item.text2}}</view>
                </view>
            </view>
        </view>
        <u-grid :col="3">
            <u-grid-item v-for="(item, index) in quickEntry2" :key="index" @tap="link(item.url)">
                <image class="q-e-img" :src="item.imgUrl" mode="aspectFit" />
                <view class="q-e-text">{{item.text}}</view>
            </u-grid-item>
        </u-grid>
    </view>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';

    const {link} = utils;

    @Component
    export default class Outpatient extends Vue {
        quickEntry1: IOBJ[] = [
            {
                text: '当日挂号',
                text2: '当日挂号新体验',
                url: '/pages/registration/index?type=0',
                imgUrl: require('@/assets/image/icon/icon_68.png'),
            },
            {
                text: '预约挂号',
                text2: '预约挂号新体验',
                url: '/pages/registration/index?type=2',
                imgUrl: require('@/assets/image/icon/icon_68.png'),
            },
        ];

        quickEntry2: IOBJ[] = [
            {
                text: '智能导诊',
                url: '/pages/registration/departmentDoctorList/index',
                imgUrl: require('@/assets/image/icon/icon_92.png'),
            },
            {
                text: '门诊缴费',
                url: '',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '报告查询',
                url: '',
                imgUrl: require('@/assets/image/icon/icon_87.png'),
            },
            {
                text: '信息查询',
                url: '/pages/info/index',
                imgUrl: require('@/assets/image/icon/icon_81.png'),
            },
            {
                text: '科室介绍',
                url: '/pages/info/departmentIsIntroduced',
                imgUrl: require('@/assets/image/icon/icon_91.png'),
            },
        ];

        link = link;

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

    .q-e-3 {
        margin-top: 32rpx;
    }
    .q-e-img {
        width: 64rpx;
        height: 64rpx;
    }
    .q-e-text {
        color: #666;
        font-size: 24rpx;
        margin-top: 16rpx;
    }

    .box-1 {
        padding: 32rpx 0;
        background: #fff -webkit-linear-gradient(left, #ebedf0, #ebedf0) no-repeat center/2rpx
            50%;
        border-bottom: 32rpx solid #ebedf0;
    }
    .b-img {
        width: 96rpx;
        height: 96rpx;
        margin-right: 25.6rpx;
    }
    .b-text-1 {
        font-size: 30.4rpx;
    }
    .b-text-2 {
        color: #999;
        margin-top: 8rpx;
        font-size: 24rpx;
    }
</style>

