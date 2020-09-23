<template>
    <web-view :src="src"></web-view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';

    @Component
    export default class Name extends Vue {

        src = '';

        async onLoad (options: IOBJ) {
            // console.log(options);
            // console.log(options.url);
            // console.log(decodeURIComponent(options.url));
            let url = options.url;
            // await uni.showModal({
            //     content: url
            // });

            // #ifdef H5
            url = decodeURIComponent(url);
            utils.showLoad();
            // setTimeout(() => {
            window.location.replace(url);
            // }, 1000);
            // #endif

            // #ifdef APP-PLUS
            const index = url.indexOf('?');
            const newUrl = url.substr(0, index);
            const strData = encodeURIComponent(url.substr(index + 5));
            this.src = `${newUrl}?url=${strData}`;
            // #endif
        }

        created () {}

        mounted () {
            // #ifdef APP-PLUS
            this.$nextTick(() => {
                var currentWebview = (this as any).$scope.$getAppWebview(); // 此对象相当于html5plus里的plus.webview.currentWebview()。在uni-app里vue页面直接使用plus.webview.currentWebview()无效，非v3编译模式使用this.$mp.page.$getAppWebview()
                var wv = currentWebview.children()[0];
                wv.addEventListener('loaded', (e: IOBJ) => {
                    const url = wv.getURL();
                    if (~url.indexOf('/pages/wvCallBack')) {
                        // console.log(url);
                        utils.link(1);
                    }
                }, false);
            });
            // #endif
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>

</style>
