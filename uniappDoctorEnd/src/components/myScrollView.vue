<template>
    <view>
        <template v-if="isScrollView">
            <scroll-view
                :scroll-y="true"
                :refresher-enabled="true"
                :refresher-triggered="refreshStatus"
                :refresher-threshold="80"
                :refresher-background="refreshBgColor"
                @refresherrefresh="refresh"
                @scrolltolower="scrolltolower"
                class="cur-s-v"
            >
                <slot :list="list"></slot>
                <!-- <loading state="{{state}}"/> -->
                <view class="load-box">
                    <view v-if="loadType[status] === 'loaderror'" @tap="getList()" class="error-text">请求出错了，点击重试</view>
                    <u-loadmore v-else :status="oneAjax ? loadType[status] : 'loading'" bg-color="transparent" @loadmore="getList()"/>
                </view>
            </scroll-view>
        </template>
        <template v-else>
            <slot :list="list"></slot>
            <!-- <loading state="{{state}}"/> -->
            <view class="load-box">
                <view v-if="loadType[status] === 'loaderror'" @tap="getList()" class="error-text">请求出错了，点击重试</view>
                <u-loadmore v-else :status="oneAjax ? loadType[status] : 'loading'" bg-color="transparent" @loadmore="getList()"/>
            </view>
        </template>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Prop} from 'vue-property-decorator';

    @Component
    export default class MyScrollView extends Vue {
        @Prop({type: Function, required: true}) readonly request!: (params?: IOBJ, options?: IOBJ) => Promise<IMyResponse>;
        @Prop({type: Function}) readonly handleItem!: <T>(item: T) => T | void;
        @Prop({type: Function}) readonly handleRes!: (res: IMyResponse) => void;
        @Prop({type: Object, default: () => ({})}) readonly params!: IOBJ;
        @Prop({type: String, default: '#f5f5f9'}) readonly refreshBgColor!: IOBJ;
        // 使用scrollView
        @Prop({type: Boolean, default: false}) readonly isScrollView!: boolean;



        // 设置当前下拉刷新状态，true 表示下拉刷新已经被触发，false 表示下拉刷新未被触发
        refreshStatus: boolean = false;

        // loadmore-加载前的状态，loading-加载中的状态，nomore-没有更多的状态
        status: number = 2;
        loadType: string[] = ['', 'loading', 'loadmore', 'nomore', 'loaderror'];

        // 第一次请求触发了吗
        oneAjax: boolean = false;
        list: IOBJ[] = [];
        curParams: IOBJ = {
            pagenumber: 0,
            count: 15,
        };

        // 1从第一页开始
        async getList (type?: 1) {
            // if (this.loading) return;
            const reset = type === 1;

            if (reset) {
                this.status = 2;
                this.oneAjax = false;
                this.list = [];
            }

            if (this.status === 1 || this.status === 3) return;
            // debugger
            this.status = 1;
            this.oneAjax = true;

            const curParams = this.curParams;
            const params = this.params;
            const handleRes = this.handleRes;
            const handleItem = this.handleItem;

            curParams.pagenumber = reset ? 1 : curParams.pagenumber + 1;

            try {
                const ajaxParams = Object.assign({}, curParams, params);
                const res = await this.request(ajaxParams, {isLoad: false});
                handleRes && handleRes(res);
                // console.log(res);
                const {data = [], countnum = 0} = res;

                handleItem && data.forEach((item: IOBJ, index: number) => {
                    const hItem = handleItem(item);
                    hItem && (data[index] = hItem);
                });

                this.list = this.list.concat(data);

                if (this.list.length >= countnum) {
                    this.status = 3;
                } else {
                    this.status = 2;
                }
                // console.log(this.status);
            } catch (error) {
                console.log(error);
                this.status = 4;
                curParams.pagenumber -= 1;
            }

            return;
        }

        // 自定义下拉刷新被触发
        async refresh () {
            // debugger
            if (this.status === 1 || this.refreshStatus) return;
            this.refreshStatus = true;
            await this.getList(1);
            this.refreshStatus = false;
        }

        // 滚动到底部/右边，会触发 scrolltolower 事件
        scrolltolower () {
            this.getList();
        }

        created () {}

        mounted () {}
    }
</script>

<style lang="scss" scoped>
    .cur-s-v{
        height: 100%;
        width: 100%;
    }

    .load-box{
        padding: 30rpx 0;
    }

    .error-text{
        color: rgb(96, 98, 102);
        font-size: 28rpx;
        text-align: center;
    }
</style>
