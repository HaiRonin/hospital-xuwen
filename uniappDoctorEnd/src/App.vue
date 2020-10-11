
<script lang="ts">
    import Vue from 'vue';
    import upDataApp from '@/assets/js/upDataApp';

    export default Vue.extend({
        mpType: 'app',
        onLaunch (options: any) {
            console.log('App Launch', options);

            // #ifdef APP-PLUS
            upDataApp();
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
