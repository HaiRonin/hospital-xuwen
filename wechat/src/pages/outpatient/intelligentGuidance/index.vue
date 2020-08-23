
<template>
    <div class="box">
        <van-tabs v-model="active" animated>
            <van-tab name="com1" title="身体部位"><intelligent/></van-tab>
            <van-tab name="com2" title="列表"><list/></van-tab>
        </van-tabs>
        <van-popup v-model="modalOpen" get-container="body" position="right" :style="{height: '100%', width: '70%'}">
            <div class="z-title">{{modalTitle}}</div>
            <ul class="z-list">
                <li class="flex-box justify-s-b align-center z-l-item" @click="link">
                    <div>123</div>
                    <div></div>
                </li>
                <!-- <li class="flex-box justify-s-b align-center z-l-item">
                    <div>123</div>
                    <div></div>
                </li> -->
            </ul>
        </van-popup>
        <!-- <keep-alive>
            <router-view />
        </keep-alive>-->
    </div>
</template>

<script lang="ts">
    import {Component, Vue, Provide} from 'vue-property-decorator';
    import intelligent from './intelligent.vue';
    import list from './list.vue';
    import {Tab, Tabs, Popup} from 'vant';
    Vue.use(Tab);
    Vue.use(Tabs);
    Vue.use(Popup);


    @Component({
        components: {
            intelligent,
            list
        },
        provide () {
            return {
                igIndex: this
            };
        }
    })
    export default class Index extends Vue {
        // @Provide('igIndex') igIndex: IOBJ = this;
        active: string = 'com1';
        modalOpen: boolean = false;
        modalTitle: string = '列表';

        openFun (item: IOBJ) {
            this.modalTitle = item.part || '--';
            this.modalOpen = true;
        }

        link (item: IOBJ) {
            this.$router.push({name: 'registrationDiagnosis'});
        }

        created () {
            // this.igIndex = this;
        }
    }
</script>

<style lang="scss" scoped>
    .box {
        height: 100%;
    }

    .box::v-deep .van-tabs{height: 100%;}

    .box::v-deep .van-tabs__content{position: absolute;top: 88px;left: 0;right: 0;bottom: 0;}

    .box::v-deep .van-tab__pane{height: 100%;}
</style>

