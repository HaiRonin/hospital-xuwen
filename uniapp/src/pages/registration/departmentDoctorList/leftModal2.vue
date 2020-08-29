<template>
    <u-popup v-model="show" mode="left" width="60%">
        <view class="z-title" @tap="change(undefined)">医生职称</view>
        <view v-for="(child, cindex) in list" :key="cindex" class="flex-box justify-s-b align-center item" @tap="change(child.label)">
            <view>{{child.label}}</view>
            <view>
                <u-icon name="checkbox-mark" class="tap-icon" v-show="child.label === value"></u-icon>
            </view>
        </view>
    </u-popup>
</template>

<script lang="ts">

    import {Component, Vue, Prop, Model, Emit} from 'vue-property-decorator';

    @Component
    export default class LeftModal2 extends Vue {
        @Model('change') readonly value!: string;
        @Prop({type: Array, default: () => ([])}) readonly list!: IOBJ[];

        show = false;

        @Emit()
        change (str: string) {
            this.show = false;
            return str;
        }

        openFun () {
            this.show = true;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-title{
        font-size: 36rpx;text-align: left;padding-left: 30rpx;
        position: sticky;
        left: 0;
        top: 0;
        background: #fff;
    }
    .item{font-size: 28rpx;padding: 20rpx;border-bottom:$border-line;}
    .tap-icon{color:$main-color;}
</style>
