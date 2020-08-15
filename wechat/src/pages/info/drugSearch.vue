<template>
    <div class>
        <van-search
            v-model="params.seachString"
            show-action
            placeholder="请输入搜索关键词"
            @search="onSearch"
            class="search-box"
        >
            <template #left>
                <div
                    class="sort-btn abs flex-box align-center justify-center"
                    @click="seachOpen = true;"
                >
                    <div class="sort-text">{{params.seachType === 1 ? '药 品' : '非药品'}}</div>
                    <van-icon name="arrow-down" />
                </div>
            </template>
            <template #action>
                <div @click="onSearch">搜索</div>
            </template>
        </van-search>

        <pageList :request="request" :params="params" ref="pageList">
            <template v-slot="{list}">
                <div v-for="(item, index) in list" :key="index">{{item.Name}}</div>
            </template>
        </pageList>

        <van-action-sheet
            v-model="seachOpen"
            :actions="sort"
            cancel-text="取消"
            close-on-click-action
            @select="onSelect"
        />
    </div>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {ActionSheet, Search} from 'vant';
    import {infoApi} from '@/apis';

    Vue.use(ActionSheet);
    Vue.use(Search);


    @Component
    export default class DrugSearch extends Vue {
        @Ref('pageList') readonly pageList!: IOBJ;

        request: TApi = infoApi.getSeachList;
        seachOpen: boolean = false;
        params: IOBJ = {
            seachString: '',
            seachType: 1,
            // pagenumber: 1,
            // count: 10,
        };
        sort: IOBJ[] = [
            {name: '药品', value: 1},
            {name: '非药品', value: 2},
        ];

        onSelect (item: IOBJ) {
            this.params.seachType = item.value;
            this.pageList.getList(1);
        }
        onSearch (...a) {
            this.pageList.getList(1);
        }
        getList () {}

        created () {}

        mounted () {
            // infoApi.getSeachList();
            // this.pageList.getList(1);
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .search-box {
        position: sticky;
        left: 0;
        top: 0;
    }
    .search-box::v-deep .van-search__content {
        padding-left: 4rem;
    }
    .sort-btn {
        width: 4.5rem;
        text-align: center;
        top: 0;
        left: 0;
        bottom: 0;
        background: #fff;
        font-size: 0.9rem;
    }
    .sort-text {
        min-width: 3rem;
    }
</style>
