<template>
    <u-modal
        v-model="show"
        title="修改密码"
        confirm-text="修改"
        :show-cancel-button="closeBtn"
        :mask-close-able="false"
        :content-style="{padding: 0}"
        async-close
        @confirm="confirm"
        @cancel="cancel"
        ref="modal"
    >
        <u-form :model="params" class="content" ref="uForm">
            <u-form-item label="手机号码" label-width="150">
                <u-input placeholder="请输入手机号" disabled :value="userInfo.userName" type="number"></u-input>
            </u-form-item>
            <u-form-item label="新密码" label-width="150">
                <u-input placeholder="请输入新密码" maxlength="18" v-model="params.password" :clearable="false" type="password"></u-input>
            </u-form-item>
            <u-form-item label="验证码" label-width="150">
                <u-input placeholder="请输入验证码" :clearable="false" maxlength="6" v-model="params.verificationCode" type="number"></u-input>
                <u-button slot="right" type="primary" size="mini" @tap="getCode">{{codeBtnText}}</u-button>
            </u-form-item>
        </u-form>
    </u-modal>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import md5 from 'md5';
    import {Getter} from 'vuex-class';
    import {modifyPassword2, sendMsg} from '@/apis';

    const countDown = function (this: IOBJ, key: string, parentS = 5) {
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
    export default class UpDataPwa extends Vue {
        @Getter('userInfo') readonly userInfo!: IOBJ;
        @Ref('modal') readonly modal!: IOBJ;

        show = false;
        closeBtn = true;
        codeBtnText = '获取验证码';
        params: IOBJ = {};
        countDown: IOBJ = {};
        check = (() => {
            const c = new utils.CheckVal({
                password: '请输入新密码',
                verificationCode: '请输入验证码',
            });

            c._addRule('password', (val) => val.length >= 6 ? '' : '密码长度大于等于6');

            return c;
        })();

        openFun () {
            this.closeBtn = true;
            this.codeBtnText = '获取验证码';
            this.params = {};
            this.show = true;
        }

        async getCode () {
            if (this.countDown.getStatus()) return;
            const data = this.params;
            const password = data.password;
            let errText = '';

            if (utils.zEmpty(password)) {
                utils.toast('请输入新密码');
                return;
            } else if ((errText = this.check.password(password))) {
                utils.toast(errText);
                return;
            }

            try {
                this.countDown.open();
                await sendMsg({phone: this.userInfo.userName});
            } catch (error) {
                this.countDown.stop();
            }
        }

        async confirm () {
            const userName = this.userInfo.userName;
            const data = utils.jsCopyObj(this.params);
            if (this.check.run(data)) {
                this.modal.clearLoading();
                return;
            }

            this.closeBtn = false;
            data.phone = userName;
            data.password = md5(data.password);
            await modifyPassword2(data).catch(() => {
                this.modal.clearLoading();
                return Promise.reject();
            });

            await utils.toast('请重新登录', 1000);
            this.countDown.stop();

            this.$store.commit('user/clearState');
            utils.link('/pages/home/outpatient', 2);
        }

        cancel () {
            this.countDown.stop();
        }

        created () {
            this.countDown = countDown.call(this, 'codeBtnText');
            this.getCode = utils.throttle(this.getCode, 300, 500, true);
            this.confirm = utils.throttle(this.confirm, 300, 500, true);

        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content{
        padding:0 20rpx;
    }
</style>
