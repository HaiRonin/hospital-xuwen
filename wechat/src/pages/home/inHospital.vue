
<template>
    <div>
        <img class="top-bg" src="@/assets/image/br01.png" alt />
        <van-grid border :column-num="2" class="q-e-3">
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


    @Component
    export default class InHospital extends Vue {
        quickEntry2: IOBJ[] = [
            {
                text: '住院信息',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_95.png'),
            },
            {
                text: '押金缴纳',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '押金缴纳记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_106.png'),
            },
            {
                text: '住院清单',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_117.png'),
            },
            {
                text: '出院汇总清单',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_109.png'),
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
</style>

