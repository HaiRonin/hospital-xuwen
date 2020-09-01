
<template>
    <view>
        <view class="flex-box f-v align-center justify-center user-info u-skeleton" :style="{backgroundImage: `url(${require('@/assets/image/memb-head.jpg')})`}">
            <image class="u-i-headimg u-skeleton-circle" :src="require('@/assets/image/d-male.png')" mode="aspectFit"/>
            <view class="text-1 u-skeleton-fillet">{{userInfo.userName}}</view>
            <view class="text-2 u-skeleton-fillet">{{userInfo.userName}}</view>
        </view>
        <u-cell-group class="common-block">
            <u-cell-item v-for="(item, index) in quickEntry2" :key="index" :title="item.text" :arrow="true" @tap="itemTap(item)">
                <template #icon>
                    <image class="list-img" :src="item.imgUrl" mode="aspectFit"/>
                </template>
            </u-cell-item>
        </u-cell-group>
        <view class="out-box" v-if="isLogin">
            <u-button type="info" :plain="false" @tap="logOut">退出登录</u-button>
        </view>
        <!-- <u-skeleton :loading="loading" :animation="true" bgColor="#FFF"></u-skeleton> -->

        <upDataPwa ref="upDataPwa"/>
    </view>
</template>

<script lang="ts">
    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {Getter, Mutation} from 'vuex-class';
    import upDataPwa from './childAction/upDataPwa.vue';

    @Component({
        components: {
            upDataPwa
        }
    })
    export default class My extends Vue {
        @Getter('isLogin') readonly isLogin!: boolean;
        @Getter('userInfo') readonly userInfo!: IOBJ;
        @Mutation('user/clearState') readonly clearUserInfo!: () => void;
        @Ref('upDataPwa') readonly upDataPwa!: IOBJ;

        // loading: boolean = false;
        quickEntry2: IOBJ[] = [
            {
                text: '修改密码',
                action: 'upDataPwa',
                imgUrl: require('@/assets/image/icon/icon_39.png'),
            },
            {
                text: '就诊人管理',
                url: '/pages/outpatient/index',
                imgUrl: require('@/assets/image/icon/icon_39.png'),
            },
            {
                text: '预约挂号记录',
                url: '/pages/outpatient/index?toUrl=',
                imgUrl: require('@/assets/image/icon/icon_41.png'),
            },
            {
                text: '待缴费处方记录',
                url: '/pages/outpatient/index?toUrl=',
                imgUrl: require('@/assets/image/icon/icon_43.png'),
            },
            {
                text: '满意度调查',
                url: '/pages/other/questionnaire',
                imgUrl: require('@/assets/image/icon/icon_43.png'),
            },
        ];

        itemTap (item: IOBJ) {
            if (!this.isLogin) return;

            const {url, action} = item;

            if (!utils.zEmpty(url)) {
                utils.link(url);
                return;
            }

            switch (action) {
                case 'upDataPwa':
                    this.upDataPwa.openFun();
                    break;
            }

        }

        logOut () {
            this.clearUserInfo();
            utils.link('/pages/home/outpatient', 2);
        }

        created () {
            console.log(this.isLogin);
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

    .out-box{
        padding: 20rpx 50rpx;
    }

    .common-block{
        width: auto;
        padding: 0;
        // border: 20rpx solid #f6f6f6;
        border-radius:20rpx;
        overflow: hidden;
    }
</style>

