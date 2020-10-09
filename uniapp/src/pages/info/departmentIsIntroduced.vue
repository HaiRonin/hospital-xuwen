<template>
    <view class="z-box">
        <template v-for="(item, index) in list">
            <view class="line-text" :key="item.title + index"><text>{{item.title}}</text></view>
            <view class="row-box" :key="index">
                <view
                    v-for="(child, cindex) in item.list"
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
            <!-- <u-collapse class="collapse-box" :key="index">
                <u-collapse-item
                    v-for="(child, cindex) in item.list"
                    :title="child.name"
                    :key="cindex"
                    class="common-block"
                >
                    <view class="fake-line"></view>
                    {{child.ddesc || '暂无介绍信息'}}
                    <view class="fake-height"></view>
                </u-collapse-item>
            </u-collapse> -->
        </template>
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

            // 1-住院科室;2-门诊科室;3-公共科室;;6-医技科室
            const type1: IOBJ[] = [];
            const type2: IOBJ[] = [];
            const type3: IOBJ[] = [];
            const type6: IOBJ[] = [];
            res.data.forEach((item: IOBJ) => {
                item.type === '1' && type1.push(item);
                item.type === '2' && type2.push(item);
                item.type === '3' && type3.push(item);
                item.type === '6' && type6.push(item);
            });

            this.oldList = res.data;
            this.list = [
                {title: '公共科室', list: type3},
                {title: '门诊科室', list: type2},
                {title: '医技科室', list: type6},
                {title: '住院科室', list: type1},
            ];
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
