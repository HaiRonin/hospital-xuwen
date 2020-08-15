<template>
    <div class="z-box">
        <div class="common-block">{{text}}</div>
    </div>
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
        letter-spacing: .05rem;
        font-size: 1.05rem;
        text-indent: 2rem;
    }
</style>
