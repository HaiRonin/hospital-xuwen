<template>
    <view class="box">
        <view @tap="tipsShow = true" v-if="info.contents && info.contents.length">
            <u-alert-tips @tap="tipsShow = true" type="primary" class="z-custom" show-icon description="点击查看 预约须知"></u-alert-tips>
        </view>

        <view style="height:32rpx"></view>

        <u-form :model="params" ref="uForm" label-width="200rpx" class="form common-block">
            <u-form-item label="姓名">
                <u-input v-model="params.name" maxlength="8" placeholder="请输入姓名" />
            </u-form-item>
            <u-form-item label="身份证">
                <u-input v-model="params.idCard" maxlength="20" placeholder="请输入身份证号码" />
            </u-form-item>
            <u-form-item label="手机">
                <u-input v-model="params.phone" maxlength="11" placeholder="请输入手机号码" />
            </u-form-item>
            <u-form-item label="预约时间">
                <!-- <picker mode="date" class="picker-input" :start="start" @change="bindDateChange($event, 'appointmentTime')">
                    <view class="picker-input" v-if="params.appointmentTime">{{params.appointmentTime}}</view>
                    <view class="picker-input p-i-color" v-else>请选择预约时间</view>
                </picker> -->
                <picker mode="multiSelector" class="picker-input" :range="sortArr" @change="bindDateChange($event, 'appointmentTime')">
                    <view class="picker-input" v-if="params.appointmentTime">{{params.appointmentTime}}</view>
                    <view class="picker-input p-i-color" v-else>请选择预约时间</view>
                </picker>
            </u-form-item>
        </u-form>

        <view class="flex-box align-center justify-center btn" @tap="commit">
            <view>提交预约</view>
        </view>

        <view class="flex-box align-center justify-center btn" @tap="statusQuery">
            <view>查询预约情况</view>
        </view>

        <view class="query-text">输入预约的身份证号，点击查询预约情况按钮即可查询预约情况</view>

        <u-modal
            v-model="tipsShow"
            title="预约须知"
            :show-cancel-button="false"
            :mask-close-able="false"
            ref="modal"
        >
            <view class="tips" v-if="info.contents">
                <view v-for="(str, index) in info.contents" :key="index">{{str}}</view>
            </view>
        </u-modal>

    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {getPreregistrationConfig, savePreregistrationInfo, queryPeregistrationStatus} from '@/apis';

    @Component
    export default class Name extends Vue {

        info: IOBJ = {};
        tipsShow = false;
        params: IOBJ = {
            appointmentTime: '',
        };

        sortArr: string[][] = [
            [
                '2020-02-02',
                '2020-02-03',
                '2020-02-04',
            ],
            [
                '14:30-17:30',
            ]
        ];

        check = (() => {
            const c = new utils.CheckVal({
                // UserName: '请输入手机号',
                // PassWord: '请输入密码',
                // verificationCode: '',
                name: '请输入姓名',
                idCard: '',
                phone: '请输入手机号',
                appointmentTime: '请选择预约时间'
            });

            // utils.getBirthdayFromIdCard(item.IDCardno);

            // c.phone1 = c.phone;
            // c.idCard = c.idCard;
            const a = c.idCard;
            c.idCard = (val: string) => {
                let str = a(val);

                if (utils.zEmpty(str)) {
                    const sex = utils.getSexIdCard(val);
                    console.log(sex);
                    str = sex === '男' ? '限制女性预约' : '';
                }

                if (utils.zEmpty(str)) {
                    // 35到64的
                    const age = utils.getAgeIdCard(val);
                    str = (age < 35 || age > 64) ? '限制35-64岁年龄的女性预约' : '';
                }

                return str;
            };

            return c;
        })();

        bindDateChange (e: IOBJ, key: string) {
            console.log(e, key);
            const arr = e.detail.value;
            const sortArr = this.sortArr;
            this.params[key] = `${sortArr[0][arr[0]]} ${sortArr[1][arr[1]]}`;
        }

        async commit () {
            const data = utils.jsCopyObj(this.params);
            if (this.check.run(data)) return;
            data.appointmentTime = `${data.appointmentTime.substr(0, 16)}:00`;

            const res = await savePreregistrationInfo(data, {isLoad: true});
            await utils.toast(res.msg, 1000);

            if (getCurrentPages().length >= 2) {
                utils.link(1);
            } else {
                utils.link('/pages/home/outpatient', 2);
            }
        }

        async getData () {
            const res = await getPreregistrationConfig({}, {isLoad: true});
            const data = this.info = res.data;

            this.genratedTime(+(data.day || 7));
            if (data.contents && data.contents.length) {
                this.tipsShow = true;
            }
        }

        genratedTime (scope = 7) {
            const date = new Date();
            const arr = [];
            for (let i = 0; i < scope - 1; i++) {
                date.setDate(date.getDate() + 1);
                const obj = utils.dateData(date);
                !obj.weekend && arr.push(obj.text);
            }
            // console.log(arr);
            this.sortArr[0] = arr;
        }

        async statusQuery () {
            const idCard = this.params.idCard;
            const errorText = this.check.idCard(idCard);
            if (errorText) {
                utils.toast(errorText);
                return;
            }
            const res = await queryPeregistrationStatus({idCard});
            const data = (res.data || [])[0];

            if (data) {
                utils.toast(`您已成功预约 ${data.appointmentTime}`, 3000);
            } else {
                utils.toast('无预约信息');
            }
        }

        onLoad () {}

        created () {
            this.getData();
            // this.genratedTime();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .box{
        font-size: 28rpx;
        // padding-top: 32rpx;
    }
    .form {
        // background: #fff;
        // margin: 20rpx;
        padding: 0 30rpx;
    }

    .picker-input{
        width: 100%;
    }

    .p-i-color{
        color: #c0c4cc;
    }

    .btn{
        background: $main-color;
        color: #fff;
        line-height: 2.6;
        font-size: 32rpx;
        border-radius:10rpx;
        margin:60rpx 40rpx 0;
    }

    .z-custom{
        // position: sticky;
        // left: 0;
        // top: 0;
        border:none;
        font-size: 26rpx;
        padding: 16rpx 30rpx;
        // line-height: 2;
        line-height: 1.6;
    }

    .tips{
        line-height: 1.5;
        color: $color-grey;
        padding: 40rpx 20rpx;
        font-size: 32rpx;
        // height: 500rpx;
        // overflow: auto;
    }

    .query-text{
        margin: 20px 20px 0;
        color: $main-error-color;
        font-size: 32rpx;
    }

</style>
