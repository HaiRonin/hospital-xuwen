<template>
    <u-modal
        v-model="show"
        async-close
        @confirm="confirm"
        :show-cancel-button="!offClose"
        :mask-close-able="!offClose"
        content="确定删除该卡号吗"
        ref="modal"
    ></u-modal>
</template>

<script lang="ts">

    import {Component, Vue, Inject, Ref} from 'vue-property-decorator';
    import {deletePatients} from '@/apis';

    @Component
    export default class DelPatientCard extends Vue {
        @Inject('outpatientIndex') readonly outpatientIndex!: IOBJ;
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

            const CardNo = this.curItem.CardNo;

            this.offClose = true;
            await deletePatients({CardNo}).catch(() => {
                this.offClose = false;
                this.modal.clearLoading();
                return Promise.reject();
            });

            this.outpatientIndex.getList();
            this.show = false;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
</style>
