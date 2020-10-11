const path = require('path');
const webpack = require('webpack');

module.exports = {
    devServer: {
        // open: true,
        host: '0.0.0.0',
        // hot: true,
        // disableHostCheck: true,
        port: 8091,
        // proxy: 'http://120.76.246.196',
        // proxy: 'http://nktest.free.idcfengye.com',
        proxy: {
            '/test': {
                target: 'http://120.76.246.196',
                pathRewrite: {
                    '^/test': ''
                }
            },
            '/yh': {
                target: 'http://nktest.free.idcfengye.com',
                pathRewrite: {
                    '^/yh': ''
                }
            },
            '/old': {
                target: 'http://app.gdsnkzxyy.cn:8088/hospitaldoctor',
                pathRewrite: {
                    '^/old': ''
                }
            }
        }
    },
    // 重写原配置
    configureWebpack: (config) => {
        const plugins = [];

        plugins.push(
            new webpack.ProvidePlugin({
                globalConfig: path.resolve(__dirname, './', 'src/globalConfig.ts'),
                utils: path.resolve(__dirname, './', 'src/utils/index.ts'),
                ajax: path.resolve(__dirname, './', 'src/apis/ajax/index.ts'),
                wx: [path.resolve(__dirname, './', 'src/assets/js/wxUtils.ts'), 'default'],
            }),
        );


        // webpack处理器，从前往后执行
        config.plugins = [...plugins, ...config.plugins];
    },
};

