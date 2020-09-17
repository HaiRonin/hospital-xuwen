<template>
    <view class="wrap">
        <view class="top"></view>
        <view class="content">
            <view class="title flex-box align-center">
                <image :src="require('@/assets/image/logo.png')" class="logo" mode=""/>
                <view>欢迎登录</view>
            </view>
            <input class="u-border-bottom" type="number" maxlength="11" v-model="params.UserName" placeholder="请输入手机号" />
            <input class="u-border-bottom" type="password" maxlength="18" v-model="params.PassWord" placeholder="请输入密码" />
            <!-- <u-input class="u-border-bottom" placeholder="请输入密码" v-model="params.PassWord" :clearable="false" type="password"></u-input> -->
            <view class="rel" v-if="isReg">
                <input class="u-border-bottom" type="number" maxlength="6" v-model="params.verificationCode" placeholder="请输入验证码" />
                <button class="abs code-btn" :class="{val: codeStatus}" @tap="getCode">{{codeBtnText}}</button>
            </view>
            <button @tap="submit" class="getCaptcha" :class="{val: submitStatus}">{{isReg ? '注册' : '登录'}}</button>
            <view class="alternative">
                <!-- <view class="password">验证码登录</view> -->
                <view class=""></view>
                <view class="issue" @tap="isReg = !isReg;">{{isReg ? '账号密码登录' : '前往注册'}}</view>
            </view>
        </view>
        <view class="buttom">
            <view class="loginType flex-box justify-center">
                <view class="wechat item">
                    <view class="icon">
                        <u-icon size="70" name="weixin-fill" color="rgb(83,194,64)"></u-icon>
                    </view>微信
                </view>
            </view>
        </view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {validateLogon, registerCur, sendMsg} from '@/apis';
    import md5 from 'md5';

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

    @Component
    export default class Login extends Vue {

        params: IOBJ = {};
        isReg = false;
        countDown: IOBJ = {};
        codeBtnText = '获取验证码';
        check = (() => {
            const c = new utils.CheckVal({
                UserName: '请输入手机号',
                PassWord: '请输入密码',
                verificationCode: '',
            });

            c.UserName = c.phone;

            c._addRule('PassWord', (val) => val.length >= 6 ? '' : '密码长度大于等于6');
            c._addRule('verificationCode', (val, data) => {
                if (!data._isReg) return '';
                return utils.zEmpty(val) ? '请输入验证码' : '';
            });

            return c;
        })();

        get codeStatus () {
            const params = this.params;
            const isReg = this.isReg;
            return isReg && !utils.zEmpty(params.UserName) && !utils.zEmpty(params.PassWord);
        }

        get submitStatus () {
            const params = this.params;
            const isReg = this.isReg;
            return (isReg ? !utils.zEmpty(params.verificationCode) : true) && !utils.zEmpty(params.UserName) && !utils.zEmpty(params.PassWord);
        }

        async getCode () {
            if (!this.codeStatus || this.countDown.getStatus()) return;

            const data = utils.jsCopyObj(this.params);
            if (this.check.run(data)) return;

            try {
                this.countDown.open();
                await sendMsg({phone: data.UserName});
            } catch (error) {
                this.countDown.stop();
            }
        }

        async submit () {
            const isReg = this.isReg;
            const data = utils.jsCopyObj(this.params);
            data._isReg = isReg;
            if (this.check.run(data)) return;

            data.PassWord = md5(data.PassWord);
            data._isReg = undefined;

            const fn = isReg ? registerCur : validateLogon;

            if (isReg) {
                data.password = data.PassWord;
                data.phone = data.UserName;
            }

            const res = await fn(data, {isLoad: true});

            if (isReg) {
                utils.toast('注册成功，请登录');
                this.isReg = false;
                return;
            }

            // console.log(res);
            delete res.data;
            delete res.resultCode;
            delete res.resultMsg;

            this.$store.commit('user/setState', res);

            let {url, index}: IOBJ = {};
            const pageObj = this.$store.getters['redirect/getRedirectUrl'];

            if (pageObj) {
                ({url, index} = pageObj);
            } else {
                url = '/pages/home/outpatient';
                index = 2;
            }

            this.$store.commit('redirect/clearData');
            utils.link(url, index);
        }

        created () {
            // console.log(getCurrentPages());
            this.countDown = countDown.call(this, 'codeBtnText');
            this.getCode = utils.throttle(this.getCode, 300, 500, true);
            this.submit = utils.throttle(this.submit, 300, 500, true);
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>


    .logo{
        width: 150rpx;
        height: 150rpx;
        margin-right: 50rpx;
    }

    .u-border-bottom{
        padding: 40rpx 0;
    }

    .u-border-bottom::after{
        border:none;
    }

    .code-btn{
        right: 0rpx;
        top: 35rpx;
        font-size: 28rpx;
        line-height: 60rpx;
        height: 60rpx;
        background-color: $main-next-color;
        color: #fff;
    }

    .code-btn.val{
        background-color: $main-color;
    }

    .code-btn::after{
        content: initial;
    }

    .getCaptcha.val{
        background-color: $main-color !important;
    }

    .wrap {
        background: #fff;
        min-height: 100%;
        font-size: 28rpx;
        .content {
            width: 600rpx;
            padding: 100rpx 0 0 0;
            margin: auto;

            .title {
                text-align: left;
                font-size: 60rpx;
                font-weight: 500;
                margin-bottom: 40rpx;
            }
            .getCaptcha {
                background-color: $main-next-color;
                color: #fff;
                border: none;
                font-size: 30rpx;
                padding: 12rpx 0;

                &::after {
                    border: none;
                }
            }
            .alternative {
                color: $color-grey;
                display: flex;
                justify-content: space-between;
                margin-top: 30rpx;
            }
        }
        .buttom {
            .loginType {
                // display: flex;
                // padding: 350rpx 150rpx 150rpx 150rpx;
                // justify-content: center;
                position: fixed;
                left: 0;
                right: 0;
                bottom: 80rpx;

                .item {
                    color: $default-color;
                    font-size: 28rpx;
                }
            }
        }
    }
</style>
