
<template>
    <div class="box">
        <van-notice-bar class="z-custom" left-icon="info-o">
            以下推荐科室仅供参考
        </van-notice-bar>
        <van-divider class="z-custom-d" content-position="left">就诊建议</van-divider>
        <div class="text">如果病情持续或加重，建议尽快到医院就诊。</div>
        <van-divider  content-position="left">推荐科室</van-divider>
        <!-- <van-divider>以上推荐科室仅供参考</van-divider> -->
        <div class="flex-box align-center justify-s-b common-block item" v-for="(item, index) in 10" :key="index">
            <div>新生儿科(出生28天内)</div>
            <div class="flex-box align-center justify-s-b">
                <van-button plain hairline round size="small" class="z-btn" @click="link(item, '2')">预约挂号</van-button>
                <van-button plain hairline round size="small" class="z-btn" @click="link(item, '0')">当天挂号</van-button>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {Divider, NoticeBar, Button} from 'vant';
    Vue.use(NoticeBar);
    Vue.use(Divider);
    Vue.use(Button);


    @Component
    export default class Diagnosis extends Vue {

        link (item: IOBJ, type: string) {
            this.$router.push({name: 'registrationDepartmentDoctorList'});
        }

        created () {
        }
    }
</script>

<style lang="scss" scoped>
    .box {
        min-height: 100%;
        background: #fff;
        // overflow: hidden;
    }
    .z-custom {
        color: $main-color;
        background: $bg-main-color;
        position:sticky;
        top: 0;
        left: 0;
        z-index: 2;
    }
    .z-custom-d{margin-bottom: 0;}
    .text{padding: 16px 48px;font-size: 28.8px;}
    // .item{
    //     margin: 0 32px 32px;padding: 25.6px 16px;font-size: 28.8px;
    //     border-radius: 9.6px;
    //     box-shadow: 0 64px 24px rgba(100, 101, 102, 0.12);
    // }
</style>

