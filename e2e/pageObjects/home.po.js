var HomepagePageObject = function() {
  this.selectors = {
    'START_BUTTON': '.e2e-start-button',
    'RESULT'      : '.e2e-result'
  };

  this.startButton = browser.$(this.selectors.START_BUTTON);
  this.result = browser.$(this.selectors.RESULT);
};

HomepagePageObject.prototype.clickStartButton = function() {
  return this.startButton.click();
};

HomepagePageObject.prototype.isResultPresent = function() {
  return this.result.isPresent();
};

module.exports = HomepagePageObject;