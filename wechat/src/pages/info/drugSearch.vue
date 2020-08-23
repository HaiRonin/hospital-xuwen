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

        <pageList :request="request" :params="params" :handleItem="handleItem" ref="pageList" class="list-box">
            <template v-slot="{list}">
                <div v-for="(item, index) in list" :key="index" class="common-block item">
                    <div class="flex-box align-center">
                        <div class="text-1">物品名称:</div>
                        <div class="text-2">{{item.Name}}</div>
                    </div>
                    <div class="flex-box align-center">
                        <div class="text-1">规格:</div>
                        <div class="text-2">{{item.spec}}</div>
                    </div>
                    <div class="flex-box align-center">
                        <div class="text-1">价格:</div>
                        <div class="text-2">{{item.price}}元</div>
                    </div>
                    <div class="flex-box align-center">
                        <div class="text-1">产地:</div>
                        <div class="text-2">{{item.manufacturerName}}</div>
                    </div>
                </div>
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
    import {seachitem} from '@/apis';

    Vue.use(ActionSheet);
    Vue.use(Search);

    @Component
    export default class DrugSearch extends Vue {
        @Ref('pageList') readonly pageList!: IOBJ;

        request: TApi = seachitem;
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

        handleItem (item: IOBJ) {
            item.oldPrice = item.price;
            item.price = utils.toFixed(item.oldPrice);
        }

        onSelect (item: IOBJ) {
            this.params.seachType = item.value;
            this.pageList.getList(1);
        }
        onSearch () {
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
        border-bottom:$border-line;
    }
    .search-box::v-deep .van-search__content {
        padding-left:128px;
    }
    .sort-btn {
        width: 144px;
        text-align: center;
        top: 0;
        left: 0;
        bottom: 0;
        background: #fff;
        font-size: 28.8px;
    }
    .sort-text {
        min-width: 96px;
    }

    .list-box{
        padding-top: 32px;
        // background: #fff;
    }

    .item{line-height: 1.8;}
    .text-1{color: $color-grey;min-width: 150px;text-align: right;padding-right: 20px;}
</style>
