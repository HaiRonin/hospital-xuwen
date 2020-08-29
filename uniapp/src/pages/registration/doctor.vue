
<template>
    <view class="box">
        <view class="common-block common-block-1">
            <view class="flex-box info-box">
                <view class="info-img">
                    <u-image :src="info.photoUrl || queryInfo.photoUrl || require('@/assets/image/d-male.png')" height="100%" mode="widthFix" ></u-image>
                </view>
                <view class="flex-1">
                    <view class="info-text-1">{{info.name || queryInfo.name}}</view>
                    <view class="info-text-2">{{info.job || queryInfo.job}}</view>
                </view>
            </view>
            <view class="info-text-3" v-show="info.ddesc">医生简介：{{info.ddesc}}</view>
        </view>

        <view class="common-block flex-box align-center tiem-box" v-if="reserveTimeList.length">
            <view
                v-for="(item, index) in reserveTimeList"
                :key="index"
                class="flex-1 tb-item"
                :class="{cur: dateText === item.date}"
                @tap="getReserveTime(item.date)"
            >
                <view class="tb-text-1">{{item.week}}</view>
                <view class="tb-text-2">{{item.day}}</view>
            </view>
        </view>

        <view
            v-for="(item, index) in list"
            :key="index"
            class="common-block flex-box align-center rel"
            v-show="item.show"
        >
            <view class="flex-1 item-text-1">{{item.text}}</view>
            <view class="flex-1">
                剩余
                <span class="num-text">{{item.num}}</span>个号
            </view>
            <view class="item-text-3">
                <span class="price-text">{{item.price}}</span>元
            </view>
            <view class="flex-1 flex-box align-center justify-end">
                {{item.disabled ? '不可挂号' : '可挂号'}}
                <u-icon name="arrow-right" class="item-icon"></u-icon>
            </view>
            <view class="abs item-disabled" v-show="item.disabled"></view>
        </view>
    </view>
</template>

<script lang="ts">
    import {Component, Vue, Prop} from 'vue-property-decorator';
    import {queryDoctorById, queryToRegDoctorListByDoctorId} from '@/apis';


    @Component
    export default class Doctor extends Vue {
        @Prop() readonly departmentorganId!: string;

        options: IOBJ = {};
        dateText: string = '';
        toDay: boolean = true;
        info: IOBJ = {};
        queryInfo: IOBJ = {};
        reserveTimeList: IOBJ[] = [];
        list: IOBJ[] = [
            {show: true, num: 0, price: 0, disabled: true, key: 'morningNum', text: '上午'},
            {show: true, num: 0, price: 0, disabled: true, key: 'noonNum', text: '中午'},
            {show: true, num: 0, price: 0, disabled: true, key: 'afternoonNum', text: '下午'},
            {show: true, num: 0, price: 0, disabled: true, key: 'eveningNum', text: '晚上'},
            {show: true, num: 0, price: 0, disabled: true, key: 'earlymorningNum', text: '凌晨'},
        ];

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
            // console.log(arr);
            this.reserveTimeList = arr;
            // this.dateText = arr[0].date;
            this.getReserveTime(arr[0].date);
        }

        // 获取医生信息
        async getDoctorInfo (organdoctorId: string) {
            // queryDoctorById
            // organdoctorId
            const res = await queryDoctorById({organdoctorId});

            this.info = (res.data && res.data[0]) || {};
        }

        // 根据医生查询号源
        async getReserveTime (date?: string) {
            // querytoregdoctorListbydoctorid
            if (date) {
                this.dateText = date;
            } else {
                const dObj = new Date();
                const {text, week} = utils.dateData(dObj);
                date = text;
            }
            const res = await queryToRegDoctorListByDoctorId({
                startDate: date,
                endDate: date,
                organdoctorId: this.options.organdoctorId,
            }).catch(() => {
                this.list.forEach((item) => {
                    item.show = false;
                });
                return Promise.reject();
            });

            const data: IOBJ = res.data[0];

            this.list.forEach((item) => {
                const num: string | undefined = data[item.key];
                // debugger;
                if (utils.zEmpty(num)) {
                    item.show = false;
                    return;
                }

                item.show = true;
                item.num = Math.max(+(num || 0), 0);
                item.disabled = item.num <= 0;
                item.price = data.consultationFee;
            });
        }

        onLoad (options: IOBJ) {
            // photoUrl, name, job, departmentorganId, type
            this.options = options;
        }

        created () {
            // const {photoUrl, name, job, type} = this.$route.query;
            const {photoUrl, name, job, type, organdoctorId} = this.options;
            const toDay = this.toDay = (type || '2') + '' === '0';
            // console.log(this.departmentorganId);

            utils.setPageTitle(toDay ? '挂号列表' : '预约列表');

            this.queryInfo = {photoUrl, name, job};
            this.getDoctorInfo(organdoctorId);
            toDay ? this.getReserveTime() : this.createSevenDay();
        }
    }
</script>

<style lang="scss" scoped>
    // .common-block-1 {
    //     margin-top: 32rpx;
    // }
    .box {
        padding-top: 32rpx;
    }
    .info-img {
        width:128rpx;
        height: 160rpx;
        object-fit: cover;
        object-position: top;
        margin-right: 32rpx;
    }
    .info-text-1 {
        font-size: 35.2rpx;
    }
    .info-text-2 {
        color: $color-grey;
        margin-top: 12.8rpx;
    }
    .info-text-3 {
        margin-top: 25.6rpx;
        padding-top: 25.6rpx;
        border-top: $border-line;
        color: $color-grey;
        font-size: 28.8rpx;
        white-space: 1.5;
        line-height: 1.5;
    }

    // .item-text-1{width: 40%;}
    .item-text-3 {
        min-width: 96rpx;
    }
    .num-text {
        // color: #07c160;
        color: $main-color;
    }
    .price-text {
        color: #ff976a;
    }
    .item-icon {
    }

    // .item-disabled{background: #999;}
    .item-disabled {
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        background: rgb(255 255 255 / 50%);
    }

    .tiem-box {
        text-align: center;
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
</style>

