<template>
    <view class="z-box">
        <template v-if="list.length">
            <rich-text class="text-1" :nodes="text"/>

            <view class="common-block" v-for="(item, index) in list" :key="item.questionId">
                <view class="title-1">{{index + 1}}：{{item.question}}</view>
                <u-radio-group v-model="params[index].answerId" :icon-size="24" width="50%">
                    <u-radio
                        v-for="(child, cindex) in item.answerList" :key="cindex"
                        :name="child.answerId"
                    >
                        {{child.answer}}
                    </u-radio>
                </u-radio-group>
            </view>

            <view class="fake-height"></view>

            <view class="btn-box">
                <button class="z-btn-default z-btn-primary" @tap="save">提交</button>
            </view>
        </template>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {getDocCallBack, saveDocCallBack} from '@/apis';

    @Component
    export default class Questionnaire extends Vue {

        list: IOBJ[] = [];
        params: IOBJ[] = [];
        text = '';

        async getData () {
            const res = await getDocCallBack({}, {isLoad: true});

            this.list = res.data;
            this.text = res.resultCode;
            this.params = res.data.map((item: IOBJ) => ({questionId: item.questionId, answerId: ''}));
        }

        async save () {
            const data = this.params;
            for (const index in data) {
                const item = data[index];
                if (utils.zEmpty(item.answerId)) {
                    utils.toast(`请选择第${+index + 1}题的答案`);
                    return;
                }
            }

            await saveDocCallBack({callBackList: data}, {isLoad: true});
            await utils.toast('提交成功', 500, true);
            utils.link(1);
        }

        created () {
            this.save = utils.throttle(this.save, 300, 300, true);
            this.getData();
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .z-box{
        overflow: initial;
    }
    .btn-box{
        padding: 30rpx 50rpx;
        box-shadow: 0 -2rpx 8rpx rgba(100, 101, 102, 0.08);
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        background: #fff;
    }

    .fake-height {
        height: 170rpx;
    }

    .title-1{
        font-size: 32rpx;
        line-height: 1.8;
    }

    .text-1{
        font-size: 28rpx;
        padding:0 32rpx;
        display: block;
        line-height: 1.6;
        color:$color-grey;
        margin-bottom: 32rpx;
    }
</style>
