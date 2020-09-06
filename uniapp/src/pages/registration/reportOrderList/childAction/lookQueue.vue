<template>
    <u-modal
        v-model="show"
        ref="modal"
        :content-style="{padding: 0}"
        :show-title="false"
        mask-close-able
    >
        <u-form class="content" label-width="170" ref="uForm">
            <u-form-item label="姓名">
                <u-input disabled :value="curItem.Name" type="text"></u-input>
            </u-form-item>
            <u-form-item label="年龄">
                <u-input disabled :value="curItem.Age + ' ' + curItem.Sex" type="text"></u-input>
            </u-form-item>
            <u-form-item label="电话号码">
                <u-input disabled :value="curItem.Mobile" type="text"></u-input>
            </u-form-item>
            <u-form-item label="身份证号码">
                <u-input disabled :value="curItem.IDCardno" type="password"></u-input>
            </u-form-item>
            <u-form-item label="就诊卡号">
                <u-input disabled :value="curItem.CardNo" type="text"></u-input>
            </u-form-item>
        </u-form>
    </u-modal>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryRegWaitNum} from '@/apis';

    @Component
    export default class LookQueue extends Vue {

        show = false;
        curItem: IOBJ = {};

        async openFun (item: IOBJ) {
            const obj = {
                sourceMark: item.sourceMark,
                patientNo: item.CardNo,
                sourceDate: item.sourceDate,
                departmentorganId: item.departmentorganId,
            };
            const res = await queryRegWaitNum(obj, {isLoad: true});

            // this.curItem
            this.show = true;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content{
        padding:0 20rpx;
    }
</style>
