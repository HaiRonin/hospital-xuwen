<template>
    <u-popup
        v-model="show"
        mode="top"
        border-radius="14"
        :mask-close-able="false"
        :closeable="false"
    >
        <u-form class="content" label-width="170" ref="uForm" :model="params">
            <u-form-item label="姓名" required>
                <u-input  v-model="params.Name" maxlength="5" placeholder="请输入姓名" type="text"></u-input>
            </u-form-item>
            <u-form-item label="身份证号码" required>
                <u-input  v-model="params.IDCardno" maxlength="18" placeholder="请输入身份证号码" type="idcard"></u-input>
            </u-form-item>
            <u-form-item label="就诊卡号">
                <u-input  v-model="params.CardNo" maxlength="18" placeholder="请输入已有的就诊卡号" type="text"></u-input>
            </u-form-item>
            <u-form-item label="联系电话" required>
                <u-input  v-model="params.Mobile" maxlength="11" placeholder="请输入联系电话" type="number"></u-input>
            </u-form-item>
            <u-form-item label="性别" required>
                <u-radio-group v-model="params.Sex" :icon-size="24">
                    <u-radio
                        v-for="(item, index) in sortSex" :key="index"
                        :name="item.value"
                    >
                        {{item.text}}
                    </u-radio>
                </u-radio-group>
            </u-form-item>
            <u-form-item label="家庭住址" required>
                <u-input  v-model="params.address" placeholder="请输入家庭住址" type="textarea"></u-input>
            </u-form-item>

            <view class="z-btn-box">
                <u-button v-if="isH5" class="flex-1 z-btn-2" type="warning" shape="circle" @tap="linkHealthCard">关联建康卡</u-button>
                <view class="flex-box align-center">
                    <u-button class="flex-1 z-btn" shape="circle" @tap="show = false;">取消</u-button>
                    <u-button class="flex-1 z-btn" type="primary" shape="circle" @tap="commit">添加</u-button>
                </view>
            </view>
        </u-form>
    </u-popup>
</template>

<script lang="ts">

    import {Component, Vue, Inject} from 'vue-property-decorator';
    import {addPatients} from '@/apis';

    @Component
    export default class AddPatient extends Vue {
        @Inject('outpatientIndex') readonly outpatientIndex!: IOBJ;

        show = false;
        isH5 = false;
        check = (() => {
            const c = new utils.CheckVal({
                Name: '请输入姓名',
                IDCardno: '请输入身份证号码',
                Mobile: '请输入联系电话',
                address: '输入家庭住址',
            });

            c.Mobile = c.phone;
            c.IDCardno = c.idCard;

            return c;
        })();

        params: IOBJ = {
            Sex: 1,
            Mobile: '',
        };

        // 0女，1男，3表示未知
        sortSex: IOBJ[] = [
            {text: '男', value: '1'},
            {text: '女', value: '0'},
            {text: '其他', value: '3'},
        ];

        openFun (item: IOBJ) {
            const user = this.$store.getters.userInfo;
            this.params = {
                Mobile: user.userName,
                Sex: 1,
                CardNo: '',
            };
            this.show = true;
        }

        linkHealthCard () {
            const pages = getCurrentPages();
            const page = pages[pages.length - 1] as IOBJ;
            // console.log(page.route);
            // console.log(page.options);
            // console.log(page);
            const redirectUri = encodeURIComponent(`${globalConfig.domain.webUrl}/${page.route}?${utils.serialize(page.options)}`);
            // console.log(`https://health.tengmed.com/open/getHealthCardList?redirect_uri=${redirectUri}`);
            window.location.replace(`https://health.tengmed.com/open/getHealthCardList?redirect_uri=${redirectUri}`);
        }

        async commit () {
            const data = this.params;
            if (this.check.run(data)) return;

            const res = await addPatients(data, {isLoad: true});

            if (res.resultMsg === '就诊人已添加！') {
                await utils.toast(res.resultMsg, 1000, true);
            }

            this.outpatientIndex.getList();
            this.show = false;
        }

        created () {
            this.commit = utils.throttle(this.commit, 300, 300, true);


            // #ifdef H5
            if (['15919865119', '13418803185', '13543599335'].includes(this.$store.getters.userInfo.userName)) {
                this.isH5 = true;
            }
            // #endif
        }

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content{
        padding: 0 40rpx;
    }

    .z-btn-box {
        padding:20rpx 0;
    }
    .z-btn{
        margin: 0 20rpx;
    }
    .z-btn-2{
        margin: 0 20rpx 20rpx;
    }
</style>
