<template>
    <div class="z-box">
        <div
            v-for="(item, index) in list"
            :key="index"
            class="common-block flex-box rel"
        >
            <img :src="item.imgUrl" alt="" class="head-img">
            <div>
                <div class="text-1">{{item.name}}</div>
                <div class="text-2">{{item.des}}</div>
            </div>
        </div>
    </div>
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
    .text-1{font-size: 32px;margin-bottom: 16px;}
    .text-2{color: $color-grey;}
</style>
