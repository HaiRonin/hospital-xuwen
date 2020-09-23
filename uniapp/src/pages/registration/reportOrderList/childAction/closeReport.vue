<template>
    <u-modal
        v-model="show"
        async-close
        @confirm="confirm"
        :show-cancel-button="!offClose"
        :mask-close-able="!offClose"
        content="确定取消预约吗"
        ref="modal"
    ></u-modal>
</template>

<script lang="ts">

    import {Component, Vue, Inject, Ref} from 'vue-property-decorator';
    import {ordeDoRegCancel} from '@/apis';

    @Component
    export default class CloseReport extends Vue {
        @Inject('reportOrderList') readonly reportOrderList!: IOBJ;
        @Ref('modal') readonly modal!: IOBJ;

        show = false;
        curItem: IOBJ | null = null;
        offClose = false;

        openFun (item: IOBJ) {
            this.curItem = item;
            this.offClose = false;
            this.show = true;
        }

        async confirm () {
            if (!this.curItem) {
                this.show = false;
                return;
            }

            const data = utils.jsCopyObj(this.curItem);
            data.patientNo = data.CardNo;
            data.payAmount = data.consultationFee;
            data.payCardNo = '';

            delete data.CardNo;
            delete data.consultationFee;
            // "{
            //     "synUserName": "",
            //     "synKey": "",
            //     "sourceMark": "202009051021",
            //     "patientNo": "99202008290495",
            //     "sourceDate": "2020-09-12",
            //     "departmentorganId": "uybtkt",
            //     "payCardNo": "",
            //     "payType": "5",
            //     "payAmount": "13",
            //     "payNo": "4200000685202009055226834287"
            // }"

            this.offClose = true;
            await ordeDoRegCancel(data).catch(() => {
                this.offClose = false;
                this.modal.clearLoading();
                return Promise.reject();
            });

            this.reportOrderList.getList();
            this.show = false;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
</style>
