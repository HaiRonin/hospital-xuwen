
import {getLastAppVersion} from '@/apis';

export default async function () {
    const name = '农垦医院';
    const version = plus.runtime.version;
    const appid = plus.runtime.appid;
    console.log(appid, name, version);
    // console.log(plus.runtime);

    try {
        const res = await getLastAppVersion({}, {closeErrorTips: true});
        const originVer = res.data;

        if (version === originVer) return;

        await utils.confirm({content: 'app更新', showCancel: true});

        let appurl = '';

        if (plus.os.name === "Android") {
            // appurl = "market://details?id=io.dcloud.hellouniapp"; //这个是通用应用市场，如果想指定某个应用商店，需要单独查这个应用商店的包名或scheme及参数
            appurl = '';
        } else {
            appurl = `itms-apps://itunes.apple.com/cn/app/${name}/${appid}`;
        }

        plus.runtime.openURL(res.data.url);
    } catch (error) {
        console.error(error);
    }
}

