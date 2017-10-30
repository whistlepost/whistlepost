var chai               = require('chai'),
    chaiAsPromised     = require('chai-as-promised'),
    expect             = chai.expect;

chai.use(chaiAsPromised);

var HomePO = require('../../pageObjects/home.po.js'),
    home   = new HomePO();

module.exports = function() {
    this.Given(/^I view the homepage$/, function(callback) {
        browser
          .get(browser.params.env.hostname)
          .then(callback);
    });

    this.When(/^I click on the Start button$/, function (callback) {
        home
          .clickStartButton()
          .then(callback);
    });

    this.Then(/^I should see a message telling me that the button was clicked$/, function (callback) {
        expect(home.isResultPresent())
            .to.eventually.equal(true)
            .and.notify(callback);
    });
};

//module.exports = steps;
    