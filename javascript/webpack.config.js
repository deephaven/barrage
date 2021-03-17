const path = require('path');

const LIB_BASE_CONFIG = {
    entry: "./src/index.ts",
    mode: "production",
    module: {
        rules: [{
            test: /\.ts?$/,
            use: 'ts-loader',
            exclude: /node_modules/
        }]
    },
    resolve: {
        extensions: ['.ts', '.js']
    },
};
const DIST_DIR = path.resolve(__dirname, 'dist');

module.exports = [{
    name: 'lib-commonjs',
    ...LIB_BASE_CONFIG,
    output: {
        filename: `barrage.js`,
        path: DIST_DIR,
        libraryTarget: 'umd',
        globalObject: 'this',
    }
},
    // {
    //     name: 'lib-umd',
    //     ...LIB_BASE_CONFIG,
    //     output: {
    //         filename: `barrage.umd.js`,
    //         path: DIST_DIR,
    //         libraryTarget: 'umd',
    //         globalObject: 'this',
    //     }
    // },
];