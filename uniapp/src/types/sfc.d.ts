declare module '*.vue' {
    import Vue from 'vue';
    export default Vue;
}

namespace global {
    let utils: any;
    let ajax: any;
    let store: any;
    let globalConfig: any;
}

interface IOBJ {
    [key: string]: any;
}
