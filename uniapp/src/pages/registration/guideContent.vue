<template>
    <view class="z-box">
        <view v-if="guideDataArr && guideDataArr.length">
            <view class="common-block content" v-for="(guideData, index) in guideDataArr" :key="index">
                <view class="flex-box align-center flex-wrap">
                    <view class="flex-100">
                        <text class="text-1">申请流水号:</text>
                        <text class="text-2">{{guideData.autonumbsf}}</text>
                    </view>
                    <view class="flex-100 flex-box">
                        <text class="text-1"></text>
                        <text class="text-2" v-if="guideData.errorImg">条形码加载失败</text>
                        <img class="code-img" v-else :src="guideData.codeImg" />
                    </view>
                    <view class="flex-100">
                        <text class="text-1">病人ID:</text>
                        <text class="text-2">{{guideData.patientID}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">患者姓名:</text>
                        <text class="text-2">{{guideData.patientName}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">患者性别:</text>
                        <text class="text-2">{{guideData.patientSex}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-1">年龄:</text>
                        <text class="text-2">{{+guideData.patientAge}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">开单时间:</text>
                        <text class="text-2">{{guideData.createDate}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">处方号:</text>
                        <text class="text-2">{{guideData.recipeNo}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">开单科室:</text>
                        <text class="text-2">{{guideData.deptName}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">开单医生:</text>
                        <text class="text-2">{{guideData.doctorName}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">诊断:</text>
                        <text class="text-2">{{guideData.psam}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">执行科室:</text>
                        <text class="text-2">{{guideData.exeDept}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">套组名称:</text>
                        <text class="text-2">{{guideData.tcmc}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-1">套组类型:</text>
                        <text class="text-2">{{guideData.tcType}}</text>
                    </view>
                    <template v-if="guideData.tcType === '检查'">
                        <view class="flex-100">
                            <text class="text-1">检查目的:</text>
                            <text class="text-2">{{guideData.jcmd}}</text>
                        </view>
                        <view class="flex-100">
                            <text class="text-1">检查类别:</text>
                            <text class="text-2">{{guideData.jclb}}</text>
                        </view>
                    </template>
                    <template v-if="guideData.tcType === '检验'">
                        <view class="flex-100">
                            <text class="text-1">检验标本:</text>
                            <text class="text-2">{{guideData.jybb}}</text>
                        </view>
                        <view class="flex-100">
                            <text class="text-1">检验采集器:</text>
                            <text class="text-2">{{guideData.jycjq}}</text>
                        </view>
                    </template>
                </view>
                <!-- <view class="z-title">清单</view>
                <view class="flex-box align-center flex-wrap child-box" v-for="(item, index) in guideData.feeItemList" :key="index">
                    <view class="flex-100">
                        <text class="text-3" style="width:auto;">申请流水号:</text>
                        <text class="text-4">{{item.autonumbsf}}</text>
                    </view>
                    <view class="flex-100">
                        <text class="text-3">名称:</text>
                        <text class="text-4">{{item.feeItemName}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-3">数量:</text>
                        <text class="text-4">{{item.feeItemNum}}{{item.feeItemUnit}}</text>
                    </view>
                    <view class="flex-50">
                        <text class="text-3">单位:</text>
                        <text class="text-4">{{item.feeItemUnit}}</text>
                    </view>
                </view> -->
            </view>
        </view>

        <u-empty text="暂无信息" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && (!guideDataArr || !guideDataArr.length)"></u-empty>

    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {createdBarCode, queryDrugWindowsInfo} from '@/apis';

    @Component
    export default class GuideContent extends Vue {

        oneLoad = true;
        guideDataArr: IOBJ[] | null = null;
        query: IOBJ = {};

        async getData () {
            // console.log(1232)
            this.oneLoad = true;

            const res = await queryDrugWindowsInfo({recipeNos: this.query.serialNumber}, {isLoad: true}).catch(() => {
                return {data: []};
            });
            const data = res.data;

            if (utils.zEmpty(data) || !data.length) {
                this.oneLoad = false;
                return;
            }

            // data.sort((a: IOBJ, b: IOBJ) => {
            //     return Date.parse(b.createDate) - Date.parse(a.createDate);
            // });

            try {
                const arr: Promise<IOBJ>[] = [];
                data.forEach((item: IOBJ) => {
                    arr.push(
                        createdBarCode({CardNo: item.autonumbsf}, {closeErrorTips: true, isLoad: true, noCloseBeforeAjax: true}).then((res) => {
                            item.codeImg = 'data:image/png;base64,' + res.msg;
                            return Promise.resolve(res);
                        }).catch(() => {
                            item.errorImg = true;
                            return Promise.reject();
                        })
                    );
                })
                // const resImg = await createdBarCode({CardNo: data.autonumbsf}, {closeErrorTips: true, isLoad: true}).catch(() => {
                //     data.errorImg = true;
                //     return Promise.reject();
                // });

                await Promise.all(arr);
                // data.codeImg = 'data:image/png;base64,' + resImg.msg;
            } catch (error) {
            }

            this.guideDataArr = data;
            this.oneLoad = false;
        }

        onLoad (options: IOBJ) {
            this.query = options;
        }

        created () {
            this.getData();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content{
        // padding: 0 40rpx;
        font-size: 28rpx;
        line-height: 70rpx;
        padding: 30rpx 20rpx;
    }

    .text-1{color: $color-grey;width: 198rpx;display: inline-block;text-align: right;padding-right: 20rpx;}
    .text-2{}

    .z-title{
        border-bottom: 0;
        border-top: $border-line;
        padding: 0;
    }

    .child-box{border:$border-line;}

    .child-box + .child-box{margin-top: 32rpx;}

    .text-3{color: $color-grey;width: 100rpx;display: inline-block;text-align: right;padding-right: 20rpx;}

    .z-btn-box{
        position: sticky;
        bottom: 0;
        left: 0;
        right: 0;
        font-size: 32rpx;
        line-height: 46rpx;
        box-shadow: 0 -2rpx 6rpx 0px $color-weak-grey;
        padding: 10rpx 30rpx;
    }

    .zbb-text-1{
        font-size: 28rpx;
    }

    .zbb-btn{
        top: 0rpx;
        right: 0;
        bottom: 0;
        background: $main-color;
        width: 280rpx;
        font-size: 40rpx;
        color: #fff;
        box-shadow: 0 -2rpx 6rpx 0px $color-weak-grey;
    }

    .flex-50{flex-basis: 50%;}
    .flex-100{flex-basis: 100%;}

    .code-img{
        height: 160rpx;
    }
</style>
