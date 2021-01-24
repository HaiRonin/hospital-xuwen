<template>
    <view class="z-box">
        <view class="common-block">
            <view class="title">问卷调查</view>
            <view class="flex-box align-center item">
                <view>姓名：{{params.PatName}}</view>
                <view style="width:60rpx;"></view>
                <view>卡号：{{params.CardNo}}</view>
            </view>
            <!-- <Tpl :list="topicList" :params="params"/> -->
            <view v-for="(item, index) in topicList" :key="index" class="item">
                <template v-if="item.type === 'radio'">
                    <view>{{item.text}}</view>
                    <u-radio-group class="radio-style-1" v-model="params[item.key]">
                        <u-radio :name="1">是</u-radio>
                        <u-radio :name="-1">否</u-radio>
                    </u-radio-group>
                </template>
                <view v-else-if="item.type === 'input'" class="flex-box align-center">
                    <view>{{item.text}}</view>
                    <u-input placeholder="请输入" class="flex-1 input-style-1" v-model="params[item.key]"/>
                </view>
            </view>
            <view class="item">{{complexData.text}}</view>
            <u-radio-group v-model="params[complexData.key]" wrap  class="radio-style-1">
                <u-radio v-for="(item, index) in complexData.children" :key="index" :name="item.value"  class="flex-box align-center">
                    <view>{{item.text}}</view>
                    <view v-if="item.children && item.children.length" class="flex-box align-center">
                        <view v-for="(child, cindex) in item.children" :key="cindex" class="flex-box align-center">
                            <view>{{child.text}}</view>
                            <u-input placeholder="请输入" class="flex-1 input-style-1" v-model="params[child.key]"/>
                        </view>
                    </view>
                </u-radio>
                <!-- <u-radio :name="-1">否</u-radio> -->
            </u-radio-group>
            <!-- <u-button @click="submit" type="pri">提交</u-button> -->
        </view>

        <view style="height:100rpx;"></view>
        <view class="bottom-btn" @tap="submit">提交</view>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';
    import {addCovSurvey} from '@/apis';
    import Tpl from './tpl.vue';

    @Component({
        components: {
            Tpl
        }
    })
    export default class Questionnaire extends Vue {

        params: IOBJ = {
            WirteDate: '',
            IdCardNo: '',
            CardNo: '',
            Mobile: '',
            PatName: '',
            // IsFever: ''
        };

        topicList: IOBJ = [
            {
                text: '您14天内是否有发热、咳嗽等呼吸道症状?',
                key: 'IsFever',
                type: 'radio'
            },
            {
                text: '体温度数',
                key: 'FeverDegree',
                type: 'input'
            },
            {
                text: '近14天内是否去过国外、香港以及国内中高风险地区*，或有病例报告的社区?',
                key: 'IsGoOutArea',
                type: 'radio'
            },
            {
                text: '近14天内是否接触过来自国外、香港以及国内中高风险地区*的发热或有呼吸道症状的患者?',
                key: 'IsTouchOutArea',
                type: 'radio'
            },
            {
                text: '近14天内是否与新冠病毒感染者（核酸检测阳性者）有接触?',
                key: 'IsTouchCovPat',
                type: 'radio'
            },
            {
                text: '近14天内您的家庭或办公室等小范围内是否出现2例及以上发热和/或呼吸道症状的病例?',
                key: 'IsHaveTwoFever',
                type: 'radio'
            },
        ];

        complexData: IOBJ = {
            text: '您14天内从哪个地区来本市?',
            // type: 'area',
            key: 'area',
            // value: [
            //     {key: 'IsAlwaysThis', value: 1},
            //     {key: 'IsAlwaysDomestic', value: 2},
            //     {key: 'IsAlwaysHK', value: 3},
            //     {key: 'IsAlwaysMacau', value: 3},
            //     {key: 'IsAlwaysTaiwan', value: 4},
            //     {key: 'IsAlwaysForeign', value: 5},
            // ],
            children: [
                {
                    text: '一直在本市',
                    key: 'IsAlwaysThis',
                    value: 1,
                },
                {
                    text: '国内',
                    key: 'IsAlwaysDomestic',
                    value: 2,
                    children: [
                        {
                            text: '省',
                            key: 'Provinces',
                            type: 'input'
                        },
                        {
                            text: '市',
                            key: 'City',
                            type: 'input'
                        },
                        {
                            text: '区',
                            key: 'Area',
                            type: 'input'
                        },
                    ]
                },
                {
                    text: '香港',
                    key: 'IsAlwaysHK',
                    value: 3,
                },
                {
                    text: '澳门',
                    key: 'IsAlwaysMacau',
                    value: 4,
                },
                {
                    text: '台湾',
                    key: 'IsAlwaysTaiwan',
                    value: 5,
                },
                {
                    text: '境外',
                    key: 'IsAlwaysForeign',
                    value: 6,
                    children: [
                        {
                            text: '境外国家或地区',
                            key: 'ForeignPlace',
                            type: 'input'
                        },
                    ]
                },
            ]
        };

        check = (() => {
            const c = new utils.CheckVal({
                IsFever: '请选择是否发热',
                FeverDegree: '请输入体温度数',
                IsGoOutArea: '请选择是否去过高风险地区',
                IsTouchOutArea: '请选择是否接触过来自高风险地区的病人',
                IsTouchCovPat: '请选择是否接触新冠病人',
                IsHaveTwoFever: '请选择是否小范围内出现2例发热',
                area: '请选择最近所在区域',
            });

            c._addRule('area', (val, data) => {
                let str = '';
                if (val === 2 && (utils.zEmpty(data.Provinces) || utils.zEmpty(data.City) || utils.zEmpty(data.Area))) {
                    str = '请输入省市区';
                } else if (val === 6 && utils.zEmpty(data.ForeignPlace)) {
                    str = '请输入境外国家或地区';
                }
                return str;
            });

            return c;
        })();


        async submit () {
            const params = utils.jsCopyObj(this.params);
            if (this.check.run(params)) return;

            const area = params.area;
            const arr = [
                'IsAlwaysThis',
                'IsAlwaysDomestic',
                'IsAlwaysHK',
                'IsAlwaysMacau',
                'IsAlwaysTaiwan',
                'IsAlwaysForeign',
            ];

            delete params.area;

            Object.keys(params).forEach((key) => {
                const val = params[key];
                if (val === 1 || val === -1) {
                    params[key] = +!!~val;
                }
            });
            arr.forEach((text, index) => {
                params[text] = +(index + 1 === area);
            });

            console.log(params);

            await addCovSurvey(params);
            utils.setStorage(`questionnaire-${params.CardNo}`, Date.now());
            utils.link(1).then(() => {
                uni.$emit('payCommit');
            });
        }

        onLoad (options: IOBJ) {
            this.params = {
                WirteDate: this.$store.getters.day[0],
                IdCardNo: options.cardNo,
                CardNo: options.patientNo,
                Mobile: options.Mobile,
                PatName: options.patientName,
                Provinces: '',
                City: '',
                Area: '',
                ForeignPlace: '',
            };
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .item{margin-top: 40rpx;}
    .input-style-1{
        padding: 10rpx !important;
    }
    .radio-style-1{
        padding-left: 40rpx;
    }

    .bottom-btn {
        position: fixed;
        left: 0;
        bottom: 0;
        right: 0;
        width: 100%;
        line-height: 90rpx;
        font-size: 34rpx;
        letter-spacing: 2rpx;
        background: $main-cur-color;
        color: #fff;
        text-align: center;
        z-index: 2;
    }

    .title{font-size: 40rpx;font-weight: bold;}
</style>
