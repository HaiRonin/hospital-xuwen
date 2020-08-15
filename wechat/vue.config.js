const path = require('path');
const webpack = require('webpack');
// const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const CompressionWebpackPlugin = require('compression-webpack-plugin');
const FilemanagerWebpackPlugin = require('filemanager-webpack-plugin');
const CopyPlugin = require('copy-webpack-plugin');
// const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const isBuild = process.env.NODE_ENV !== 'development';

function resolve (dir) {
    return path.join(__dirname, dir);
}
// console.log(resolve('./pulbic/favicon.ico'));
module.exports = {
    // baseUrl: './',
    // publicPath: process.env.NODE_ENV === 'development' ? '/' : './',
    publicPath: './',
    // publicPath: './',

    // 打包文件夹
    outputDir: 'dist',

    // 放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录。
    // assetsDir: PACK ? `${PACK}/static` : 'static',
    assetsDir: 'static',

    // 指定生成的 index.html 的输出路径 (相对于 outputDir)。也可以是一个绝对路径。
    // indexPath: 'index.html',

    // 默认情况下，生成的静态资源在它们的文件名中包含了 hash 以便更好的控制缓存。
    filenameHashing: true,

    // 如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建。
    productionSourceMap: !isBuild,

    // 是否使用包含运行时编译器的 Vue 构建版本
    // runtimeCompiler: true,

    // 禁用 eslint-loader
    // lintOnSave: process.env.NODE_ENV === 'development' ? 'error' : false,
    lintOnSave: true,

    // 本地开发
    devServer: {
        open: true,
        host: '0.0.0.0',
        // hot: true,
        // disableHostCheck: true,
        port: 8090,
        openPage: 'index/'
    },
    // 修改 webpack 配置 https://cli.vuejs.org/zh/guide/webpack.html#%E9%93%BE%E5%BC%8F%E6%93%8D%E4%BD%9C-%E9%AB%98%E7%BA%A7
    chainWebpack: (config) => {

        config.resolve.alias
            .set('@pages', resolve('src/pages'))
            .set('@', resolve('src'));

        // 查看包的情况， 资源分布图
        // config.plugin('webpack-bundle-analyzer')
        //     .use(require('webpack-bundle-analyzer').BundleAnalyzerPlugin);

        // config.plugin('html').tap((args) => {
        //     args[0].title = 'h5';
        //     return args;
        // });

        config.plugins.delete('prefetch');
        config.plugins.delete('preload');
    },
    // css相关配置
    // css: {
    //     // 是否提取css生成单独的文件 默认 true
    //     // extract: !isBuild,
    //     extract: true,
    //     sourceMap: !isBuild
    // },
    css: {
        loaderOptions: {
            sass: {
                prependData: `@import "@/assets/style/mixin.scss";`
            }
        }
    },
    // 重写原配置
    configureWebpack: (config) => {
        const plugins = [];
        console.log(config.dev);
        plugins.push(
            new webpack.DefinePlugin({
                'process.env': {
                    BASE_URL: JSON.stringify(process.env.BASE_URL),
                    NODE_ENV: JSON.stringify(process.env.NODE_ENV),
                    PACK: JSON.stringify(process.env.PACK),
                }
            })
        );

        if (isBuild) {
            plugins.push(
                new CompressionWebpackPlugin({
                    filename: '[path].gz[query]',
                    algorithm: 'gzip',
                    test: new RegExp('\\.(js|css)$'),
                    threshold: 10240,
                    minRatio: 0.8
                })
            );

            // plugins.push(
            //     new FilemanagerWebpackPlugin({
            //         onEnd: {
            //             delete: ['./dist/zxwy_inline.zip'],
            //             archive: [
            //                 {
            //                     source: './dist',
            //                     destination: './dist/zxwy_inline.zip'
            //                 }
            //             ]
            //         }
            //     })
            // );

            // 移除console
            // plugins.push(
            //     new UglifyJsPlugin({
            //         uglifyOptions: {
            //             compress: {
            //                 warnings: false,
            //                 drop_console: true,
            //                 drop_debugger: false,
            //                 pure_funcs: ['console.log']
            //             }
            //         },
            //         sourceMap: false,
            //         parallel: true
            //     })
            // );
        }

        plugins.push(
            new webpack.ProvidePlugin({
                utils: path.resolve(__dirname, './', 'src/utils/index.ts'),
                ajax: path.resolve(__dirname, './', 'src/apis/ajax/index.ts'),
            }),
        );

        // plugins.push(
        //     new CopyPlugin([
        //         {from: resolve('./public/favicon.ico'), to: resolve(`./dist/${PACK}/`)},
        //         // {from: resolve('./dist/fun1/index.html'), to: resolve(`./dist/`)},
        //     ])
        // );

        // webpack处理器，从前往后执行
        config.plugins = [...plugins, ...config.plugins];
    },
};
