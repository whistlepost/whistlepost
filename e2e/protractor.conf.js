exports.config = {

  seleniumAddress: 'http://selenium:4444/wd/hub', // This is targetting your local running instance of the selenium webdriver

  specs: [
    'tests/*.feature'
  ],

  baseURL: 'http://localhost:8080/',

  capabilities: {
    browserName: 'chrome' // You can use any browser you want. On a CI environment you're going to want to use PhantomJS
  },

  framework: 'custom', //We need this line to use the cucumber framework

  frameworkPath: require.resolve('protractor-cucumber-framework'), // Here it is

  cucumberOpts: {
    format:  'pretty',
    require: 'tests/step_definitions/*.js', // This is where we'll be writing our actual tests
  },

  params: {
    env: {
//      hostname: 'http://0.0.0.0:8000' // Whatever the address of your app is
      hostname: 'http://feedhabit.com' // Whatever the address of your app is
    }
  }

};
