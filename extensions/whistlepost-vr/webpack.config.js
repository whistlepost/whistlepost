const path = require('path');
const webpack = require('webpack');

module.exports = {
  entry: './src/main/resources/assets/js/whistlepost-vr.js',
  output: {
    filename: 'resources/main/assets/js/whistlepost-vr.bundle.js',
    path: path.resolve(__dirname, 'build')
  },
  module: {
    rules: [
	  {
        test: /\.(png|svg|jpg|gif)$/,
        use: [
		  {
            loader: 'file-loader',
            options: {
            	'outputPath': 'resources/main/assets/images/',
            	'useRelativePath': true
			}
          }
        ]
      }
    ]
  }
};
