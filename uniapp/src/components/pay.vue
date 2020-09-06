<template>
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
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';

    @Component
    export default class Name extends Vue {

        params: IOBJ = {};
        show = false;
        list: IOBJ = {
            wxpay: {key: 'wxpay', icon: 'weixin-fill', iconColor: '#04BE02', text: '微信支付', show: false},
            alipay: {key: 'alipay', icon: 'zhifubao', iconColor: '#1677ff', text: '支付宝支付', show: false},
        };

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
                    success (res) {
                        // console.log('success:' + JSON.stringify(res));
                        rel(res);
                    },
                    fail (err) {
                        // console.log('fail:' + JSON.stringify(err));
                        rej(err);
                    }
                });
            });
        }

        async startPay (data: IOBJ) {
            this.params = utils.jsCopyObj(data);

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

        async selPay (type: 'alipay' | 'wxpay') {
            // 请求接口，得到相应的数据
            try {
                // 调起支付
                await this.requestPayment(type, {});

                this.show = false;
            } catch (error) {
                console.error(error);
                utils.toast('支付失败');
            }
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
