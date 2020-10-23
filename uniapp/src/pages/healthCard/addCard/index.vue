<template>
    <view class="">
        <view class="flex-box align-center top-box">
            <view
                v-for="(item, index) in sort"
                :key="index"
                class="flex-1 tb-text"
                :class="{cur: index === sortIndex}"
                @tap="sortIndex = index"
            >{{item.name}}</view>
        </view>

        <view v-show="sortIndex === 0"><inView ref="inView" :patientData="patientData"/></view>
        <view v-show="sortIndex === 1"><upDataView ref="upDataView"/></view>

        <u-form :model="params" ref="uForm" label-width="200rpx" class="form common-block">
            <u-form-item label="手机号码">
                <u-input v-model="params.phone1" maxlength="15" placeholder="请输入手机号码" />
            </u-form-item>
            <u-form-item label="验证码">
                <u-input v-model="params.smsCode" placeholder="请输入验证码" />
                <button class="code-btn" @tap="getCode">{{codeBtnText}}</button>
            </u-form-item>
        </u-form>

        <view class="flex-box align-center justify-center btn" @tap="commit">
            <view>完成注册</view>
        </view>
        <!-- <view class="b-tips" v-show="sortIndex === 0" @tap="selPatient">已有就诊卡，一键快速关联</view> -->
        <view class="b-tips" v-show="sortIndex === 0" @tap="selHealth">已有健康卡，一键快速关联</view>

    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import inView from './inView.vue';
    import upDataView from './upDataView.vue';
    import {healthCardRegisterHealthCard, healthCardSendCode} from '@/apis';

    const countDown = function (this: IOBJ, key: string, parentS = 60) {
        const keyText = key;
        let s = parentS;
        let time: any = null;
        // 状态
        let isStatus = false;

        // console.log(this, key);

        const stop = () => {
            clearInterval(time);

            time = null;
            s = parentS;
            isStatus = false;

            this[keyText] = '重新获取';
        };

        const open = () => {
            isStatus = true;
            this[keyText] = `${s}秒后重新获取`;

            time = setInterval(() => {
                s--;
                this[keyText] = `${s}秒后重新获取`;

                if (s <= 0) {
                    stop();
                }
            }, 1000);
        };

        return {
            open,
            stop,
            getStatus: () => {
                return isStatus;
            }
        };
    };


    @Component({
        components: {
            inView, upDataView
        }
    })
    export default class Index extends Vue {
        @Ref('inView') readonly inView!: IOBJ;
        @Ref('upDataView') readonly upDataView!: IOBJ;

        options: IOBJ = {};
        countDown: IOBJ = {};
        patientData: IOBJ = {};
        patientItem = '';
        sortIndex = 0;
        codeBtnText = '获取验证码';
        sort = [
            {name: '输入身份证号码'},
            {name: '上传身份证照片'},
        ];

        params: IOBJ = {
            phone1: ''
        };

        check = (() => {
            const c = new utils.CheckVal({
                // UserName: '请输入手机号',
                // PassWord: '请输入密码',
                // verificationCode: '',
                name: '',
                gender: '请选择性别',
                nation: '请选择民族',
                birthday: '请选择出生日期',
                idNumber: '请输入身份证号',
                idType: '',
                phone1: '请输入手机号',
                smsCode: '请输入验证码',
            });

            c.phone1 = c.phone;
            c.idNumber = c.idCard;
            c._addRule('name', (val, data) => {
                // console.log(data);
                if (data.sortIndex === 0 && utils.zEmpty(val)) return '请输入姓名';

                if (utils.zEmpty(val)) return '请上传身份证';
                return '';
            });

            return c;
        })();

        getPatientData (item: IOBJ) {
            item = item.oldData;

            // this.$set(params, 'patientNo', item.patientNo);
            // this.$set(params, 'patientName', item.patientName);
            // this.$set(params, 'cardNo', item.cardNo);
            // console.log(item);
            const params = this.params;
            params.phone1 = item.Mobile;
            this.patientData = utils.jsCopyObj(item);
        }

        selPatient () {
            utils.link('/pages/outpatient/index?sel=1');
        }

        selHealth () {
            const redirectUri = encodeURIComponent(utils.getStorage('healthCodeRedirectUri'));
            // alert(redirectUri);
            window.location.href = `https://health.tengmed.com/open/getHealthCardList?redirect_uri=${redirectUri}`;
        }

        setDefaultData () {
            const patientItem = this.patientItem;
            if (utils.zEmpty(patientItem)) return;
            const data = utils.getStorage(`relevancePatientItem-${patientItem}`);

            // utils.removeStorage(`relevancePatientItem-${itemFlag}`);

            if (utils.zEmpty(data)) return;
            this.getPatientData({oldData: data});
        }

        async getCode () {
            if (this.countDown.getStatus()) return;

            const phone1 = this.params.phone1;
            const errText = this.check.phone1(phone1);
            // console.log(this.check.phone1(phone1));
            if (!utils.zEmpty(errText)) {
                utils.toast(errText);
                return;
            }

            try {
                this.countDown.open();
                await healthCardSendCode({phone: phone1});
            } catch (error) {
                this.countDown.stop();
            }
        }

        async commit () {
            const sortIndex = this.sortIndex;
            const domCom = sortIndex === 0 ? this.inView : this.upDataView;
            // console.log(domCom);
            const data = Object.assign({}, this.params, this.options, domCom.params);

            data.sortIndex = this.sortIndex;
            if (this.check.run(data)) return;

            const res = await healthCardRegisterHealthCard(data, {isLoad: true});
            utils.setStorage('addHealthData', {healthRes: res, params: data});
            window.history.go(-1);

            // 就诊卡列表添加数据(引用健康卡数据)
            // const healthCodeRedirectUri = utils.getStorage('healthCodeRedirectUri');
            // if (healthCodeRedirectUri) {
            //     const redirectUri = encodeURIComponent(`${healthCodeRedirectUri}`);
            //     // alert(healthCodeRedirectUri);
            //     utils.removeStorage('healthCodeRedirectUri');
            //     await utils.toast('请选择新添加的 健康卡');
            //     window.location.replace(`https://health.tengmed.com/open/getHealthCardList?redirect_uri=${redirectUri}`);
            //     return;
            // }

            // window.location.replace(`${globalConfig.domain.webUrl}/pages/healthCard/list`);
        }

        onLoad (options: IOBJ) {

            this.patientItem = options.patientItem || '';
            this.options = options;

            delete options.patientItem;

            this.setDefaultData();
        }

        created () {
            this.countDown = countDown.call(this, 'codeBtnText');
            uni.$on('upDataPatient', this.getPatientData);
        }

        mounted () {}

        activated () {}

        beforeDestroy () {
            uni.$off('upDataPatient', this.getPatientData);
        }

    }
</script>

<style lang="scss" scoped>
    .top-box {
        background: #fff;
        line-height: 80rpx;
        font-size: 31rpx;
        text-align: center;
        position:sticky;
        top:0;
        left: 0;
        right: 0;
        box-shadow: 0 2rpx 6rpx 0px $color-weak-grey;
        margin-bottom: 32rpx;
    }
    .top-box .tb-text.cur {
        color: $main-color;
        font-weight: bold;
        background: -webkit-linear-gradient(left, $main-color, $main-color) center
            bottom / 50rpx 5rpx no-repeat;
    }

    .form {
        // background: #fff;
        // margin: 20rpx;
        padding: 0 30rpx;
    }

    .code-btn {
        font-size: 28rpx;
        line-height: 60rpx;
        height: 60rpx;
        background-color: $main-color;
        color: #fff;
    }

    .code-btn::after {
        content: initial;
    }

    .btn{
        background: $main-color;
        color: #fff;
        line-height: 2.6;
        font-size: 32rpx;
        border-radius:10rpx;
        margin:60rpx 40rpx 0;
    }

    .b-tips{
        font-size: 28rpx;
        color: $main-color;
        text-align: center;
        padding: 30rpx;
    }
</style>
