
<template>
    <view class="box">
        <view class="common-block common-block-1">
            <view class="flex-box info-box">
                <view class="info-img">
                    <u-image :src="(info.photoUrl) || queryInfo.photoUrl || require('@/assets/image/d-male.png')" height="100%" mode="widthFix" ></u-image>
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
            @tap="linkDownOrder(item)"
        >
            <view class="flex-1 item-text-1">{{item.timestypeNoName}}</view>
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

        <view class="error-text" v-if="!oneLoad && !list.length">没有相关信息</view>
    </view>
</template>

<script lang="ts">
    import {Component, Vue, Prop} from 'vue-property-decorator';
    import {queryDoctorById, queryToRegDoctorTimes, queryDepartmentById} from '@/apis';


    @Component
    export default class Doctor extends Vue {

        options: IOBJ = {};
        dateText: string = '';
        toDay: boolean = true;
        info: IOBJ = {};
        queryInfo: IOBJ = {};
        departmentInfo: IOBJ = {};
        reserveTimeList: IOBJ[] = [];
        oneLoad = true;

        list: IOBJ[] = [
            // {show: true, num: 0, price: 0, disabled: true, key: 1, text: '上午'},
            // {show: true, num: 0, price: 0, disabled: true, key: 2, text: '中午'},
            // {show: true, num: 0, price: 0, disabled: true, key: 3, text: '下午'},
            // {show: true, num: 0, price: 0, disabled: true, key: 4, text: '晚上'},
            // {show: true, num: 0, price: 0, disabled: true, key: 5, text: '凌晨'},
        ];

        linkDownOrder (item: IOBJ) {
            const {organdoctorId, departmentorganId, timestypeNo, sourceDate, sourceTimeType, consultationFee, timestypeNoName} = item;
            const toDay = this.toDay;
            const departmentorganName = this.departmentInfo.name;
            const doctorName = this.info.name;
            const sourceItem = globalConfig.sourceTimeType.find(ii => ii.value === sourceTimeType);
            const sourceDateText = `${item.sourceDate.split(' ')[0]}　${toDay ? sourceItem!.text : timestypeNoName}`;
            // organdoctorId: 6461
            // departmentorganId: 687
            // sourceDate: 2020-09-02
            // timestypeNo: R4438083
            // sourceTimeType: 5

            // 支付金额
            // payAmount: 3

            // 下面两个 就诊卡上拿到
            // patientNo: 99202008290495
            // cardNo: 44058219930826557X

            // 这个不清楚
            // socialsecurityNO:

            const strData = utils.serialize({
                organdoctorId,
                doctorName,
                timestypeNo,
                sourceDate,
                sourceDateText,
                sourceTimeType,
                departmentorganId,
                departmentorganName,
                payAmount: consultationFee,
                socialsecurityNO: ''
            });

            // utils.link(`/pages/confirmOrder?${strData}`);
            utils.link(`/pages/registration/confirmOrder?${strData}`);
        }

        // 判断预约携带时间是否无效的
        isRegdateInvalid (regdate: string, startTime: number, endTime: number) {
            // regdate 为空处理
            if (!regdate) return true;
            const {time} = utils.dateData(new Date(`${regdate.replace(/-/g, '/')} 00:00:00`));

            if (time < startTime || time > endTime) {
                return true;
            }

            return false;
        }

        // 创建七天时间
        createSevenDay () {
            const regdate = this.options.regdate;

            const date = new Date(this.$store.getters.systemTime);
            const arr = [];
            for (let i = 0; i < 7; i++) {
                date.setDate(date.getDate() + 1);
                const {text, week, d, time} = utils.dateData(date);
                arr.push({
                    date: text,
                    week,
                    day: d,
                    time
                });
            }
            // console.log(arr);
            this.reserveTimeList = arr;

            const isInvalid = this.isRegdateInvalid(regdate, arr[0].time, arr[arr.length - 1].time);
            this.getReserveTime(isInvalid ? arr[0].date : regdate);
        }

        // 获取医生信息
        async getDoctorInfo (organdoctorId: string) {
            // queryDoctorById
            // organdoctorId
            const res = await queryDoctorById({organdoctorId});

            this.info = (res.data && res.data[0]) || {};
            this.info.photoUrl = (this.info.photoUrl || '').trim();
        }

        // 根据医生查询号源
        async getReserveTime (date?: string) {
            // querytoregdoctorListbydoctorid
            if (date) {
                this.dateText = date;
            } else {
                const dObj = new Date(this.$store.getters.systemTime);
                const {text, week} = utils.dateData(dObj);
                date = text;
            }
            // const res = await queryToRegDoctorListByDoctorId({
            //     startDate: date,
            //     endDate: date,
            //     organdoctorId: this.options.organdoctorId,
            // }).catch(() => {
            //     this.list.forEach((item) => {
            //         item.show = false;
            //     });
            //     return Promise.reject();
            // });

            // const data: IOBJ = res.data[0];

            // this.list.forEach((item) => {
            //     const num: string | undefined = data[item.key];
            //     // debugger;
            //     if (utils.zEmpty(num)) {
            //         item.show = false;
            //         return;
            //     }

            //     item.show = true;
            //     item.num = Math.max(+(num || 0), 0);
            //     item.disabled = item.num <= 0;
            //     item.price = data.consultationFee;
            // });


            const {departmentorganId, organdoctorId} = this.options;
            const sourceTimeType = globalConfig.sourceTimeType;
            // const fnArr: any[] = [];

            utils.showLoad();

            // sourceTimeType.forEach((item) => {
            //     fnArr.push(
            //         queryToRegDoctorTimes({
            //             departmentorganId,
            //             organdoctorId,
            //             sourceTimeType: item.value,
            //             sourceDate: date
            //         }, {closeErrorTips: true})
            //     );
            // });
            // const resList = await Promise.allSettled(fnArr);

            const resList: IOBJ[] = [];
            for (const item of sourceTimeType) {
                await queryToRegDoctorTimes({
                    departmentorganId,
                    organdoctorId,
                    sourceTimeType: item.value,
                    sourceDate: date
                }, {closeErrorTips: true}).then((value) => {
                    resList.push({status: 'fulfilled', value});
                }).catch((value) => {
                    resList.push({status: 'rejected', value});
                });
            }

            const list: IOBJ[] = [];
            resList.forEach((item: IOBJ) => {
                if (item.status === 'rejected') return;
                const data = item.value.data;
                data.forEach((child: IOBJ) => {
                    const sType = sourceTimeType.find(ii => ii.value === child.sourceTimeType);
                    if (!sType) return;

                    list.push(Object.assign({
                        show: true,
                        num: child.SourceDateNum,
                        price: child.consultationFee,
                        // disabled: true,
                        // key: 5,
                        text: sType.text
                    }, child));
                });
            });

            this.list = list;
            this.oneLoad = false;
            utils.hideLoad();
        }

        // 获取部门信息
        async getDepartmentInfo () {
            const departmentorganId = this.options.departmentorganId;
            const res = await queryDepartmentById({organId: departmentorganId}, {closeErrorTips: true}).catch((res) => ({data: res.data}));
            this.departmentInfo = res.data;
        }

        onLoad (options: IOBJ) {
            // photoUrl, name, job, departmentorganId, type, regdate, organdoctorId
            this.options = options;
        }

        created () {
            // const {photoUrl, name, job, type} = this.$route.query;
            const {photoUrl, name, job, type, organdoctorId} = this.options;
            const toDay = this.toDay = (type || '2') + '' === '0';

            utils.setPageTitle(toDay ? '挂号列表' : '预约列表');

            this.queryInfo = {photoUrl, name, job};
            this.getDepartmentInfo();
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

    .item-text-1{min-width: 220rpx;}
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

    .error-text{
        color: rgb(96, 98, 102);
        font-size: 28rpx;
        text-align: center;
    }
</style>

