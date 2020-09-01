<template>
    <view class="z-box">
        <u-search
            placeholder="请输入搜索关键词"
            shape="round"
            :clearabled="false"
            :show-action="false"
            @change="input"
            class="search-box"
        ></u-search>

        <u-alert-tips
            class="w-tips"
            type="warning"
            :description="tips"
            :show-icon="true"
            v-if="tips"
        ></u-alert-tips>

        <view id="zLine"></view>

        <view class="row-box" v-if="searchList.length">
            <view
                v-for="(child, cindex) in searchList"
                :key="cindex"
                class="common-block flex-box align-center justify-s-b"
                @tap="link(child)"
            >
                <view>{{child.name}}</view>
                <u-icon name="arrow-right" class="item-icon"></u-icon>
            </view>
        </view>

        <template v-for="(item, index) in list" v-else>
            <view :key="item.title + index" class="line-text-box" :style="{position: topVal ? 'sticky' : 'static', top: topVal}">
                <view class="line-text">
                    <text>{{item.title}}</text>
                </view>
            </view>
            <view class="row-box" :key="index">
                <view
                    v-for="(child, cindex) in item.list"
                    :key="cindex"
                    class="common-block flex-box align-center justify-s-b"
                    @tap="link(child)"
                >
                    <view>{{child.name}}</view>
                    <u-icon name="arrow-right" class="item-icon"></u-icon>
                </view>
            </view>
        </template>

        <view class="fake-view"></view>
        <view class="bottom-btn" @tap="linkSearchDoctor">查找医生</view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {queryDepartmentList} from '@/apis';

    @Component
    export default class DepartmentIsIntroduced extends Vue {

        activeName: string = '';
        tips = '';
        topVal = '';
        options: IOBJ = {};
        list: IOBJ[] = [];
        searchList: IOBJ[] = [];
        oldList: IOBJ[] = [];

        input (val: string) {
            if (utils.zEmpty(val)) {
                this.searchList = [];
                return;
            }
            this.searchList = this.oldList.filter((item) => ~item.name.indexOf(val));
        }

        async getInfo () {
            const res = await queryDepartmentList();

            // 科室类别（1、住院科室  2、门诊科室）
            const type1: IOBJ[] = [];
            const type2: IOBJ[] = [];
            res.data.forEach((item: IOBJ) => {
                item.type === '1' && type1.push(item);
                item.type === '2' && type2.push(item);
            });

            this.oldList = res.data;
            this.list = [
                {
                    title: '住院科室',
                    list: type1
                },
                {
                    title: '门诊科室',
                    list: type2
                }
            ];
        }

        link (item: IOBJ) {
            const strData = utils.serialize({
                departmentorganId: item.organId,
                type: this.options.type,
            });
            utils.link(`/pages/registration/departmentDoctorList/index?${strData}`);
        }

        linkSearchDoctor () {
            const type = this.options.type;
            utils.link(`/pages/registration/searchDoctor?type=${type}`);
        }

        setTopVal () {
            // line
            uni.createSelectorQuery().select('#zLine').boundingClientRect((res) => {
                res && res.top && (this.topVal = `${res.top}px`);
            }).exec();
        }

        onLoad (options: IOBJ) {
            // type 0今日 2预约
            this.options = options;
            uni.setNavigationBarTitle({
                title: `科室选择(${this.options.type === '0' ? '今日' : '七日内'})`
            });
        }

        created () {
            this.input = utils.throttle(this.input, 500);
            this.getInfo();
        }

        mounted () {
            this.$nextTick(() => {
                this.setTopVal();
            });
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-box {
        padding: 0;
        overflow: initial;
        padding-bottom: 20rpx;
    }
    .search-box {
        padding: 20rpx;
        box-shadow: 0 0 8rpx rgba(100, 101, 102, 0.08);
        height: 100rpx;
        position: sticky;
        top: 0;
        background: #fff;
        left: 0;
        z-index:3;
    }
    .w-tips {
        border: none;
        border-radius: initial;
        position: sticky;
        top: 100rpx;
        left: 0;
        z-index:2;
    }
    .line-text-box{
        padding-top: 20rpx;
        padding-bottom: 20rpx;
        // position: sticky;
        top: 0;
        left: 0;
        background: #fff;
        z-index: 2;
        box-shadow: 0 0 8rpx rgba(100, 101, 102, 0.08);
    }
    .row-box {
        padding-top: 20rpx;
    }
    .common-block {
        // padding: 0;
        // padding-left: 20rpx;
        // padding-right: 20rpx;
        line-height: 1.6;
        letter-spacing: 2rpx;
    }

    .fake-view{
        height: 100rpx;
    }

    .bottom-btn{
        position: fixed;
        left: 0;
        bottom: 0;
        right: 0;
        width: 100%;
        line-height: 90rpx;
        font-size: 34rpx;
        letter-spacing: 2rpx;
        background: $main-cur-color;
        color: #fff;
        text-align: center;
        z-index: 2;
    }
</style>
