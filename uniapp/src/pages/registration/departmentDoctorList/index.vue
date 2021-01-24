
<template>
    <view class="box">
        <view class="top-box">
            <view class="flex-box tap-style">
                <view class="flex-box align-center justify-center flex-1" @tap="leftModal.openFun()">
                    {{(leftModal && leftModal.curText) || '科室'}}
                    <u-icon name="arrow-down" class="tap-icon"></u-icon>
                </view>
                <view class="flex-box align-center justify-center flex-1" @tap="leftModal2.openFun()">
                    {{params.theTitle || '医生职称'}}
                    <u-icon name="arrow-down" class="tap-icon" v-if="theTitleList.length"></u-icon>
                </view>
            </view>
            <u-alert-tips v-if="params.type === '0' && (leftModal && leftModal.curItem && ['1', '6'].includes(leftModal.curItem.type))" type="primary" class="z-custom" show-icon description="请提前与该科室医生联系，方可挂号，以免找不到医生"></u-alert-tips>

            <view class="line-text">
                <text>{{this.params.type === '2' ? '非当天预约挂号' : '当天预约挂号'}}</text>
            </view>

            <template v-if="this.params.type === '2'">
                <view class="common-block flex-box align-center tiem-box">
                    <view
                        v-for="(item, index) in reserveTimeList"
                        :key="index"
                        class="flex-1 tb-item"
                        :class="{cur: params.startDate === item.date}"
                        @tap="getList(item.date)"
                    >
                        <view class="tb-text-1">{{item.week}}</view>
                        <view class="tb-text-2">{{item.day}}</view>
                    </view>
                </view>
                <view class="fake-height"></view>
            </template>
        </view>


        <view>
            <view v-for="(item, index) in list" :key="index" class="flex-box align-center justify-s-b common-block item" @tap="link(item)">
                <view class="item-img">
                    <u-image :src="item.photoUrl || require('@/assets/image/d-male.png')" height="100%" mode="widthFix"></u-image>
                </view>
                <view class="flex-1">
                    <view class="item-text-1">{{item.name}}</view>
                    <view class="item-text-2">{{item.job}}</view>
                </view>
                <u-button type="primary" plain size="mini" shape="circle" class="item-btn z-btn" @tap="link(item)">挂号</u-button>
            </view>
        </view>

        <u-empty text="没有查到相关医生" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="loadCount === 1 && !list.length"></u-empty>

        <leftModal ref="leftModal" v-model="params.departmentorganId" @change="getList()" />
        <leftModal2 ref="leftModal2" :list="theTitleList" v-model="params.theTitle" @change="theTitleChange" />
    </view>
</template>

<script lang="ts">
    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryDoctorlistSource} from '@/apis';
    import leftModal from './leftModal.vue';
    import leftModal2 from './leftModal2.vue';


    @Component({
        components: {
            leftModal, leftModal2
        }
    })
    export default class DepartmentDoctorList extends Vue {
        @Ref('leftModal') readonly leftModal!: IOBJ;
        @Ref('leftModal2') readonly leftModal2!: IOBJ;

        options: IOBJ = {};
        reserveTimeList: IOBJ[] = [];
        show = true;
        params: IOBJ = {
            departmentorganId: '',
            // theTitle: '',
            // type: '2'
            startDate: '',
            endDate: '',
        };

        loadCount: 0 | 1 = 0;
        theTitleList: IOBJ[] = [];

        oldList: IOBJ[] = [];
        list: IOBJ[] = [];

        link (item: IOBJ) {
            const {type, departmentorganId, startDate} = this.params;
            const {photoUrl, name, job, organdoctorId} = item;
            const dataStr = utils.serialize({
                photoUrl, name, job, organdoctorId, type, departmentorganId, regdate: startDate
            });
            utils.link(`/pages/registration/doctor?${dataStr}`);
        }

        theTitleChange (e: string | undefined) {
            this.list = e === undefined ? [...this.oldList] : this.oldList.filter((item) => item.job === e);
        }

        // 获取医生职位
        handleTheTitle (list: IOBJ[]) {
            const set = new Set();
            // console.log(list);
            list.forEach((item) => {
                !item.job && console.log('少了job', item);
                !!item.job && set.add(item.job);
            });

            this.params.theTitle = undefined;
            this.theTitleList = Array.from(set).map((value: any | string) => ({label: value, value}));
        }

        // 创建七天时间
        createSevenDay () {
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
            this.reserveTimeList = arr;
            // console.log(arr);
            this.getList(arr[0].date);
        }

        async getList (dateText?: string) {
            // console.log(dateText);
            // debugger;
            if (dateText) {
                this.params.startDate = dateText;
                this.params.endDate = dateText;
            }
            // queryDoctorlistSource
            // const departmentorganId = this.params.departmentorganId;
            this.loadCount = 0;
            const params = utils.jsCopyObj(this.params);
            params.type = undefined;
            params.theTitle = undefined;
            const res = await queryDoctorlistSource(params).catch((err) => ({data: []}));

            this.handleTheTitle(res.data);
            this.oldList = res.data;
            this.list = [...res.data];
            this.loadCount = 1;
        }

        // 初始化请求时间
        initTime (type: string) {
            // console.log(type === '0');
            const arr = (type === '0') ? this.$store.getters.day : this.$store.getters.sevenDay;
            // console.log(this.$store.getters);
            this.params.startDate = arr[0];
            this.params.endDate = arr[1];
            // this.$set(this.params, 'startDate', arr[0]);
            // this.$set(this.params, 'endDate', arr[1]);
            // console.log(this.params);
        }

        onPullDownRefresh () {
            utils.pullDown(() => this.getList());
        }

        onLoad (options: IOBJ) {
            // console.log(options);
            this.options = options;
            // departmentorganId 科室id
            // type 0今日 2预约
        }

        created () {
            const {departmentorganId, type} = this.options;
            this.params.departmentorganId = (departmentorganId || '') + '';
            this.params.type = (type || '2') + '';
            this.initTime(this.params.type);

            // this.params.type === '2' && this.createSevenDay();
            // this.params.departmentorganId && this.getList();

            if (this.params.type === '2') {
                this.createSevenDay();
            } else if (this.params.departmentorganId) {
                this.getList();
            }
        }
    }
</script>

<style lang="scss" scoped>
    .box {
        // padding:0;
        background: #fff;
        min-height: 100%;
        padding-bottom: 20rpx;
    }

    .tap-style {
        font-size: 32rpx;
        padding: 20rpx 0;
        box-shadow: 0 0 8rpx rgba(100, 101, 102, 0.08);
    }
    .tap-icon {
        font-size: 16rpx;
        color: $color-grey;
        margin-left: 18rpx;
    }

    .top-box {
        position: sticky;
        top: 0;
        left: 0;
        background: #fff;
        margin: 0;
        z-index: 2;
    }

    .line-text {
        margin-top: 10rpx;
    }

    // .item{box-shadow: 0 64rpx 24rpx rgb(100 101 102 / 5%);}
    .item-img {
        width: 96rpx;
        height: 96rpx;
        border-radius: 50%;
        object-fit: cover;
        object-position: top;
        margin-right: 32rpx;
        overflow: hidden;
    }
    .item-text-1 {
        font-size: 35.2rpx;
    }
    .item-text-2 {
        color: $color-grey;
        margin-top: 6.4rpx;
    }
    .item-btn {
        min-width: 160rpx;
    }

    .tiem-box {
        text-align: center;
        margin-top: 32rpx;
        margin-bottom: 0;
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
    .fake-height{height: 32rpx;}

    .z-custom{
        color: $main-color;
        background: $bg-main-color;
        position:sticky;
        top: 0;
        left: 0;
        z-index: 2;
        border:none;
        border-radius: 0;
    }

    .z-custom::v-deep .u-icon__icon{
        color: $main-error-color !important;
    }

    .z-custom::v-deep .u-alert-desc{
        font-size: 30rpx;
        line-height: 1.8;
    }
</style>

