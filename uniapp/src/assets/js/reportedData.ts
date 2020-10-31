
import {reportedData} from '@/apis';

// scene department
interface IHealthData {
    scene: string;
    department?: string;
}

export const healthCardRD = function (strOrObj: string | IHealthData) {
    const healthCardId = utils.getStorage('HealthyCardNo') || '';
    if (utils.zEmpty(healthCardId)) return;

    let data: IHealthData = {
        scene: '',
    };

    if (typeof strOrObj === 'string') {
        data.scene = strOrObj;
    } else {
        data = strOrObj;
    }

    reportedData(Object.assign(data, {healthCardId}), {closeErrorTips: true, noCloseBeforeAjax: true, coerceCloseErrorTips: true});
};

