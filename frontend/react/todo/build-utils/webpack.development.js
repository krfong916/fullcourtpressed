const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const Dotenv = require('dotenv-webpack');

module.exports = (mode) => ({
  devServer: {
    publicPath: '/',
    hot: true
  },
  // loaders pre-process files, this allows us to bundle static resources beyond javascript
  module: {
    rules: [
      {
        test: /\.(sc|sa|c)ss$/i,
        exclude: /node_modules/,
        // add exports of a module  as style to DOM
        // loads css file w/resolved imports and return CSS code
        // loads and compiles a SASS/SCSS file
        use: ['style-loader', 'css-loader', 'sass-loader']
      },
      {
        test: /\.(ts|js)x?$/,
        exclude: /node_modules/,
        use: {
          // loads es6 code and transpiles to es5 using babel
          loader: 'babel-loader',
          options: {
            presets: [
              '@babel/preset-env',
              '@babel/preset-react',
              '@babel/preset-typescript'
            ]
          }
        }
      }
    ]
  },
  // plugins work at the bundle or chunk level, loaders work at the individual file level
  // they hook into webpack's bundle lifecycle and modify how bundles are created
  // plugins influence the output - during the build process we can minify or write to the file system
  plugins: [
    new Dotenv(),
    new HtmlWebpackPlugin({
      template: './public/index.html',
      title: 'Development'
    }),
    new webpack.HotModuleReplacementPlugin(),
    new webpack.ProgressPlugin() // report progress during compilation
  ]
});
