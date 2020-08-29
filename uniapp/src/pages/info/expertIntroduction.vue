<template>
    <view class="z-box">
        <view
            v-for="(item, index) in list"
            :key="index"
            class="common-block flex-box rel"
        >
            <image :src="item.imgUrl" alt="" class="head-img" mode="widthFix"/>
            <view>
                <view class="text-1">{{item.name}}</view>
                <view class="text-2">{{item.des}}</view>
            </view>
        </view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {expertIntroductionJson} from '@/assets/textConfigJs';

    @Component
    export default class ExpertIntroduction extends Vue {

        list: IOBJ[] = expertIntroductionJson;

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .head-img{
    }
    .text-1{font-size: 32rpx;margin-bottom: 16rpx;}
    .text-2{color: $color-grey;}
</style>
