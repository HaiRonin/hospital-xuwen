<template>
    <view class="z-box row-box">
        <view
            v-for="(child, cindex) in list"
            :key="cindex"
            class="common-block"
            @tap="open(child)"
        >
            <view class="flex-box align-center justify-s-b">
                <view>{{child.name}}</view>
                <u-icon :name="child.open ? 'arrow-up' : 'arrow-down'" class="item-icon"></u-icon>
            </view>
            <view class="text-1" v-if="!!child.open">
                <view class="fake-line"></view>
                {{child.ddesc || '暂无介绍信息'}}
            </view>
        </view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryDepartmentList} from '@/apis';

    @Component
    export default class DepartmentIsIntroduced extends Vue {

        activeName: string = '';
        oldList: IOBJ[] = [];
        list: IOBJ[] = [];

        async getInfo () {
            const res = await queryDepartmentList();

            this.oldList = res.data;
            this.list = res.data;
        }

        open (item: IOBJ) {
            this.oldList.forEach(ii => ii.organId !== item.organId && this.$set(ii, 'open', false));
            this.$set(item, 'open', !item.open);
        }

        created () {
            this.getInfo();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-box{
        padding:0;
        overflow: initial;
        padding-bottom: 20rpx;
    }
    .line-text{
        padding-top: 20rpx;
        padding-bottom: 20rpx;
        position: sticky;
        top: 0;
        left: 0;
        background: #fff;
        z-index: 2;
        box-shadow: 0 0 8rpx rgba(100,101,102,.08);
    }
    .common-block {
        // padding: 0;
        // padding-left: 20rpx;
        // padding-right: 20rpx;
        line-height: 1.6;
        letter-spacing: 2rpx;
    }


    .row-box {
        padding-top: 20rpx;
    }

    .fake-line{
        padding-top: 24rpx;
        margin-top: 24rpx;
        border-top: $border-line
    }

    .text-1{
        color:$color-grey;
    }

</style>
