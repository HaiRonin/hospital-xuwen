
<template>
    <view>
        <image class="top-bg" :src="require('@/assets/image/br01.png')" alt />
        <u-grid :col="2" class="common-block" :border="false">
            <u-grid-item v-for="(item, index) in quickEntry2" :key="index" @tap="link(item.url)">
                <image class="q-e-img" :src="item.imgUrl" mode="aspectFit"/>
                <view class="q-e-text">{{item.text}}</view>
            </u-grid-item>
        </u-grid>

        <view class="out-box">
            <u-button type="info" :plain="false" @tap="logOut">退出登录</u-button>
        </view>
    </view>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {Getter, Mutation} from 'vuex-class';


    @Component
    export default class Home extends Vue {
        @Mutation('user/clearState') readonly clearUserInfo!: () => void;

        quickEntry2: IOBJ[] = [
            {
                text: '预约挂号患者查询',
                url: '/pages/homeChild/reserveInfo?isPreReg=1',
                imgUrl: require('@/assets/image/icon/icon_84.png'),
            },
            {
                text: '当天挂号患者查询',
                url: '/pages/homeChild/reserveInfo?isPreReg=0',
                imgUrl: require('@/assets/image/icon/icon_84.png'),
            },
            {
                text: '查看患者门诊病历',
                url: '/pages/homeChild/caseHistoryList?type=1',
                imgUrl: require('@/assets/image/icon/icon_104.png'),
            },
            {
                text: '统计分析',
                url: '/pages/homeChild/statistics',
                imgUrl: require('@/assets/image/icon/icon_109.png'),
            },
            {
                text: '门诊医生转诊',
                url: '/pages/homeChild/caseHistoryList?type=2',
                imgUrl: require('@/assets/image/icon/icon_111.png'),
            },
            {
                text: '门诊医生转诊记录查询',
                url: '/pages/homeChild/referralCenterRecordList',
                imgUrl: require('@/assets/image/icon/icon_117.png'),
            },
            {
                text: '门诊报告查询',
                url: '/pages/homeChild/caseHistoryList?type=3',
                imgUrl: require('@/assets/image/icon/icon_87.png'),
            },
        ];

        link = utils.link;

        logOut () {
            this.clearUserInfo();
            utils.link('/pages/login', 2);
        }

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

    // .q-e-3{margin-top: 32rpx;}
    .q-e-img{width: 64rpx;height: 64rpx;}
    .q-e-text{color: #666;font-size: 24rpx;margin-top: 16rpx;}
    .common-block{
        width: auto;
        padding: 0;
        // border: 20rpx solid #f6f6f6;
        border-radius:20rpx;
        overflow: hidden;
        margin-top: 32rpx;
    }

    .out-box{
        padding: 20rpx 50rpx;
    }
</style>

