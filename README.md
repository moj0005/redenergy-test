# Getting Started

### Prerequisite Environment
* Should have java version 1.8.0_211
* Should have apache maven version 3.6.1

### How to Build
* Checkout redenergy-test locally
* Go to directory redenergy-test
* Execute the following command: mvn clean install. It should be successful as unit tests are included as part of this maven project. Total unit tests addressing various different types of csv files are 28.

### How to test any external CSV file
* Go to class com.redenergy.Driver and add the absolute path of the CSV file for parsing. It contains main method and should do the purpose.

### Code Coverage
Existing unit tests are covering approximately 75.6% of the java source code (used eclipse plugin to evaluate that).
Code coverage can be increased by having unit tests for model classes, either it is useful or not, it is debatable.
However, the main class, SimpleNem12ParserImpl, has 100% code coverage.

### Questions to ask
* Can a meter read block exist (200) without it's subsequent data (300)?
* Can more than one meter readings exists for the same NMI for the same day? Duplicate reading for the same day?
* Can there be spaces, currently no acceptingno spaces?
* unit and quality will be case sensitive?
* Exact decimal format of volume?
