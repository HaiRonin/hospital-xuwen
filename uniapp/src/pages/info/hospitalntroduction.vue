<template>
    <view class="z-box">
        <view class="common-block">{{text}}</view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {hospitalntroductionText} from '@/assets/textConfigJs';

    @Component
    export default class Hospitalntroduction extends Vue {
        text: string = hospitalntroductionText;

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .common-block{
        // text-indent: ;
        line-height: 1.8;
        letter-spacing: 1.6rpx;
        font-size: 33.6rpx;
        text-indent: 64rpx;
    }
</style>
