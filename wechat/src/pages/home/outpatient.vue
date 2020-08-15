
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
        <van-grid border :column-num="3" class="q-e-3">
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
                name: '',
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
        height: 12.5rem;
        width: 100%;
    }

    .q-e-3{margin-top: 1rem;}
    .q-e-img{width: 2rem;height: 2rem;object-fit: contain;}
    .q-e-text{color: #666;font-size: .75rem;margin-top: .5rem;}

    .box-1{
        padding: 1rem 0;
        background:#fff -webkit-linear-gradient(left, #ebedf0, #ebedf0) no-repeat center/.0625rem 50%;
        border-bottom: .0625rem solid #ebedf0;
    }
    .b-img{width: 3rem;height: 3rem;object-fit: contain;margin-right: .8rem;}
    .b-text-1{font-size: .95rem;}
    .b-text-2{color: #999;margin-top: .2rem;font-size: .75rem;}
</style>

