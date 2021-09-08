const path = require('path');
const { merge } = require('webpack-merge');
const modeConfig = (mode) => require(`./build-utils/webpack.${mode}`)(mode);

module.exports = (env, { mode } = { mode: 'production' }) => {
  return merge(
    {
      mode, // mode is determined from command line args via scripts in package.json
      entry: path.join(__dirname, './src/index.tsx'),
      resolve: {
        extensions: ['.tsx', '.ts', '.js']
      }
    },
    modeConfig(mode) // mode config refers to the build file - either dev or prod configuration
  );
};
