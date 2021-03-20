const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  mode: "development",
  entry: {
    index: "./index.js",
  },
  // high-quality source maps playboy
  devtool: "eval-source-map",
  devServer: {
    hot: true,
    contentBase: "./dist",
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: "main.html",
      title: "Custom Query Selector",
    }),
  ],
  output: {
    filename: "[name].bundle.js",
    path: path.resolve(__dirname, "dist"),
    clean: true,
  },
};
