<template>
    <view class>
        <view class="flex-box search-box align-center">
            <view
                class="sort-btn flex-box align-center justify-center"
                @tap="seachOpen = true;"
            >
                <view class="sort-text">{{sortText}}</view>
                <u-icon name="arrow-down" class="item-icon"></u-icon>
            </view>

            <u-search
                placeholder="请输入项目的拼音首字母"
                shape="round"
                clearabled
                show-action
                v-model="params.seachString"
                @search="onSearch"
                @custom="onSearch"
                class="flex-1"
            ></u-search>
        </view>
        <view class="z-custom flex-box align-center" @tap="explainOpen = !explainOpen">
            <u-icon name="info-circle" class="zc-icon"></u-icon>
            <view class="flex-1" :class="{'old-ellipsis': !explainOpen}">
                1、标有★的属于医保范围。
                <br/>
                2、根据社保统一规定，所有医保项目均是先自付后按参保类别（职工、居民）进行统筹报销。其中“甲类”项目先自付0%，“乙类”项目自付10%，“丙类”项目自付100%（此类不得报销），“丁类”项目先自付20%，“戊类”项目先自付30%，“己类”项目先自付50%。
            </view>
            <view class="flex-box align-center zc-control">
                <view>{{explainOpen ? '收起' : '展开'}}</view>
                <u-icon :name="explainOpen ? 'arrow-up' : 'arrow-down'" class="zc-icon-2"></u-icon>
            </view>
        </view>
        <!-- <u-alert-tips type="primary" class="z-custom" show-icon description="以下推荐科室\n仅供参考"></u-alert-tips> -->


        <myScrollView :request="request" :params="params" :handleItem="handleItem" ref="myScrollView">
            <template v-slot="{list}">
                <view v-for="(item, index) in list" :key="index" class="common-block item">
                    <view class="flex-box align-center">
                        <view class="text-1">物品名称:</view>
                        <view class="text-2">{{item.name}}</view>
                    </view>
                    <view class="flex-box align-center">
                        <view class="text-1">医保类别:</view>
                        <view class="text-2">{{item.ybType}}</view>
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

        request: TApi = (params, options) => seachitem(params, Object.assign({closeErrorTips: true}, options)).catch(() => ({returnList: []}));
        seachOpen: boolean = false;
        explainOpen = false;
        params: IOBJ = {
            seachString: '',
            seachType: 1,
        };

        sort: IOBJ[] = [
            {text: '药品', value: 1},
            {text: '医疗服务', value: 2},
            {text: '医用耗材', value: 3},
        ];

        get sortText () {
            // this.params.seachType
            // globalConfig.gFilter(this.params.seachType, this.sort)

            return globalConfig.gFilter(this.params.seachType, this.sort);
        }

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
    .z-custom{
        position: sticky;
        left: 0;
        top: 100rpx;
        border:none;
        font-size: 26rpx;
        background:$u-type-primary-light;
        color:$u-content-color;
        padding: 16rpx 30rpx;
        // line-height: 2;
        line-height: 1.6;
    }

    .zc-icon{
        color: $u-type-primary;
        font-size: 40rpx;
        margin-right: 16rpx;
    }

    .zc-control{
        color: $main-error-color;
    }

    .zc-icon-2{
        margin-left: 4rpx;
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
