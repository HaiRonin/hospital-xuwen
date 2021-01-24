
<script lang="ts">
    import Vue from 'vue';
    import wxObj from '@/assets/js/wxUtils';
    import {wechatConfig, getInnerWhitelist} from '@/apis';
    import upDataApp from '@/assets/js/upDataApp';
    import store from '@/store';

    export default Vue.extend({
        mpType: 'app',
        onLaunch (options: any) {
            console.log('App Launch', options);

            // console.log(this.$store === store);
            getInnerWhitelist().then((res) => {
                // console.log(res);
                const data = res.data;
                store.commit('setWhiteData', {
                    // 1-内测,白名单可访问,2-正式开放,用户可访问；3-关闭下架,任何人不可访问
                    whiteListStatus: data.state,
                    whiteList: data.whiteList
                });
            });

            // #ifdef APP-PLUS
            upDataApp();
            // #endif

            // #ifdef H5
            if (globalConfig.banAuth.includes(`/${options.path}`)) return;
            wxObj.cWxAuth.init(options.query, this);
            // const signedUrl = window.location.href.replace(window.location.search, '');
            const signedUrl = window.location.href;
            wechatConfig({signedUrl}).then((res) => {
                // console.log(signedUrl);
                // console.log(res);
                wxObj.cWxConfig(res.data);
            });
            // #endif
        },
        onShow () {
            console.log('App Show');
        },
        onHide () {
            console.log('App Hide');
        }
    });
</script>

<style lang="scss">
    /*每个页面公共css */
    @import "uview-ui/index.scss";
    @import "@/assets/style/common.scss";
    @import "@/assets/style/font/iconfont.css";
</style>
