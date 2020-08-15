
// 每个接口对应一个缓存对象
// 每个缓存对象最多缓存(不同数据) cacheMax 条数据
// 数据缓存时间 validityTime 分钟
export default class CacheAjax {
    private promiseAjax: IMyPromiseAjax;
    private cacheMax: number;
    private cacheData: IMyCacheData;

    constructor (promiseAjax: IMyPromiseAjax, cacheMax?: number) {
        this.promiseAjax = promiseAjax;
        this.cacheMax = cacheMax || 10;
        this.cacheData = {count: 0};
        // this.clearAllCache();
    }

    // 清空缓存
    clearAllCache () {
        this.cacheData = {count: 0};
    }

    // 创建key值
    createKey (data: IOBJ | string) {
        return typeof data === 'object' ? JSON.stringify(data) : data;
    }

    // 进行存储
    wCache (index: string, data: IOBJ) {
        if (this.cacheData.count > this.cacheMax) {
            this.delHead();
        } else {
            this.cacheData.count++;
        }
        this.cacheData[index] = data;
    }

    // 删除前面的缓存数据
    delHead () {
        const arr = Object.entries(this.cacheData);
        arr.splice(0, 1);
        const obj: IMyCacheData = {count: arr.length};
        arr.forEach(item => {
            obj[item[0]] = item[1];
        });
        this.cacheData = obj;
    }

    // 判断数据是否过期
    isOver (data: IOBJ, validityTime: number) {
        const s = (Date.now() - data.cacheTime) / 1000;
        if (s > 60 * validityTime) {
            return false;
        } else {
            return true;
        }
    }

    // 执行
    run (params: IOBJ, options: IMyOptions) {
        return new Promise((rel, rej) => {
            // debugger;
            const key = this.createKey(params);
            const item = this.cacheData[key] as IOBJ;
            const {isRCache = false, validityTime = 5} = options;

            delete options.isRCache;
            delete options.validityTime;

            if (isRCache && item && this.isOver(item, validityTime)) {
                rel(utils.jsCopyObj(item));
            } else {
                this.promiseAjax(params, options).then((res) => {
                    const data = utils.jsCopyObj(res);
                    data.cacheTime = Date.now();
                    this.wCache(key, data);
                    rel(res);
                }).catch((err) => {
                    rej(err);
                });
            }
        });
    }
}

