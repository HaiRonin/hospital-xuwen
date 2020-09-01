<template>
    <view class="z-box">
        <view class="common-block flex-box align-center tiem-box" v-if="reserveTimeList.length">
            <view
                v-for="(item, index) in reserveTimeList"
                :key="index"
                class="flex-1 tb-item"
                :class="{cur: params.date === item.date}"
                @tap="params.date = item.date;getList()"
            >
                <view class="tb-text-1">{{item.week}}</view>
                <view class="tb-text-2">{{item.day}}</view>
            </view>
        </view>

        <view v-for="(item, index) in list" :key="index" class="flex-box justify-s-b align-center common-block">
            <view class="text-1 flex-1">{{item.locationName}}</view>
            <view class="text-2">{{item.doctorName}}</view>
            <view class="text-3">{{item.regTimeName}}</view>
        </view>


        <u-empty text="没有出诊安排" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="!oneLoad && !list.length"></u-empty>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {getDoctorRegList} from '@/apis';

    @Component
    export default class DoctorTimeing extends Vue {

        reserveTimeList: IOBJ[] =[];
        list: IOBJ[] =[];
        oneLoad = true;
        params: IOBJ = {
            date: '',
        };

        // 创建七天时间
        createSevenDay () {
            const date = new Date(this.$store.getters.systemTime);
            // date.setDate();
            const arr = [];
            for (let i = 0; i < 7; i++) {
                date.setDate(date.getDate() + 1);
                const {text, week, d} = utils.dateData(date);
                arr.push({
                    date: text,
                    week,
                    day: d,
                });
            }
            this.reserveTimeList = arr;
            this.params.date = arr[0].date;

            this.getList();
        }

        async getList () {
            const date = this.params.date;
            const res = await getDoctorRegList({regDate: date}, {isLoad: true, closeErrorTips: true}).catch(() => {
               return {data: []};
            });
            console.log(res);

            this.list = res.data;
            this.oneLoad = false;
        }

        onPullDownRefresh () {
            utils.pullDown(() => this.getList());
        }

        created () {
            this.createSevenDay();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-box{
        overflow: initial;
        padding-top: 0;
    }

    .tiem-box {
        text-align: center;
        position: sticky;
        top: 0;
        left: 0;
        background: #fff;
        margin: 0 0 32rpx;
        border-radius:0;
    }

    .tb-text-2 {
        width: 48rpx;
        height: 48rpx;
        line-height: 48rpx;
        // background: #000;
        margin: 16rpx auto 0;
        border-radius: 50%;
        color: $main-color;
    }

    .tb-item.cur .tb-text-2 {
        background: $main-cur-color;
        color: #fff;
        font-size: 25.6rpx;
    }

    .text-2{width:200rpx;color: #ff976a;}
    .text-3{width: 100rpx;color: $main-color;}
</style>
