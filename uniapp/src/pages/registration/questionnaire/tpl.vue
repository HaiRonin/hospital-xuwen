<template>
    <view class="">
        <view v-for="(item, index) in list" :key="index" class="item">
            <template v-if="item.type === 'radio'">
                <view>{{item.text}}</view>
                <u-radio-group v-model="params[item.key]">
                    <u-radio :name="1">是</u-radio>
                    <u-radio :name="-1">否</u-radio>
                </u-radio-group>
            </template>
            <view v-else-if="item.type === 'input'" class="flex-box align-center">
                <view>{{item.text}}</view>
                <u-input placeholder="请输入" class="flex-1 input-style-1" v-model="params[item.key]"/>
            </view>
            <template v-else-if="item.type === 'text'">
                <view>{{item.text}}</view>
            </template>
            <template v-if="item.children && item.children.length">
                <oneself :list="item.children" :params="params"/>
            </template>
        </view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Prop} from 'vue-property-decorator';

    @Component({
        name: 'oneself'
    })
    export default class Tpl extends Vue {

        @Prop({type: Array, required: true}) list!: IOBJ[];
        @Prop({type: Object, required: true}) params!: IOBJ[];

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .item{margin-top: 20rpx;}
    .input-style-1{}
</style>
