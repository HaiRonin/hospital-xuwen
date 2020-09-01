<template>
    <view class="z-box">
        <view class="common-block" v-for="(item) in list" :key="item.CardNo" @tap="selItem(item)">
            <view class="flex-box align-center justify-s-b">
                <view class="flex-1">{{item.Name}}</view>
                <view class="text-2">(就诊卡号{{item.CardNo}})</view>
                <u-icon name="arrow-right" class="text-icon" v-if="isToUrl"></u-icon>
            </view>
            <view class="flex-box align-center justify-s-b action-box" v-if="!isSelModel">
                <view class="text-action text-del" @tap.stop="delPatientCard.openFun(item)">
                    <u-icon name="trash" class="del-icon"></u-icon>删除
                </view>
                <view class="flex-box align-center">
                    <view class="text-action" @tap.stop="lookPatientCardInfo.openFun(item)">
                        <u-icon name="list-dot" class="del-icon"></u-icon>卡号信息
                    </view>
                    <view class="text-action" @tap.stop="lookBarCode.openFun(item)">
                        <u-icon name="tiaoxingma" custom-prefix="z-icon" class="del-icon"></u-icon>查看条形码
                    </view>
                </view>
            </view>
            <view v-else class="fake-padding"></view>
        </view>

        <view @tap="addPatient.openFun()" v-show="loadCount === 1 && !list.length">
            <u-empty
                text="点击前往添加就诊人"
                mode="list"
                margin-top="150"
                icon-size="200"
                font-size="36"
            ></u-empty>
        </view>

        <view class="fake-height"></view>

        <view class="add-box">
            <!-- <u-button v-show="false" type="primary" :plain="false" @tap="addPatient.openFun()">添加就诊人</u-button> -->
            <button class="z-btn-default z-btn-primary" @tap="addPatient.openFun()">添加就诊人</button>
        </view>

        <delPatientCard ref="delPatientCard" />
        <lookPatientCardInfo ref="lookPatientCardInfo" />
        <lookBarCode ref="lookBarCode" />
        <addPatient ref="addPatient" />
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Provide} from 'vue-property-decorator';
    import {queryPatients} from '@/apis';
    import delPatientCard from './childAction/delPatientCard.vue';
    import lookPatientCardInfo from './childAction/lookPatientCardInfo.vue';
    import lookBarCode from './childAction/lookBarCode.vue';
    import addPatient from './childAction/addPatient.vue';

    @Component({
        components: {
            delPatientCard, lookPatientCardInfo, lookBarCode, addPatient
        }
    })
    export default class Index extends Vue {
        @Ref('delPatientCard') readonly delPatientCard!: IOBJ;
        @Ref('lookPatientCardInfo') readonly lookPatientCardInfo!: IOBJ;
        @Ref('lookBarCode') readonly lookBarCode!: IOBJ;
        @Ref('addPatient') readonly addPatient!: IOBJ;
        @Provide('outpatientIndex') outpatient = this;

        list: IOBJ[] = [];
        options: IOBJ = {};
        isToUrl = false;
        isSelModel = false;
        loadCount = 0;

        selItem (item: IOBJ) {
            if (this.isSelModel) {
                uni.$emit('upDataPatient', {
                    patientNo: item.CardNo,
                    patientName: item.Name,
                    cardNo: item.IDCardno,
                });
                utils.link(1);
                return;
            }
        }

        async getList () {
            const res = await queryPatients({}, {isLoad: true, closeErrorTips: true}).catch(() => {
                return {data: []};
            });

            this.list = res.data;
            this.loadCount = 1;
            return Promise.resolve();
        }

        onPullDownRefresh () {
            utils.pullDown(() => this.getList());
        }

        onLoad (options: IOBJ) {
            // toUrl 下一步的跳转路径
            this.options = options;
            this.isToUrl = !!options.sel || !!options.toUrl;
            this.isSelModel = !!options.sel;
        }

        created () {
            this.getList();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-box {
        overflow: initial;
    }

    .text-2 {
        color: $color-grey;
    }

    .add-box {
        padding: 30rpx 50rpx;
        box-shadow: 0 -2rpx 8rpx rgba(100, 101, 102, 0.08);
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        background: #fff;
    }

    .fake-height {
        height: 150rpx;
    }

    .common-block {
        padding-bottom: 0;
    }

    .action-box {
        border-top: $border-line;
        margin-top: 26rpx;
        font-size: 26rpx;
    }

    .fake-padding{
        height: 26rpx;
    }

    .text-action {
        padding: 26rpx 0 24rpx;
        margin-left: 40rpx;
    }

    .text-del {
        margin-left: 0;
        color: $main-error-color;
    }

    .del-icon {
        margin-right: 10rpx;
    }
    .text-icon {
        margin-left: 10rpx;
        color: $color-grey;
    }
</style>
