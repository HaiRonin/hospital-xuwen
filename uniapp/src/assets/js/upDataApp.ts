
import {getLastAppVersion} from '@/apis';

// 版本比对 versionComparison('2.2.2', '1.2.3')
const versionComparison = (newVal: string, oldVal: string) => {
    if (newVal === oldVal) return false;

    const nArr = newVal.split('.').map(s => +s);
    const oArr = oldVal.split('.').map(s => +s);
    const len = nArr.length;

    let i = 0;
    let isUpdata = false;

    if (oArr.length !== len) {
        throw new Error(`版本对比出错了 ${newVal},${oldVal}}`);
    }

    while (i < len) {
        if (nArr[i] < oArr[i]) break;

        isUpdata = nArr[i] > oArr[i];

        if (isUpdata) break;
        i++;
    }

    return isUpdata;
};

export default async function () {
    const name = '农垦医院';
    const version = plus.runtime.version as string;
    const appid = plus.runtime.appid;
    // console.log(appid, name, version);
    // console.log(plus.runtime);

    try {
        const res = await getLastAppVersion({}, {closeErrorTips: true});
        const originVer = res.data.version;
        // console.log(res);
        console.log(originVer, version);

        if (!versionComparison(originVer, version)) return;

        await utils.confirm({content: 'app更新', showCancel: true});

        let appurl = '';

        if (plus.os.name === 'Android') {
            // appurl = "market://details?id=io.dcloud.hellouniapp"; //这个是通用应用市场，如果想指定某个应用商店，需要单独查这个应用商店的包名或scheme及参数

            // type = 1患者, 2是医生
            appurl = `${globalConfig.domain.webUrl}/his/download/app/1`;
        } else {
            appurl = `itms-apps://itunes.apple.com/cn/app/${name}/${appid}`;
        }

        console.log(appurl);
        plus.runtime.openURL(appurl);
    } catch (error) {
        console.error(error);
    }
}

