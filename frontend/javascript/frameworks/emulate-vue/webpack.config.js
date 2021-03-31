const path = require("path");
const HTMLWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  mode: "development",
  entry: {
    index: "./index.js",
  },
  devtool: "eval-source-map",
  devServer: {
    hot: true,
    contentBase: "./dist",
  },
  plugins: [
    new HTMLWebpackPlugin({
      template: "main.html",
      title: "Emulate Vue",
    }),
  ],
  output: {
    filename: "[name].bundle.js",
    path: path.resolve(__dirname, "dist"),
    clean: true,
  },
};
