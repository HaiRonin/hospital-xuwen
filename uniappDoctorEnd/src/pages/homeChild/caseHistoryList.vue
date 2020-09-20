<template>
    <view class="z-box">
        <topSort v-model="modalShow" @commit="getData" ref="topSort" class="top-sort"/>

        <view class="common-block rel" v-for="(item, index) in list" :key="index" @tap="link(item)">
            <view class="flex-box align-center row">
                <view class="text-1">挂号时间:</view>
                <view class="text-2 main-color">{{item.regtime}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">患者姓名:</view>
                <view class="text-2">{{item.name}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">患者年龄:</view>
                <view class="text-2">{{item.age}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">门诊流水号:</view>
                <view class="text-2">{{item.mzregno}}</view>
            </view>
            <view class="flex-box align-center row">
                <view class="text-1">卡号:</view>
                <view class="text-2">{{item.cardno}}</view>
            </view>

            <u-icon name="arrow-right" class="z-icon abs"></u-icon>
        </view>

        <u-empty v-if="!modalShow && !list.length && !oneLoad" text="暂无数据" mode="list" margin-top="150" icon-size="200" font-size="36"></u-empty>

        <upDataJobNum ref="upDataJobNum"/>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import topSort from '@/components/topSort.vue';
    import upDataJobNum from '@/components/upDataJobNum.vue';
    import {getDocAnalysis} from '@/apis';

    @Component({
        components: {
            topSort, upDataJobNum
        }
    })
    export default class CaseHistoryList extends Vue {
        @Ref('topSort') readonly topSort!: IOBJ;
        @Ref('upDataJobNum') readonly upDataJobNum!: IOBJ;

        modalShow = true;
        oneLoad = true;
        params: IOBJ = {};
        list: IOBJ[] = [];
        options: IOBJ = {};
        clearRenderList: null | TClearRenderList = null;

        link (item: IOBJ) {
            const {endDate, startDate} = this.topSort.params;
            const type = this.options.type;
            const strData = utils.serialize({
                cardNo: item.cardno,
                endDate,
                startDate,
            });

            switch (type) {
                case '3':
                    utils.link(`/pages/homeChild/reportData/index?lsInOut=1&${strData}`);
                    break;
                case '2':
                    // utils.link(`/pages/homeChild/referralCenterList?${strData}`);
                    this.upDataJobNum.openFun({cardNo: item.cardno});
                    break;
                default:
                    utils.link(`/pages/homeChild/caseHistoryDetail?${strData}`);
                    break;
            }
        }

        async getData (data: IOBJ) {
            console.log(data);
            if (!data) return;

            this.clearRenderList && this.clearRenderList();
            const list = this.list = [];

            Object.assign(data, this.params);
            const res = await getDocAnalysis(data, {isLoad: true, closeErrorTips: true}).catch(() => ({data: []}));


            // res.data.forEach((item: IOBJ) => {
            //     item.reportDate = (item.reportDate || '').replace(/\//g, '-');
            //     if (item.contentpicsrc) {
            //         const arr = item.contentpicsrc.split('/');
            //         item.contentpicsrcText = arr[arr.length - 1];
            //     }
            // });

            this.oneLoad = false;

            this.clearRenderList = utils.renderList({
                list,
                data: res.data,
            });
        }

        onLoad (options: IOBJ) {
            // type 1查看患者门诊病历 2门诊医生转诊 3门诊报告查询
            this.options = options;
        }

        created () {}

        mounted () {}

        activated () {}

        beforeDestroy () {
            this.clearRenderList && this.clearRenderList();
        }

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
        min-width: 180rpx;
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
