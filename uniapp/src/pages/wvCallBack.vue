<template>
    <div class="">
        回调页面
    </div>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';

    @Component
    export default class WvCallBack extends Vue {

        created () {}

        mounted () {
            // utils.link(10);
            // (uni as any).webView.navigateBack();
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>

</style>
