<template>
    <view class="wrap">
        <view class="top"></view>
        <view class="content">
            <view class="title flex-box align-center">
                <image :src="require('@/assets/image/logo.png')" class="logo" mode=""/>
                <view>欢迎登录</view>
            </view>
            <input class="u-border-bottom" type="number" maxlength="11" v-model="params.UserCode" placeholder="请输入工号" />
            <input class="u-border-bottom" type="password" maxlength="18" v-model="params.Password" placeholder="请输入密码" />
            <button @tap="submit" class="getCaptcha" :class="{val: submitStatus}">登录</button>
        </view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {registerDocotr} from '@/apis';
    import md5 from 'md5';

    @Component
    export default class Login extends Vue {

        params: IOBJ = {};
        check = (() => {
            const c = new utils.CheckVal({
                UserCode: '请输入工号',
                Password: '请输入密码',
            });

            c._addRule('Password', (val) => val.length >= 6 ? '' : '密码长度大于等于6');

            return c;
        })();


        get submitStatus () {
            const params = this.params;
            return !utils.zEmpty(params.UserCode) && !utils.zEmpty(params.Password);
        }


        async submit () {
            const data = utils.jsCopyObj(this.params);
            if (this.check.run(data)) return;

            const oldPassWord = data.Password;
            data.Password = md5(data.Password).toLocaleUpperCase();
            data.UserName = '';


            await registerDocotr(data, {isLoad: true});

            // data.oldPassWord = oldPassWord;
            this.$store.commit('user/setState', data);

            let {url, index}: IOBJ = {};
            const pageObj = this.$store.getters['redirect/getRedirectUrl'];

            if (pageObj) {
                ({url, index} = pageObj);
            } else {
                url = '/pages/home';
                index = 2;
            }

            this.$store.commit('redirect/clearData');
            utils.link(url, index);
        }

        created () {
            // console.log(getCurrentPages());
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
