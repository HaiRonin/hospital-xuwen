// #ifdef H5

import jweixin from 'jweixin-module';
import {getOpenId} from '@/apis';
const wxObj: any = jweixin;

// 授权地址
// `http://open.weixin.qq.com/connect/oauth2/authorize?appid=${wx}&redirect_uri=${wx}&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect`

// 微信授权
const wxAuth = {
    async init (options: IOBJ, vueCompon: IOBJ) {
        if (this.isOpenId() || await this.isCode(options, vueCompon)) return;
        const appid = globalConfig.APPID;
        const redirectUri = encodeURIComponent(window.location.href);
        window.location.replace(`http://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${redirectUri}&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect`);
    },
    async isCode (options: IOBJ, vueCompon: IOBJ) {
        const code = options.code;
        if (utils.zEmpty(code)) return false;

        // code 换取openId 存到本地
        const res = await getOpenId({code}, {isLoad: true});
        utils.setStorage('openId', res.data.openId);

        const r = vueCompon.$route;
        vueCompon.$router.replace({path: r.path, query: {}});
        return true;
    },
    isOpenId () {
        const openId = utils.getStorage('openId');
        // console.log(!!openId);
        return !!openId;
    }
};

const obj = {
    cWxAuth: wxAuth,
    cWxConfig: (data: IOBJ) => {
        return new Promise((rel, rej) => {
            wxObj.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: data.appId, // 必填，公众号的唯一标识
                timestamp: data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.nonceStr, // 必填，生成签名的随机串
                signature: data.signature, // 必填，签名
                jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表
            });

            wxObj.ready(function () {
                // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
                rel();
            });

            wxObj.error(function (res: IOBJ) {
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                console.error('weixin', res);
                rej(res);
            });
        });

    },
    cChooseWXPayPromise: (data: IOBJ) => {
        return new Promise((rel, rej) => {
            wxObj.chooseWXPay({
                timestamp: data.timestamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
                nonceStr: data.nonceStr, // 支付签名随机串，不长于 32 位
                package: data.package, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
                signType: data.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
                paySign: data.paySign, // 支付签名
                success: rel,
                fail: rej
            });
        });
    },
    cChooseWXPayPromise2: (data: IOBJ) => {
        return new Promise((rel, rej) => {
            const WeixinJSBridge = (window as any).WeixinJSBridge;
            if (!WeixinJSBridge || !WeixinJSBridge.invoke) {
                rej();
                utils.toast('cChooseWXPayPromise2: 支付失败');
                return;
            }
            WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    appId: data.appId, // 公众号名称，由商户传入
                    timeStamp: data.timeStamp, // 时间戳，自1970年以来的秒数
                    nonceStr: data.nonceStr, // 随机串
                    package: data.package,
                    signType: data.signType, // 微信签名方式：
                    paySign: data.paySign // 微信签名
                },
                function (res: IOBJ) {
                    if (res.err_msg === 'get_brand_wcpay_request:ok') {
                        // 使用以上方式判断前端返回,微信团队郑重提示：
                        // res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                        rel(res);
                    } else {
                        rej(res);
                        utils.toast('支付失败2');
                    }
                });
        });
    },
};

// 通过 webpack.ProvidePlugin 暴露出去了
export default Object.assign(wxObj, obj);
// #endif
