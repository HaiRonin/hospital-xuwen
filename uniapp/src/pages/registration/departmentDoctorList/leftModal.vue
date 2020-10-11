<template>
    <u-popup v-model="show" mode="left" width="60%">
        <view v-for="child in subjects" :key="child.value" class="flex-box justify-s-b align-center item" @tap="selTap(child)">
            <view>{{child.name}}</view>
            <view>
                <u-icon name="checkbox-mark" class="tap-icon" v-show="child.organId === cur"></u-icon>
            </view>
        </view>
    </u-popup>
</template>

<script lang="ts">

    import {Component, Vue, Model, Emit} from 'vue-property-decorator';
    import {queryDepartmentList} from '@/apis';

    @Component
    export default class LeftModal extends Vue {
        @Model('change', {type: String}) readonly value!: string;

        show = false;
        subjects: IOBJ[] = [];
        cur = '';
        curText = '';

        @Emit()
        change () {
            return this.cur;
        }

        selTap (item: IOBJ) {
            this.cur = item.organId;
            this.curText = item.name;
            this.change();
            this.show = false;
        }

        // 获取科室数据
        async getData () {
            const res = await queryDepartmentList({}, {isLoad: true});

            const subjects: IOBJ[] = res.data;

            const cur = this.cur;
            let curText = '';

            subjects.forEach((item) => {
                // item.type === '1' && type1.push(item);
                // item.type === '2' && type2.push(item);
                cur === item.organId && (curText = item.name);
            });

            this.subjects = subjects;

            if (!cur) {
                this.curText = subjects[0].name;
                this.cur = subjects[0].organId;
                this.change();
            } else {
                this.curText = curText;
            }
        }

        openFun () {
            this.show = true;
        }

        created () {
            this.cur = this.value || '';
            this.getData();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-title{
        font-size: 36rpx;text-align: left;padding-left: 30rpx;
        position: sticky;
        left: 0;
        top: 0;
        background: #fff;
    }
    .item{font-size: 28rpx;padding: 30rpx 20rpx;border-bottom:$border-line;}
    .tap-icon{color:$main-color;}
</style>
