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
        initSrc = '/static/html/intelligentGuidance/index.html#/outpatient/intelligentGuidance/index';
        src = '';

        created () {
            // this.src = `${this.src}?env=build`;
            this.src = `${this.initSrc}?parendDomain=${globalConfig.domain.baseApi}`;
            // console.log(this.src);
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
                // var currentWebview = (this as any).$scope.$getAppWebview(); // 此对象相当于html5plus里的plus.webview.currentWebview()。在uni-app里vue页面直接使用plus.webview.currentWebview()无效，非v3编译模式使用this.$mp.page.$getAppWebview()
                // var wv = currentWebview.children()[0];
                // wv.addEventListener('loaded', (e: IOBJ) => {
                //     console.log('loaded: ' + e);
                //     console.log('loaded: ' + wv.getURL());
                // }, false);
                // wv.setStyle({top: 150, height: 300});
            });
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>
</style>
