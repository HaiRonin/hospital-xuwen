<template>
    <view class>
        <view class="flex-box align-center top">
            <view class="text-1 flex-1">
                <template v-if="params.startDate && params.endDate">
                    时间范围：
                    <text class="main-color">{{params.startDate}}~{{params.endDate}}</text>
                </template>
                <template v-else>时间范围</template>
            </view>
            <view class="text-2">
                <text class="main-color">{{params.xType === '2' ? '检查' : '检验'}}</text>报告
            </view>
        </view>
        <view class="sort-block" v-if="modalShow">
            <view class="title-3">筛选条件</view>
            <view @tap="timeShow = true" class="title-1">
                <template v-if="params.startDate && params.endDate">{{params.startDate}}~{{params.endDate}}</template>
                <text v-else class="color-grey">请选择时间范围</text>
            </view>
            <view class="flex-box align-center">
                <view class="title-2">报告类型</view>
                <u-radio-group v-model="params.xType">
                    <u-radio v-for="(item, index) in reportTypeSort" :key="index" :name="item.value">{{item.text}}</u-radio>
                </u-radio-group>
            </view>

            <view class="z-btn" @tap="query">查询</view>
        </view>
        <view class="flex-box align-center justify-center sort-control" @tap="controlModal">
            <u-icon name="arrow-up" v-if="modalShow" class="z-icon"></u-icon>
            <u-icon name="arrow-down" v-else class="z-icon"></u-icon>
        </view>

        <u-calendar
            v-model="timeShow"
            active-bg-color="#463f3a"
            range-color="#463f3a"
            range-bg-color="#bcb8b1"
            mode="range"
            @change="timeChange"
            class="z-calendar"
        ></u-calendar>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Emit, Model} from 'vue-property-decorator';

    @Component
    export default class TopSort extends Vue {
        @Model('change', {type: Boolean}) readonly modalShow!: boolean;

        // modalShow = true;
        timeShow = false;
        params: IOBJ = {
            xType: '1',
            endDate: '',
            startDate: ''
        };

        reportTypeSort: IOBJ[] = [
            {text: '检验', value: '1'},
            {text: '检查', value: '2'},
        ];

        timeChange (item: IOBJ) {
            // console.log(item);
            const params = this.params;
            params.startDate = item.startDate;
            params.endDate = item.endDate;
        }

        controlModal () {
            const modalShow = this.modalShow;
            // this.modalShow = !modalShow;
            this.change(!modalShow);
            if (modalShow) {
                this.query();
            }
        }

        @Emit()
        change (val: boolean) {
            return val;
        }

        @Emit('commit')
        query () {
            const params = this.params;

            if (utils.zEmpty(params.startDate) || utils.zEmpty(params.endDate)) {
                utils.toast('请筛选时间');
                return null;
            }

            // this.modalShow = false;
            this.change(false);
            return utils.jsCopyObj(params);
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .top {
        background: #fff;
        font-size: 30rpx;
        position: relative;
        z-index: 2;
        line-height: 80rpx;
        padding: 0 24rpx;
    }

    .z-calendar {
        font-size: 28rpx;
    }

    .title-1 {
        text-align: center;
        line-height: 80rpx;
        border: $border-line;
        border-radius: 50rpx;
        font-size: 32rpx;
        margin-bottom: 80rpx;
        border-color: $main-color;
    }

    .title-2 {
        margin-right: 80rpx;
        margin-left: 10rpx;
    }

    .title-3 {
        text-align: center;
        margin-bottom: 50rpx;
        font-size: 36rpx;
        font-weight: bold;
    }

    .z-btn {
        color: #fff;
        background: $main-color;
        text-align: center;
        line-height: 80rpx;
        border-radius: 10rpx;
        margin-top: 80rpx;
    }

    .sort-block {
        margin: 0;
        background-color: #fff;
        padding: 24rpx 16rpx;
        font-size: 28rpx;
    }

    .sort-control {
        background: #ebedf0;
        color: #909399;
        border-top: $border-line;
        border-bottom: $border-line;
        font-size: 36rpx;
    }

    .main-color {
        color: $main-color;
    }

    .color-grey {
        color: $color-grey;
    }
</style>
