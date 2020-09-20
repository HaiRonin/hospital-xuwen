<template>
    <view class="z-box">
        <topSort v-model="modalShow" @commit="getData" ref="topSort" class="top-sort" :showCard="false"/>

        <view class="common-block rel" v-for="(item, index) in list" :key="index">
            <view class="flex-box align-center row">
                <view class="text-1">统计时间:</view>
                <view class="text-2 main-color">{{topSort.params.startDate}} 至 {{topSort.params.startDate}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">挂号人数:</view>
                <view class="text-2">{{item.regnum}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">门诊开单收入:</view>
                <view class="text-2">{{item.docget}}</view>
            </view>
            <!-- <u-icon name="arrow-right" class="z-icon abs"></u-icon> -->
        </view>

        <u-empty v-if="!modalShow && !list.length && !oneLoad" text="暂无数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import topSort from '@/components/topSort.vue';
    import {getDocWork} from '@/apis';

    @Component({
        components: {
            topSort
        }
    })
    export default class Statistics extends Vue {
        @Ref('topSort') readonly topSort!: IOBJ;

        modalShow = true;
        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};

        async getData (data: IOBJ) {
            console.log(data);
            if (!data) return;
            Object.assign(data, this.params);
            const res = await getDocWork(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));


            // res.data.forEach((item: IOBJ) => {
            //     item.reportDate = (item.reportDate || '').replace(/\//g, '-');
            //     if (item.contentpicsrc) {
            //         const arr = item.contentpicsrc.split('/');
            //         item.contentpicsrcText = arr[arr.length - 1];
            //     }
            // });

            this.oneLoad = false;
            this.list = res.data;
        }

        onLoad (options: IOBJ) {
            this.options = options;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-box{
        padding-top: 0;
    }

    .top-sort {
        position: sticky;
        top: 0;
        left: 0;
        right: 0;
        margin-bottom: 32rpx;
        z-index: 2;

    }

    .common-block{
        line-height: 60rpx;
    }
    .text-1{
        color:$color-grey;
        padding-right: 20rpx;
        min-width: 200rpx;
        text-align: right;
        padding-right: 20rpx;
    }

    .z-icon{
        right: 24rpx;
        top: 50%;
        transform: translateY(-50%);
        color: $color-grey;
    }
</style>
