
<template>
    <div class="box">
        <u-alert-tips type="primary" class="z-custom" show-icon description="以下推荐科室仅供参考"></u-alert-tips>

        <view class="line-text z-custom-d"><text>就诊建议</text></view>
        <div class="text">如果病情持续或加重，建议尽快到医院就诊。</div>

        <view class="line-text z-custom-d"><text>推荐科室</text></view>

        <div class="flex-box align-center justify-s-b common-block item" v-for="(item, index) in list" :key="index">
            <div class="old-ellipsis">{{item.organName || '--'}}</div>
            <div class="flex-box align-center justify-s-b">
                <button class="z-btn old-ellipsis" @click="link(item, '2')">预约挂号</button>
                <button class="z-btn old-ellipsis" @click="link(item, '0')">当天挂号</button>
            </div>
        </div>

        <u-empty text="没有查到相关科室" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !list.length"></u-empty>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {diagnosis as diagnosisApi} from '@/apis';


    @Component
    export default class diagnosis extends Vue {

        params: IOBJ = {};
        options: IOBJ = {};
        list: IOBJ[] = [];
        oneLoad = true;

        async getList () {
            const res = await diagnosisApi(this.options, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));
            this.list = res.data;
            this.oneLoad = false;
        }

        link (item: IOBJ, type: string) {
            // this.$router.push({name: 'registrationDepartmentDoctorList'});
            utils.link(`/pages/registration/departmentDoctorList/index?departmentorganId=${item.organId}&type=${type}`);
        }

        onLoad (options: IOBJ) {
            this.options = options;
        }

        created () {
            this.getList();
        }
    }
</script>

<style lang="scss" scoped>
    .box {
        min-height: 100%;
        background: #fff;
        // overflow: hidden;
    }
    .z-custom {
        color: $main-color;
        background: $bg-main-color;
        position:sticky;
        top: 0;
        left: 0;
        z-index: 2;
        border:none;
        border-radius: 0;
    }
    .z-custom-d{
        margin: 40rpx 0;
        color:$color-grey;
        font-size: 28rpx;
    }
    .z-custom-d>text{
        margin-left: -50%;
    }
    .z-custom-d::after{
        background: -webkit-linear-gradient(left, $color-weak-grey, $color-weak-grey) no-repeat;
        background-size: 100% 2rpx;
        background-position: center;
    }
    .text{padding: 16rpx 48rpx;font-size: 28rpx;}

    .z-btn{
        margin-left: 20rpx;
        color: $main-color;
        border: 2rpx solid $main-color;
        line-height: 60rpx;
        background: #fff;
        font-size: 26rpx;
        padding: 0 20rpx;
        border-radius: 40rpx;
    }
    // .item{
    //     margin: 0 32px 32px;padding: 25.6px 16px;font-size: 28.8px;
    //     border-radius: 9.6px;
    //     box-shadow: 0 64px 24px rgba(100, 101, 102, 0.12);
    // }
</style>

