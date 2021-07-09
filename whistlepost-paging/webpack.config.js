const path = require('path');
const webpack = require('webpack');

module.exports = {
  entry: './src/main/resources/SLING-INF/content/js/paging.js',
  output: {
    filename: 'resources/main/SLING-INF/content/js/paging.bundle.js',
    path: path.resolve(__dirname, 'build'),
    library: 'paging'
  }
};
