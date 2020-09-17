
<template>
    <div>
        <img class="top-bg" src="@/assets/image/br01.png" alt />
        <div class="flex-box align-center box-1">
            <div class="flex-box align-center justify-center flex-1" v-for="(item, index) in quickEntry1" :key="index"  @click="$router.push({name: item.name})">
                <img :src="item.imgUrl" alt="" class="b-img">
                <div>
                    <div class="b-text-1">{{item.text}}</div>
                    <div class="b-text-2">{{item.text2}}</div>
                </div>
            </div>
        </div>
        <van-grid border :column-num="3" class="">
            <van-grid-item v-for="(item, index) in quickEntry2" :key="index" @click="$router.push({name: item.name})">
                <img class="q-e-img" :src="item.imgUrl"/>
                <div class="q-e-text">{{item.text}}</div>
            </van-grid-item>
        </van-grid>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {Grid, GridItem} from 'vant';
    Vue.use(Grid);
    Vue.use(GridItem);
    // Vue.use(VanImage);

    @Component
    export default class Outpatient extends Vue {
        quickEntry1: IOBJ[] = [
            {
                text: '当日挂号',
                text2: '当日挂号新体验',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_68.png'),
            },
            {
                text: '预约挂号',
                text2: '预约挂号新体验',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_68.png'),
            },
        ];
        quickEntry2: IOBJ[] = [
            {
                text: '智能导诊',
                name: 'outpatientIntelligentGuidanceIndex',
                imgUrl: require('@/assets/image/icon/icon_92.png'),
            },
            {
                text: '门诊缴费',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '报告查询',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_87.png'),
            },
            {
                text: '信息查询',
                name: 'infoIndex',
                imgUrl: require('@/assets/image/icon/icon_81.png'),
            },
            {
                text: '科室介绍',
                name: 'infoDepartmentIsIntroduced',
                imgUrl: require('@/assets/image/icon/icon_91.png'),
            },
        ];

        created () {
        }
    }
</script>

<style lang="scss" scoped>
    .top-bg {
        display: block;
        height: 400px;
        width: 100%;
    }

    .q-e-3{margin-top: 32px;}
    .q-e-img{width: 64px;height: 64px;object-fit: contain;}
    .q-e-text{color: #666;font-size: 24px;margin-top: 16px;}

    .box-1{
        padding: 32px 0;
        background:#fff -webkit-linear-gradient(left, #ebedf0, #ebedf0) no-repeat center/2px 50%;
        border-bottom: 32px solid #ebedf0;
    }
    .b-img{width: 96px;height: 96px;object-fit: contain;margin-right: 25.6px;}
    .b-text-1{font-size: 30.4px;}
    .b-text-2{color: #999;margin-top: 8px;font-size: 24px;}
</style>

