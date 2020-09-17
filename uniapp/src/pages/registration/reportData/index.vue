<template>
    <view class="box">
        <topSort v-model="modalShow" @commit="getData" ref="topSort" class="top-sort" />

        <view class="common-block" v-for="(item, index) in list" :key="index">
            <view class="flex-box justify-s-b">
                <view class="text-1">检查名称:</view>
                <view class="text-2">{{item.checkName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">报告编号:</view>
                <view class="text-2">{{item.reportNo}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">患者姓名:</view>
                <view class="text-2">{{item.patientName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">报告状态:</view>
                <view class="text-2">{{item.reportStatus | f_reportStatus}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">报告的类型:</view>
                <view class="text-2">{{item.reportType | f_reportType}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">检查医生:</view>
                <view class="text-2">{{item.chekckdoctorName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">送检医生:</view>
                <view class="text-2">{{item.doctorName}}</view>
            </view>
            <view class="flex-box align-center justify-s-b">
                <view class="text-1">检查时间:</view>
                <view class="text-2">{{item.reportDate}}</view>
            </view>
            <view class="flex-box justify-s-b">
                <view class="text-1">描述:</view>
                <view class="text-2">{{item.checkDesc}}</view>
            </view>
            <view class="flex-box justify-s-b">
                <view class="text-1">结果:</view>
                <view class="text-2 main-color" v-if="item.contentpicsrcText" @tap="down(item.contentpicsrc)">{{item.contentpicsrcText}}</view>
                <view class="text-2" v-else>{{item.checkResult}}</view>
            </view>
        </view>

        <u-empty v-if="!modalShow && !list.length && !oneLoad" text="暂无检查报告数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import topSort from './topSort.vue';
    import {uspGetPacsApp} from '@/apis';

    @Component({
        components: {
            topSort
        },
        filters: {
            f_reportStatus: (val: any) => globalConfig.gFilter(val, globalConfig.reportStatus),
            f_reportType: (val: any) => globalConfig.gFilter(val, globalConfig.reportType),
        }
    })
    export default class Index extends Vue {
        @Ref('topSort') readonly topSort!: IOBJ;

        modalShow = true;
        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};

        down (url: string) {
            if (utils.zEmpty(url)) return;

            // #ifdef H5
            window.open(url);
            // #endif

            // #ifdef APP-PLUS
            plus.runtime.openURL(url);
            // #endif
        }

        async getData (data: IOBJ) {
            console.log(data);
            if (!data) return;
            Object.assign(data, this.params);
            const res = await uspGetPacsApp(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));


            res.data.forEach((item: IOBJ) => {
                item.reportDate = (item.reportDate || '').replace(/\//g, '-');
                if (item.contentpicsrc) {
                    const arr = item.contentpicsrc.split('/');
                    item.contentpicsrcText = arr[arr.length - 1];
                }
            });

            this.oneLoad = false;
            this.list = res.data;
        }

        onLoad (options: IOBJ) {
            // lsInOut 1.门诊 2.住院
            const lsInOut = this.params.lsInOut = options.lsInOut || '1';
            this.params.patientNo = options.patientNo;
            this.options = options;
            uni.setNavigationBarTitle({
                title: `${lsInOut === '2' ? '住院' : '门诊'}报告查询`
            });
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box {
        background: #fff;
        min-height: 100%;
    }

    .top-sort {
        position: sticky;
        top: 0;
        left: 0;
        right: 0;
        margin-bottom: 32rpx;
    }

    .common-block{
        line-height: 60rpx;
    }

    .text-1{
        color:$color-grey;
        padding-right: 20rpx;
        min-width: 180rpx;
    }

    .text-2{text-align: right;}

    .check-name{line-height: 40rpx;padding-bottom: 10rpx;}
</style>
