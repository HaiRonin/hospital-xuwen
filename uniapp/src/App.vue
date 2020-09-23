
<script lang="ts">
    import Vue from 'vue';
    import wxObj from '@/assets/js/wxUtils';

    export default Vue.extend({
        mpType: 'app',
        onLaunch (options: any) {
            console.log('App Launch', options);

            // #ifdef H5
            if (['pages/wv', 'pages/wvCallBack'].includes(options.path)) return;
            wxObj.cWxAuth.init(options.query, this);
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
