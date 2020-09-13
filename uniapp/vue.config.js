const path = require('path');
const webpack = require('webpack');

module.exports = {
    devServer: {
        // open: true,
        host: '0.0.0.0',
        // hot: true,
        // disableHostCheck: true,
        port: 8090,
        proxy: 'http://120.76.246.196'
    },
    // 重写原配置
    configureWebpack: (config) => {
        const plugins = [];

        plugins.push(
            new webpack.ProvidePlugin({
                globalConfig: path.resolve(__dirname, './', 'src/assets/js/globalConfig.ts'),
                utils: path.resolve(__dirname, './', 'src/utils/index.ts'),
                ajax: path.resolve(__dirname, './', 'src/apis/ajax/index.ts'),
                wx: [path.resolve(__dirname, './', 'src/assets/js/wxUtils.ts'), 'default'],
            }),
        );


        // webpack处理器，从前往后执行
        config.plugins = [...plugins, ...config.plugins];
    },
};

