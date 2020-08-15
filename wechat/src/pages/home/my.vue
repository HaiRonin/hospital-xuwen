
<template>
    <div>
        <div class="flex-box f-v align-center justify-center user-info" :style="{backgroundImage: `url(${require('@/assets/image/memb-head.jpg')})`}">
            <div class="van-skeleton van-skeleton--animate" v-if="loading">
                <div class="van-skeleton__avatar van-skeleton__avatar--round" style="width: 140px; height: 140px;margin:auto;"></div>
            </div>
            <template v-else>
                <img class="u-i-headimg" :src="require('@/assets/image/d-male.png')" alt="">
                <div class="text-1">zkp xxx</div>
                <div class="text-2">xxx</div>
            </template>
        </div>
        <van-cell-group>
            <van-cell v-for="(item, index) in quickEntry2" :key="index" center is-link @click="$router.push({name: item.name})">
                 <template #title>
                    <img class="list-img" :src="item.imgUrl" alt="">
                    <span class="list-text">{{item.text}}</span>
                 </template>
            </van-cell>
        </van-cell-group>
    </div>
</template>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import {Skeleton, Cell, CellGroup} from 'vant';
    Vue.use(Cell);
    Vue.use(CellGroup);
    Vue.use(Skeleton);

    @Component
    export default class My extends Vue {
        loading: boolean = false;
        quickEntry2: IOBJ[] = [
            {
                text: '就诊人管理',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_39.png'),
            },
            {
                text: '预约挂号记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_41.png'),
            },
            {
                text: '待缴费处方记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_43.png'),
            },
            {
                text: '已缴费处方记录',
                name: '',
                imgUrl: require('@/assets/image/icon/icon_43.png'),
            },
        ];

        created () {
        }
    }
</script>

<style lang="scss" scoped>
    .list-img{width: 1.2rem;height: 1.2rem;object-fit: contain;margin-right: .5rem;display: inline-block;vertical-align: middle;}
    .list-text{display: inline-block;vertical-align: middle;font-size: .9rem;}

    .user-info{
        background: no-repeat center/cover;
        height: 12rem;
        margin-bottom: 1rem;
        color: #fff;
    }

    .u-i-headimg{
        width: 3.5rem;height: 3.5rem;object-fit: cover;
        border: .15rem solid #fff;
        border-radius: 50%;
    }

    .text-1{font-size: 1.1rem;margin-top: .5rem;}
    .text-2{font-size: .8rem;margin-top: .2rem;}
</style>

