<template>
    <u-modal
        v-model="show"
        title="门诊医生转诊"
        confirm-text="转诊"
        :show-cancel-button="closeBtn"
        :mask-close-able="false"
        :content-style="{padding: 0}"
        async-close
        @confirm="confirm"
        ref="modal"
    >
        <u-form :model="params" class="content" ref="uForm">
            <u-form-item label="原医生工号" label-width="150">
                <u-input placeholder="" disabled :value="temporaryCode || userInfo.UserCode" type="number"></u-input>
            </u-form-item>
            <u-form-item label="转医生工号" label-width="150">
                <u-input placeholder="请输入医生工号" maxlength="20" v-model="params.organdoctorId" type="number"></u-input>
            </u-form-item>
        </u-form>
    </u-modal>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {Getter} from 'vuex-class';
    import {toDocReferral} from '@/apis';

    @Component
    export default class UpDataPwa extends Vue {
        @Getter('userInfo') readonly userInfo!: IOBJ;
        @Getter('temporaryCode') readonly temporaryCode!: IOBJ;
        @Ref('modal') readonly modal!: IOBJ;

        show = false;
        closeBtn = true;
        params: IOBJ = {};

        openFun (item: IOBJ) {
            this.params = item;
            this.closeBtn = true;
            this.show = true;
        }

        async confirm () {

            const data = utils.jsCopyObj(this.params);
            if (utils.zEmpty(data.organdoctorId)) {
                this.modal.clearLoading();
                utils.toast('请输入医生工号');
                return;
            }

            this.closeBtn = false;
            await toDocReferral(data).catch(() => {
                this.closeBtn = true;
                this.modal.clearLoading();
                return Promise.reject();
            });


            this.$store.commit('setTemporaryCode', data.organdoctorId);
            this.modal.clearLoading();
            this.show = false;
            utils.link('/pages/home', 2);
        }

        created () {
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
