<template>
    <view class>
        <view class="flex-box search-box align-center">
            <view
                class="sort-btn flex-box align-center justify-center"
                @tap="seachOpen = true;"
            >
                <view class="sort-text">{{params.seachType === 1 ? '药 品' : '非药品'}}</view>
                <u-icon name="arrow-down" class="item-icon"></u-icon>
            </view>

            <u-search
                placeholder="请输入搜索关键词"
                shape="round"
                clearabled
                show-action
                v-model="params.seachString"
                @search="onSearch"
                @custom="onSearch"
                class="flex-1"
            ></u-search>
        </view>

        <myScrollView :request="request" :params="params" :handleItem="handleItem" ref="myScrollView">
            <template v-slot="{list}">
                <view v-for="(item, index) in list" :key="index" class="common-block item">
                    <view class="flex-box align-center">
                        <view class="text-1">物品名称:</view>
                        <view class="text-2">{{item.name}}</view>
                    </view>
                    <view class="flex-box align-center">
                        <view class="text-1">规格:</view>
                        <view class="text-2">{{item.spec}}</view>
                    </view>
                    <view class="flex-box align-center">
                        <view class="text-1">价格:</view>
                        <view class="text-2">{{item.price}}元</view>
                    </view>
                    <view class="flex-box align-center">
                        <view class="text-1">产地:</view>
                        <view class="text-2">{{item.manufacturerName}}</view>
                    </view>
                </view>
            </template>
        </myScrollView>

        <u-action-sheet :list="sort" v-model="seachOpen" :border-radius="20" @click="onSelect"></u-action-sheet>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import myScrollView from '@/components/myScrollView.vue';
    import {seachitem} from '@/apis';

    @Component({
        components: {
            myScrollView: myScrollView
        }
    })
    export default class DrugSearch extends Vue {
        @Ref('myScrollView') readonly myScrollView!: IOBJ;

        request: TApi = seachitem;
        seachOpen: boolean = false;
        params: IOBJ = {
            seachString: '',
            seachType: 1,
        };

        sort: IOBJ[] = [
            {text: '药品', value: 1},
            {text: '非药品', value: 2},
        ];

        handleItem (item: IOBJ) {
            item.oldPrice = item.price;
            item.price = utils.toFixed(item.oldPrice);
        }

        onSelect (index: number) {
            const item = this.sort[index];
            this.params.seachType = item.value;
            this.myScrollView.getList(1);
        }

        onSearch () {
            this.myScrollView.getList(1);
        }

        onPullDownRefresh () {
            utils.pullDown(() => this.myScrollView.getList(1));
        }

        onReachBottom () {
            this.myScrollView.getList();
        }

        getList () {}

        created () {}

        mounted () {
            // infoApi.getSeachList();
            this.myScrollView.getList(1);
        }

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .search-box {
        position: sticky;
        left: 0;
        top: 0;
        border-bottom: $border-line;
        background: #fff;
        // padding: 20rpx 20rpx 20rpx 0;
        padding-right: 20rpx;
        height: 100rpx;
        z-index: 2;
    }
    .search-box::v-deep .van-search__content {
        padding-left: 128rpx;
    }
    .sort-btn {
        width: 150rpx;
        text-align: center;
        // top: 0;
        // left: 0;
        // bottom: 0;
        // background: #fff;
        font-size: 28.8rpx;
    }
    .sort-text {
        min-width: 96rpx;
    }

    .list-box {
        // padding-top: 32rpx;
        // background: #fff;
        position: absolute;
        top: 100rpx;
        left: 0;
        bottom: 0;
        right: 0;
    }

    .item {
        line-height: 1.8;
        margin: 32rpx 32rpx 0;
    }

    .text-1 {
        color: $color-grey;
        min-width: 150rpx;
        text-align: right;
        padding-right: 20rpx;
    }
</style>
