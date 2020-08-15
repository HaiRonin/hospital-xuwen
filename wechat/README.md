# zxwy_h5

## 多页面项目，支持单独模块打包，全部模块打包
```
"dev:mtPay": "cross-env PACK=mtPay yarn run initDev",
"test:mtPay": "cross-env PACK=mtPay yarn run initTest && cross-env PACK=mtPay node util/filesCopy.js && cross-env PACK=mtPay node util/writeZip.js",
"build:mtPay": "cross-env PACK=mtPay yarn run initBuild && cross-env PACK=mtPay node util/filesCopy.js && cross-env PACK=mtPay node util/writeZip.js",

"dev": "vue-cli-service serve --mode development",
"test": "vue-cli-service build --mode test && node util/filesCopy.js && node util/writeZip.js",
"build": "vue-cli-service build --mode production && node util/filesCopy.js && node util/writeZip.js",
```

## 美团支付页命令
```
yarn dev:mtPay
yarn test:mtPay
yarn build:mtPay
```

## Project setup
```
yarn install
```

### Compiles and hot-reloads for development
```
yarn dev
```

### Compiles and minifies for production
```
yarn build
```

### Lints and fixes files
```
yarn lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
