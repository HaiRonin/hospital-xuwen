<template>
    <web-view :src="src"></web-view>
    <!-- <web-view src="/hybrid/intelligentGuidance/index.html/#/outpatient/intelligentGuidance/index"></web-view> -->
    <!-- <web-view src="http://localhost:8091/"></web-view> -->
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import './intelligentGuidanceApi';

    @Component
    export default class Index extends Vue {

        // development
        // test
        // production
        src = '/static/html/intelligentGuidance/index.html#/outpatient/intelligentGuidance/index';

        created () {
            this.src = `${this.src}?env=build`;
            console.log(this.src);
        }

        mounted () {
            this.$nextTick(() => {
                // const currentWebview = this.$scope.$getAppWebview();
                // const wv = currentWebview.children()[0];
                // wv.setStyle({top: 150, height: 300});
                // wv.intelligentGuidance = ilgApu;
                // console.log(wv.parent());
                // wv.addEventListener('loaded', () => console.log('load'));
                // wv.intelligentGuidance = ilgApu;
                // console.log(this.$scope);
                // console.log(currentWebview.intelligentGuidance = ilgApu);
                // console.log(plus.webview.window);
                // plus.webview.intelligentGuidance = ilgApu;
                // const wv = currentWebview.children()[0];
                // console.log(wv);
            });
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>

</style>
