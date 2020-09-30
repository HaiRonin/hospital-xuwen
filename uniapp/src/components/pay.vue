<template>
    <view>
        <u-modal
            v-model="show"
            ref="modal"
            :show-title="false"
            :show-confirm-button="false"
            show-cancel-button
        >
            <view class="content">
                <view
                    class="flex-box align-center item"
                    v-for="(item, index) in list"
                    :key="index"
                    @tap="selPay(item.key)"
                    v-show="item.show"
                >
                    <u-icon :name="item.icon" class="icon-item" :color="item.iconColor"></u-icon>
                    <view class="flex-1">{{item.text}}</view>
                </view>
                <!-- <view class="flex-box align-center item">
                    <u-icon name="zhifubao" class="icon-item" color="#1677ff"></u-icon>
                    <view class="flex-1">支付宝支付</view>
                </view>-->
            </view>
        </u-modal>

        <u-modal v-model="modalShow" content="确认支付成功吗" title="温馨提示" @confirm="paySuccess" show-cancel-button></u-modal>
    </view>
</template>

<script lang="ts">

    import {Component, Vue, Ref, Prop, Emit} from 'vue-property-decorator';
    import wxObj from '@/assets/js/wxUtils';

    @Component
    export default class Name extends Vue {
        @Prop({type: Function, required: true}) readonly request!: TApi;

        params: IOBJ = {};
        show = false;
        modalShow = false;
        list: IOBJ = {
            wxpay: {key: 'wxpay', icon: 'weixin-fill', iconColor: '#04BE02', text: '微信支付', show: false},
            alipay: {key: 'alipay', icon: 'zhifubao', iconColor: '#1677ff', text: '支付宝支付', show: false},
        };

        @Emit('paySuccess')
        paySuccess () {
            console.log('支付成功');
        }

        // 获取支付平台
        getProvider () {
            return new Promise((rel, rej) => {
                uni.getProvider({
                    service: 'payment',
                    success (res: IOBJ) {
                        // console.log(res);
                        // alipay wxpay
                        const provider: any[] = res.provider;

                        if (!provider.length) {
                            rej({errMsg: '获取不到支付平台', res});
                            return;
                        }
                        rel(provider);
                        // rel(['wxpay', 'alipay']);
                    },
                    fail (err: IOBJ) {
                        rej(err);
                    }
                });
            });
        }

        requestPayment (provider: 'alipay' | 'wxpay', orderInfo: IOBJ | string) {
            return new Promise((rel, rej) => {
                uni.requestPayment({
                    provider,
                    orderInfo: orderInfo as string, // 微信、支付宝订单数据
                    success: (res) => {
                        // console.log('success:' + JSON.stringify(res));
                        rel(res);
                        // this.paySuccess();
                    },
                    fail: (err) => {
                        // console.log('fail:' + JSON.stringify(err));
                        rej(err);
                    }
                });
            });
        }

        // app开始支付
        async appStart () {
            utils.showLoad('准备中');

            try {
                const platformArr = await this.getProvider();
                (platformArr as string[]).forEach((key) => {
                    const item = this.list[key];
                    item && (item.show = true);
                });

                this.show = true;
            } catch (error) {
                console.error(error);
                utils.toast('获取不到支付平台');
            }

            utils.hideLoad();
        }

        // 浏览器支付（app内使用这种解决方案）
        wxH5Pay (url: string) {
            // alert('https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb');
            // plus.webview.create('https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb');

            // const str = utils.serialize({
            //     prepay_id: 'wx191526400451970269c41f3ab281230000',
            //     package: 'prepay_id=wx191526400451970269c41f3ab281230000',
            //     redirect_url: encodeURIComponent('http://apptest.gdsnkzxyy.cn/pages/wv')
            // });
            // const url = `https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?${str}`;
            const webUrl = `${globalConfig.domain.webUrl}/pages/wv?url=${encodeURIComponent(url)}`;
            // console.log(webUrl);
            utils.link(`/pages/wv?url=${webUrl}`);
        }

        async selPay (type: 'alipay' | 'wxpay') {
            // 请求接口，得到相应的数据

            try {
                // 调起支付
                // await this.requestPayment(type, {});
                const params = this.params;
                params.payType = type === 'wxpay' ? 7 : 2;
                params.redirectUrl = `${globalConfig.domain.webUrl}/pages/wvCallBack`;
                const res = await this.request(params, {isLoad: true});
                // console.log(res);
                // console.log(res.data.prePaySign.orderInfo.replace(/"/g, ''));
                type === 'wxpay' && this.wxH5Pay(res.data.prePaySign.payUrl);
                type === 'alipay' && this.requestPayment('alipay', res.data.prePaySign.orderInfo.replace(/"/g, ''));
                // type === 'alipay' && this.requestPayment('alipay', encodeURIComponent(res.data.prePaySign.orderInfo.replace(/"/g, '')));

                this.show = false;
                this.modalShow = true;
            } catch (error) {
                console.error(error);
                utils.toast('支付失败1');
            }
        }

        // 微信内支付
        async wxJsPay () {
            try {
                const params = this.params;
                params.payType = 5;
                params.openId = utils.getStorage('openId');

                const res = await this.request(params, {isLoad: true});

                utils.showLoad('请稍后');
                const prePaySign = res.data.prePaySign;

                await wxObj.cChooseWXPayPromise2({
                    appId: prePaySign.appId,
                    timeStamp: prePaySign.timeStamp,
                    nonceStr: prePaySign.nonceStr,
                    package: prePaySign.package,
                    signType: prePaySign.signType,
                    paySign: prePaySign.paySign,
                });

                this.modalShow = true;

                utils.hideLoad();
            } catch (error) {
                console.error(error);
            }
        }

        handleData () {}

        async startPay (data: IOBJ) {
            const params = this.params = utils.jsCopyObj(data);
            params.synUserName = this.$store.getters.userInfo.userName;

            // #ifdef H5
            this.wxJsPay();
            // #endif

            // #ifdef APP-PLUS
            this.appStart();
            // #endif
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>
    .content {
        padding: 0 30rpx;
    }

    .item {
        font-size: 34rpx;
        line-height: 60rpx;
        padding: 40rpx;
        font-weight: bold;
    }
    .item + .item {
        border-top: $border-line;
    }
    .icon-item {
        font-size: 38rpx;
        margin-right: 30rpx;
    }
</style>
