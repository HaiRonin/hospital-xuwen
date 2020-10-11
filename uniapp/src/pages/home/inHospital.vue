
<template>
    <view>
        <image class="top-bg" :src="require('@/assets/image/br01.png')" alt />
        <u-grid :col="2" class="common-block" :border="false">
            <u-grid-item v-for="(item, index) in quickEntry2" :key="index" @tap="link(item)">
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
                url: '/pages/outpatient/index?toUrl=/pages/registration/hospitalInfo',
                imgUrl: require('@/assets/image/icon/icon_95.png'),
                // tips: ''
            },
            // {
            //     text: '住院检验/检测报告',
            //     url: '/pages/outpatient/index?toUrl=/pages/registration/reportData/index?lsInOut=2',
            //     imgUrl: require('@/assets/image/icon/icon_104.png'),
            // },
            {
                text: '押金缴纳',
                url: '/pages/outpatient/index?toUrl=/pages/registration/depositHospitalPay',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '押金缴纳记录',
                url: '/pages/outpatient/index?toUrl=/pages/registration/depositHospitalList',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '住院清单',
                url: '/pages/outpatient/index?toUrl=/pages/registration/hospitalizationDayList',
                imgUrl: require('@/assets/image/icon/icon_117.png'),
            },
            {
                text: '出院汇总清单',
                url: '/pages/outpatient/index?toUrl=/pages/registration/totalCostStatement',
                imgUrl: require('@/assets/image/icon/icon_109.png'),
            },
        ];

        link (item: IOBJ) {
            utils.link(item.url);
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
</style>

