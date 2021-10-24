
<template>
    <view class="z-box">
        <view
            v-for="(item, index) in list"
            :key="index"
            class="common-block flex-box align-center rel"
            @tap="link(item.url)"
        >
            <view class="flex-1 item-text-1">{{item.text}}</view>
            <u-icon name="arrow-right" class="item-icon"></u-icon>
        </view>
    </view>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';

    const {link} = utils;

    @Component
    export default class Index extends Vue {

        list: IOBJ[] = [
            // {text: '医院简介', url: '/pages/info/hospitalntroduction'},
            // {text: '专家介绍', url: '/pages/info/expertIntroduction'},
            {text: '科室介绍', url: '/pages/info/departmentIsIntroduced'},
            {text: '药品/医疗服务/医用耗材查询', url: '/pages/info/drugSearch'},
        ];

        link = link;

        created () {
        }

        beforeDestroy () {
            console.log(this);
        }
    }
</script>

<style lang="scss" scoped>
</style>

