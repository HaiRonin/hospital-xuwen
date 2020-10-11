
<template>
    <view class="z-box">
        <view
            v-for="(item, index) in list"
            :key="index"
            class="common-block flex-box align-center rel"
            @tap="link(item)"
        >
            <view class="flex-1 item-text-1">{{item.text}}</view>
            <u-icon name="arrow-right" class="item-icon"></u-icon>
        </view>
    </view>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';


    @Component
    export default class Index extends Vue {

        list: IOBJ[] = [
            {text: '医院简介', url: 'https://mp.weixin.qq.com/s/k9N5u69_3LTnKT_Ohe8lpA', type: 'href'},
            // {text: '专家介绍', url: '/pages/info/expertIntroduction'},
            {text: '科室介绍', url: '/pages/info/departmentIsIntroduced'},
            {text: '药品/非药品查询', url: '/pages/info/drugSearch'},
        ];


        link (item: IOBJ) {
            // debugger;
            if (item.type === 'href') {
                window.location.href = item.url;
                return;
            }
            utils.link(item.url);
        }

        created () {
        }

        beforeDestroy () {
            console.log(this);
        }
    }
</script>

<style lang="scss" scoped>
</style>

