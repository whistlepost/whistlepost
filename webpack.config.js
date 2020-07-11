const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const GoogleFontsPlugin = require('google-fonts-plugin')

module.exports = {
  mode: 'development',
  entry: './src/assets/js/whistlepost.js',
  output: {
    filename: 'js/whistlepost.bundle.js',
    path: path.resolve(__dirname, 'src/assets')
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [MiniCssExtractPlugin.loader, "css-loader"]
      },
	  {
        test: /\.(png|svg|jpg|gif)$/,
        use: [
		  {
            loader: 'file-loader',
            options: {
            	'outputPath': 'images/',
            	'useRelativePath': true,
            	publicPath: '/assets/wp/images/'
			}
          }
        ]
      },
	  {
        test: /\.(woff|woff2|eot|ttf|otf)$/,
        use: [
		  {
            loader: 'file-loader',
            options: {
            	'outputPath': 'fonts/',
            	'useRelativePath': true,
                publicPath: '/assets/wp/fonts/'
			}
          }
        ]
      },
	  {
        test: require.resolve('jquery'),
        use: [
        	{
			  loader: 'expose-loader',
			  options: 'jQuery'
		    },
		    {
				loader: 'expose-loader',
				options: '$'
			}
		]
      }
    ]
  },
  plugins: [
    new GoogleFontsPlugin({
		fonts: [
			{ family: "Roboto" }
		],
		filename: 'css/fonts.css'
	}),
    new MiniCssExtractPlugin({
        filename: "css/whistlepost.bundle.css"
    })
  ]
};
