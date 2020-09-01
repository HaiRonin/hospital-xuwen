<template>
    <view class="box">
        <u-search
            placeholder="请输入医生名称"
            shape="round"
            :clearabled="false"
            :show-action="false"
            @change="input"
            class="search-box"
            focus
        ></u-search>

        <view class="fake-height"></view>

        <view v-for="(item, index) in list" :key="index" class="flex-box align-center justify-s-b common-block item">
            <view class="item-img">
                <u-image :src="item.photoUrl || require('@/assets/image/d-male.png')" height="100%" mode="widthFix" ></u-image>
            </view>
            <view class="flex-1">
                <view class="item-text-1">{{item.doctorName}}</view>
                <view class="item-text-2">{{item.departmentName}}</view>
                <view class="item-text-2">值班时间 {{item.regdate}}</view>
            </view>
            <u-button type="primary" plain size="mini" shape="circle" class="item-btn z-btn" @tap="link(item)">挂号</u-button>
        </view>

        <u-empty text="没有搜索到该医生" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !list.length"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {seachDoctorForName} from '@/apis';

    @Component
    export default class Name extends Vue {

        list: IOBJ[] = [];
        options: IOBJ = {};
        isToday: 0 | 1 = 0;
        oneLoad = true;

        async input (name: string) {
            if (utils.zEmpty(name)) return;
            const isToday = this.isToday;

            const res = await seachDoctorForName({isToday, name}, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));

            this.list = res.data;
            this.oneLoad = false;
        }

        link (item: IOBJ) {
            const type = this.options.type;
            const {photoUrl, doctorName, job, organdoctorId, departmentId, regdate} = item;
            const dataStr = utils.serialize({
                photoUrl,
                job,
                organdoctorId,
                type,
                regdate,
                name: doctorName,
                departmentorganId: departmentId
            });
            utils.link(`/pages/registration/doctor?${dataStr}`);
        }

        onLoad (options: IOBJ) {
            // type 0今日 2预约
            this.options = options;
            this.isToday = this.options.type === '0' ? 0 : 1;
            uni.setNavigationBarTitle({
                title: `搜索医生(${this.options.type === '0' ? '今日' : '七日内'})`
            });
        }

        created () {
            this.input = utils.throttle(this.input, 500);
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box{
        // padding:0;
        background: #fff;
        min-height: 100%;
    }

    .search-box {
        padding: 20rpx;
        box-shadow: 0 0 8rpx rgba(100, 101, 102, 0.08);
        height: 100rpx;
        position: sticky;
        top: 0;
        background: #fff;
        left: 0;
        z-index:3;
        // margin-bottom: 32rpx;
    }

    .fake-height{height: 32rpx;}

    .item-img{width: 96rpx;height: 96rpx;border-radius: 50%;object-fit: cover;object-position: top;margin-right: 32rpx;overflow: hidden;}
    .item-text-1{font-size: 35.2rpx;}
    .item-text-2{color: $color-grey;margin-top: 10rpx;}
    .item-btn{min-width: 160rpx;}
</style>
