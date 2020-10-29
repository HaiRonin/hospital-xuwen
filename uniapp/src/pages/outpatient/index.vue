<template>
    <view class="z-box">
        <view class="common-block" v-for="(item) in list" :key="item.CardNo" @tap="selItem(item)">
            <view class="flex-box align-center justify-s-b">
                <view class="flex-1">{{item.Name}}</view>
                <view class="text-1">(就诊卡号{{item.CardNo}})</view>
                <u-icon name="arrow-right" class="text-icon" v-if="isToUrl || isSelModel"></u-icon>
            </view>
            <view class="item flex-box f-v rel" v-if="$store.getters.isTest">
                <view class="flex-box align-center justify-s-b">
                    <view class="text-2">广东省卫生健康委员会</view>
                    <view class="flex-box align-center">
                        <image class="logo-2" :src="require('@/assets/image/healthCard/icon2.png')"/>
                        <view class="text-5">电子健康卡</view>
                    </view>
                </view>

                <template v-if="item.HealthyCardNo">
                    <view class="flex-box align-end justify-s-b content-box" >
                        <view>
                            <view class="text-3">{{item.Name}}</view>
                            <view class="text-4">{{item.newIdNumber}}</view>
                        </view>
                        <!-- <view class="qr-box rel">
                            <image class="qr" :src="require('@/assets/image/healthCard/fake.png')"/>
                            <image class="logo abs" :src="require('@/assets/image/healthCard/logo_.png')"/>
                        </view> -->
                        <imgLoad v-bind="{
                            idType: '1',
                            healthCardId: item.HealthyCardNo,
                            idNumber: item.IDCardno,
                        }"/>
                    </view>

                    <view class="text-6">中华人民共和国国家卫生健康委员会监制</view>
                </template>

                <view class="abs item-mask flex-box align-center justify-center" v-else>
                    <button class="im-btn z-btn-default z-btn-primary" @tap.stop="upDateCardInfo(item)">升级健康卡</button>
                </view>
            </view>
            <view class="flex-box align-center justify-s-b action-box" v-if="!isSelModel">
                <view v-if="isToUrl || isSelModel"></view>
                <view v-else class="text-del flex-1">
                    <view class="text-action" @tap.stop="delPatientCard.openFun(item)">
                        <u-icon name="trash" class="del-icon"></u-icon>删除
                    </view>
                </view>
                <view class="flex-box align-center flex-1 justify-end">
                    <view class="text-action text-action-2" v-if="item.HealthyCardNo" @tap.stop="lookHealthCard(item)">
                        <u-icon name="list-dot" class="del-icon"></u-icon>健康卡信息
                    </view>
                    <view class="text-action text-action-2" @tap.stop="lookPatientCardInfo.openFun(item)">
                        <u-icon name="list-dot" class="del-icon"></u-icon>卡号信息
                    </view>
                    <view class="text-action text-action-2" @tap.stop="lookBarCode.openFun(item)">
                        <u-icon name="tiaoxingma" custom-prefix="z-icon" class="del-icon"></u-icon>查看二维码
                    </view>
                </view>
            </view>
            <view v-else class="fake-padding"></view>
        </view>

        <view @tap="addPatientFun()" v-show="loadCount === 1 && !list.length">
            <u-empty
                text="点击前往添加"
                mode="list"
                margin-top="150"
                icon-size="200"
                font-size="36"
            ></u-empty>
        </view>

        <view class="fake-height"></view>

        <view class="add-box" v-if="loadCount === 1">
            <!-- <u-button v-show="false" type="primary" :plain="false" @tap="addPatient.openFun()">添加就诊人</u-button> -->
            <button class="z-btn-default z-btn-primary" @tap="addPatientFun()">
                {{isH5 && $store.getters.isTest ? '创建健康卡' : '添加就诊人'}}
            </button>
        </view>

        <delPatientCard ref="delPatientCard" />
        <lookPatientCardInfo ref="lookPatientCardInfo" />
        <lookBarCode ref="lookBarCode" />
        <addPatient ref="addPatient" />
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Provide} from 'vue-property-decorator';
    import {queryPatients, healthCardGetHealthCard, addPatients} from '@/apis';
    import delPatientCard from './childAction/delPatientCard.vue';
    import lookPatientCardInfo from './childAction/lookPatientCardInfo.vue';
    import lookBarCode from './childAction/lookBarCode.vue';
    import addPatient from './childAction/addPatient.vue';
    import imgLoad from '@/components/imgLoad.vue';

    @Component({
        components: {
            delPatientCard, lookPatientCardInfo, lookBarCode, addPatient, imgLoad
        }
    })
    export default class Index extends Vue {
        @Ref('delPatientCard') readonly delPatientCard!: IOBJ;
        @Ref('lookPatientCardInfo') readonly lookPatientCardInfo!: IOBJ;
        @Ref('lookBarCode') readonly lookBarCode!: IOBJ;
        @Ref('addPatient') readonly addPatient!: IOBJ;
        @Provide('outpatientIndex') outpatient = this;

        list: IOBJ[] = [];
        options: IOBJ = {};
        isToUrl = false;
        isSelModel = false;
        isH5 = false;
        loadCount = 0;

        upDateCardInfo (item: IOBJ) {
            if (this.isH5) {
                this.addPatient.linkHealthCard(item);
            } else {
                utils.toast('关注公众号"广东省农垦中心医院",升级健康卡');
            }
        }

        addPatientFun () {
            this.isH5 && this.$store.getters.isTest ? this.addPatient.linkHealthCard() : this.addPatient.openFun();
        }

        // 提供给下单时候使用的
        confirmOrderPatient (item: IOBJ) {
            // if (!this.isSelModel) return;
            uni.$emit('upDataPatient', {
                patientNo: item.CardNo,
                patientName: item.Name,
                cardNo: item.IDCardno,
                oldData: item,
            });
            utils.link(1);
        }

        // 跳转下一页
        linkToUrl (item: IOBJ) {
            const toUrl = this.options.toUrl;
            // if (!this.isToUrl && !toUrl) return;
            const is = ~toUrl.indexOf('?');
            // {"bankCardNumber":"","synUserName":"","synKey":"","cardNo":"440822195306247118","visitCardNo":"52400200199564","socialsecurityNO":""}
            const strData = utils.serialize({
                patientNo: item.CardNo,
                name: item.Name,
                idCardno: item.IDCardno,
                // patientNo: '52400200199564',
                // name: item.Name,
                // idCardno: '440822195306247118'
            });
            utils.link(`${toUrl}${is ? '&' : '?'}${strData}`);
        }

        lookHealthCard (item: IOBJ) {
            const strData = utils.serialize({
                // patientNo: item.CardNo,
                name: item.Name,
                idNumber: item.IDCardno,
                phone1: item.Mobile,
                healthCardId: item.HealthyCardNo
            });
            utils.link(`/pages/healthCard/cardInfo?${strData}`);
        }

        selItem (item: IOBJ) {
            if (this.isSelModel) {
                this.confirmOrderPatient(item);
            } else if (this.isToUrl && this.options.toUrl) {
                this.linkToUrl(item);
            } else {
                this.lookHealthCard(item);
            }
        }

        async getList () {
            const res = await queryPatients({HealthyCardNo: ''}, {isLoad: true, closeErrorTips: true}).catch(() => {
                return {data: []};
            });

            res.data.forEach((data: IOBJ) => {
                data.newIdNumber = `${data.IDCardno.substr(0, 4)}***********${data.IDCardno.substr(-4)}`;
                data.newPhone1 = `${data.Mobile.substr(0, 2)}*******${data.Mobile.substr(-2)}`;
            });

            this.list = res.data;
            this.loadCount = 1;
            return Promise.resolve();
        }

        // 健康卡 重定向回来
        setStoreRedirectUri () {
            const options = utils.jsCopyObj(this.options);
            if (options.healthCode) return;
            utils.setStorage('healthCodeRedirectUri', `${globalConfig.domain.webUrl}/pages/outpatient/index?${utils.serialize(options)}`);
        }

        // 健康卡添加就诊人
        async singAddPatientAndHealth (healthInfo: IOBJ) {
            const params = {
                Name: healthInfo.name,
                IDCardno: healthInfo.idNumber,
                CardNo: '',
                Mobile: healthInfo.phone1,
                address: healthInfo.address || '',
                HealthyCardNo: healthInfo.healthCardId,
                // HealthyQrCodeText: healthInfo.qrCodeText,
                Sex: globalConfig.sexState.find((item) => item.text === healthInfo.gender)!.value,
            };
            // console.log(params);

            return await addPatients(params);
        }

        // 添加成功后，返回来的
        async handleAddHealthData () {
            const data = utils.getStorage('addHealthData');
            if (utils.zEmpty(data)) return;
            utils.removeStorage('addHealthData');
            try {
                const {healthRes, params} = data;
                const healthData = JSON.parse(healthRes.msg);

                await this.singAddPatientAndHealth({...params, ...healthData});
            } catch (error) {
                console.error(error);
                utils.toast('添加失败,请重试', 1000);
            }
            this.getList();
        }

        // 关联已有的，返回来这
        async healthAddPatient () {
            let healthCode = this.options.healthCode;
            const pages = getCurrentPages();
            const {route, options} = pages[pages.length - 1] as IOBJ;
            delete options.healthCode;

            healthCode = typeof healthCode === 'undefined' ? '' : `${healthCode}`;

            if (utils.zEmpty(healthCode)) return;
            // debugger;
            // 别的处理 https://open.tengmed.com/doc/#41
            if (healthCode === '0') {
                // const patientItem = this.options.patientItem;
                // utils.link(`/pages/healthCard/cardInfo?healthCode=${healthCode}&patientItem=${patientItem}`, 1);
                // utils.link(`/pages/healthCard/cardInfo?healthCode=${healthCode}`, 1);
                window.history.go(-1);
                return;
            } else if (healthCode === '-1') {
                // utils.toast('请重新操作, 注意需要进行授权', 2000);
                window.history.go(-2);
                return;
            }


            utils.showLoad('添加中');

            try {
                let healthInfo = await healthCardGetHealthCard({healthCode});
                // console.log(healthInfo);

                healthInfo = JSON.parse(healthInfo.msg);
                healthInfo = healthInfo.card;

                await this.singAddPatientAndHealth(healthInfo);

                utils.hideLoad();
                this.getList();
            } catch (error) {
                console.error(error);
                // alert(healthCode);
                // alert(JSON.stringify(error, null, 4));
                await utils.toast('添加失败,请重试', 1000);
            }
            window.history.go(-2);

            // utils.link(utils.getStorage('healthCodeRedirectUri'), 1);
            // window.location.replace(`${globalConfig.domain.webUrl}/${route}?${utils.serialize(options)}`);
        }

        onPullDownRefresh () {
            utils.pullDown(() => this.getList());
        }

        onLoad (options: IOBJ) {
            // toUrl 下一步的跳转路径
            this.options = options;
            // 携带跳转的链接
            this.isToUrl = !!options.toUrl;
            // 下单时候选择诊疗卡
            this.isSelModel = !!options.sel;
            // console.log(globalConfig.sexState.find((item) => item.text === '男')!.value);
            this.setStoreRedirectUri();
        }

        async created () {
            await this.getList();
            this.healthAddPatient();
            // this.handleAddHealthData();

            // #ifdef H5
            this.isH5 = true;
            var browserRule = /^.*((iPhone)|(iPad)|(Safari))+.*$/;
            if (browserRule.test(navigator.userAgent)) {
                window.onpageshow = (event: IOBJ) => {
                    if (event.persisted) {
                        this.getList();
                    }
                };
            }
            // #endif
        }

        mounted () {}

        activated () {}

        beforeDestroy () {
            window.onpageshow = null;
        }

    }
</script>

<style lang="scss" scoped>
    .z-box {
        overflow: initial;
    }

    .text-1 {
        color: $color-grey;
    }

    .add-box {
        padding: 30rpx 50rpx;
        box-shadow: 0 -2rpx 8rpx rgba(100, 101, 102, 0.08);
        position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        background: #fff;
    }

    .fake-height {
        height: 150rpx;
    }

    .common-block {
        padding-bottom: 0;
    }

    .action-box {
        border-top: $border-line;
        margin-top: 26rpx;
        font-size: 26rpx;
    }

    .fake-padding{
        height: 26rpx;
    }

    .text-action {
        padding: 26rpx 0 24rpx;
        // margin-left: 40rpx;
        // display: inline-block;
        white-space: nowrap;
    }

    .text-action-2{padding-right: 10rpx;}
    .text-action-3{color: $main-color;}

    .text-del {
        margin-left: 0;
        color: $main-error-color;
    }

    .del-icon {
        margin-right: 10rpx;
    }
    .text-icon {
        margin-left: 10rpx;
        color: $color-grey;
    }


    .item{
        width: 620rpx;
        height: 350rpx;
        background: url('@/assets/image/healthCard/bg2.png') no-repeat center/cover;
        margin: 0 auto;
        padding:30rpx 15rpx 16rpx 40rpx;
        font-family: PingFangSC-Medium, 'PINGFANG MEDIUM';
        font-weight: bold;
        box-shadow:  0 0rpx 40rpx 2rpx rgba(100, 101, 102, 0.12);
        // border-top: $border-line;
        margin-top: 30rpx;
        border-radius: 12rpx;
        overflow: hidden;
    }

    .item-mask{
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        // filter: blur(2rpx);
        font-weight: initial;
        background: rgba(255,255,255,0.8);
    }

    .text-2{ font-size: 24rpx; }
    .text-3{ font-size: 36rpx; }
    .text-4{ font-size: 30rpx;margin-top: 20rpx;line-height: 0.9; }
    .text-5{font-size: 30rpx;margin-left: 12rpx;margin-right: 4rpx;}
    .text-6{font-size: 24rpx;text-align: center;margin-top: 25rpx;}

    .content-box::v-deep .qr-box{
        height: 162rpx;
        width: 162rpx;
        margin:initial;
    }

    .content-box::v-deep .logo{
        width: 44rpx;
        height: 44rpx;
        top: 50%;
        left: 50%;
        margin-left: -22rpx;
        margin-top: -22rpx;
    }

    .logo-2{
        width: 55rpx;
        height: 55rpx;
    }

    .content-box{
        margin-top: auto;
    }

</style>
