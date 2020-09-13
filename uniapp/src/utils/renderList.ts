
const renderList: TRenderList = ({list, data, num = 10, interval = 200, cb}) => {
    // debugger;
    let count = 0;
    let t: number | null = 0;
    // debugger;
    // data = JSON.parse(JSON.stringify(data));

    const fn = () => {
        // debugger;
        count++;
        const newArr = data.slice(num * count - num, num * count);
        // const newArr = data.splice(0, num);
        newArr.forEach((item, index) => {
            cb ? list.push(cb(item, index, count)) : list.push(item);
            // list.push(item);
        });
        // console.log(123);
    };

    const timeFn = () => {
        // debugger;
        // if (data.length) fn();
        // if (data.length) setTimeout(timeFn, interval);
        // console.log(list.length);
        if (data.length > list.length) fn();
        if (data.length > list.length) t = setTimeout(timeFn, interval);
    };

    timeFn();

    return () => {
        clearTimeout(t as number);
        t = null;
    };
};

export default renderList;
