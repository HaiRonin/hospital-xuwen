<template>
    <div class="z-box">
        <van-collapse v-model="activeName" accordion class="collapse-box">
            <van-collapse-item
                v-for="(item) in list"
                :key="item.organId"
                :title="item.name"
                :name="item.organId"
                class="common-block"
            >{{item.ddesc || '暂无介绍信息'}}</van-collapse-item>
        </van-collapse>
    </div>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {Collapse, CollapseItem} from 'vant';
    import {queryDepartmentList} from '@/apis';

    Vue.use(Collapse);
    Vue.use(CollapseItem);

    @Component
    export default class DepartmentIsIntroduced extends Vue {

        activeName: string = '';
        list: IOBJ[] = [];

        async getInfo () {
            const res = await queryDepartmentList();
            this.list = res.data;
        }

        created () {
            this.getInfo();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .common-block{
        padding: 0;
    }

    .collapse-box::after{border:none}
</style>
