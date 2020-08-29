
<template>
    <view class="box">
        <view class="top-box">
            <!-- <van-dropdown-menu>
                <van-dropdown-item
                    v-model="params.departmentorganId"
                    :options="subjects"
                    @change="subjectsChange"
                />
                <van-dropdown-item v-model="params.theTitle" :options="theTitleList" @change="theTitleChange"/>
            </van-dropdown-menu> -->
            <!-- <van-viewider class="z-custom">{{this.params.type ? '今日' : '七日内'}}可预约</van-viewider> -->
            <!-- <u-dropdown close-on-click-mask close-on-click-self>
                <u-dropdown-item title="科室" v-model="params.departmentorganId" :options="subjects" @change="subjectsChange"></u-dropdown-item>
                <u-dropdown-item title="医生职称" v-model="params.theTitle" :options="theTitleList" @change="theTitleChange"></u-dropdown-item>
            </u-dropdown> -->
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
            <view class="line-text"><text>{{this.params.type === '2' ? '七日内' : '今日'}}可预约</text></view>
        </view>
        <view>
            <view v-for="(item, index) in list" :key="index" class="flex-box align-center justify-s-b common-block item">
                <view class="item-img">
                    <u-image :src="item.photoUrl || require('@/assets/image/d-male.png')" height="100%" mode="widthFix" ></u-image>
                </view>
                <view class="flex-1">
                    <view class="item-text-1">{{item.name}}</view>
                    <view class="item-text-2">{{item.job}}</view>
                </view>
                <u-button type="primary" plain size="mini" shape="circle" class="item-btn z-btn" @tap="link(item)">挂号</u-button>
            </view>
        </view>

        <u-empty text="没有查到相关医生" mode="list" margin-top="150" icon-size="200" font-size="36" v-show="loadCount === 1 && !list.length"></u-empty>

        <leftModal ref="leftModal" v-model="params.departmentorganId" @change="getList"/>
        <leftModal2 ref="leftModal2" :list="theTitleList" v-model="params.theTitle" @change="theTitleChange"/>
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
            const {type, departmentorganId} = this.params;
            const {photoUrl, name, job, organdoctorId} = item;
            const dataStr = utils.serialize({
                photoUrl, name, job, organdoctorId, type, departmentorganId
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

        async getList () {
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

        setTime (type: string) {
            // console.log(type === '0');
            const arr = (type === '0') ? this.$store.getters.day : this.$store.getters.sevenDay;
            console.log(this.$store.getters);
            this.params.startDate = arr[0];
            this.params.endDate = arr[1];
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
            this.setTime(this.params.type);

            // console.log(this.loadCount);
            this.params.departmentorganId && this.getList();
        }
    }
</script>

<style lang="scss" scoped>
    .box{
        // padding:0;
        background: #fff;
        min-height: 100%;
        padding-bottom: 20rpx;
    }

    .tap-style{font-size: 32rpx;padding: 20rpx 0;box-shadow: 0 0 8rpx rgba(100,101,102,.08)}
    .tap-icon{font-size: 16rpx;color: $color-grey;margin-left: 18rpx;}

    .top-box{
        position: sticky;
        top: 0;
        left: 0;
        background: #fff;
        margin: 0;
        z-index: 2;
    }

    .line-text{margin-top: 10rpx;}

    // .item{box-shadow: 0 64rpx 24rpx rgb(100 101 102 / 5%);}
    .item-img{width: 96rpx;height: 96rpx;border-radius: 50%;object-fit: cover;object-position: top;margin-right: 32rpx;overflow: hidden;}
    .item-text-1{font-size: 35.2rpx;}
    .item-text-2{color: $color-grey;margin-top: 6.4rpx;}
    .item-btn{min-width: 160rpx;}
</style>

