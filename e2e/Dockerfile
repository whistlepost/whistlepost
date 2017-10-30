FROM node

RUN npm install --save-dev protractor cucumber protractor-cucumber-framework chai chai-as-promised

WORKDIR /e2e

ADD protractor.conf.js ./
#ADD step_definitions step_definitions
ADD pageObjects pageObjects
ADD tests tests

CMD ["/node_modules/.bin/protractor", "protractor.conf.js"]
