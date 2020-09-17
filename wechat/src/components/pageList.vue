<template>
    <van-list
        v-model="loading"
        :finished="isEnd"
        :isError="isError"
        finished-text="没有更多了"
        error-text="加载失败"
        @load="getList()"
        :immediate-check="true"
    >
        <slot :list="list"></slot>
    </van-list>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Emit, Prop} from 'vue-property-decorator';
    import {List} from 'vant';
    Vue.use(List);

    @Component
    export default class PageList extends Vue {
        @Prop({type: Function, required: true}) readonly request!: (params?: IOBJ, options?: IOBJ) => Promise<IOBJ>;
        @Prop({type: Function}) readonly handleItem!: <T>(item: T) => T | void;
        @Prop({type: Object, default: () => ({})}) readonly params!: IOBJ;

        loading: boolean = false;
        isEnd: boolean = false;
        isError: boolean = false;
        list: IOBJ[] = [];
        curParams: IOBJ = {
            pagenumber: 0,
            count: 15,
        };

        // 1有值从第一页开始
        async getList (type?: 1) {
            // if (this.loading) return;
            const reset = type === 1;

            if (reset) {
                this.isEnd = false;
                this.list = [];
            }

            if (this.isEnd) return;

            this.loading = true;
            const curParams = this.curParams;
            const params = this.params;
            const handleItem = this.handleItem;

            curParams.pagenumber = reset ? 1 : curParams.pagenumber + 1;
            if (reset) {
                curParams.pagenumber = 1;
                this.list = [];
            } else {
                curParams.pagenumber += 1;
            }

            try {
                const ajaxParams = Object.assign({}, curParams, params);
                const res = await this.request(ajaxParams, {isLoad: false});
                const {returnList = [], countnum = 0} = res;

                handleItem && returnList.forEach((item: IOBJ, index: number) => {
                    const hItem = handleItem(item);
                    hItem && (returnList[index] = hItem);
                });

                this.list = this.list.concat(returnList);

                if (this.list.length >= countnum) {
                    this.isEnd = true;
                }
            } catch (error) {
                this.isError = true;
                curParams.pagenumber -= 1;
            }

            this.loading = false;
        }

        created () {
            // this.getList = utils.throttle(this.getList, 300, 300, true);
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>

</style>
