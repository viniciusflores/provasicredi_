# ProvaSicredi_

Java Web Test Project, contains use of Selenium and TestNG to validate the stipulated scenarios.


### Description

To run browser tests, Selenium will need to be able to conduct a browser
installed on your system. By default, this repository is set to start Chrome.

To change the browser Selenium will launch, edit the file
`src/main/java/suite/testNG.xml` changing the value of the name parameter 'browser'.
Available options can be found at `src/main/java/project_contants/AutomationTypeEnum`.


### Project

Here's a look at what the project contains:

```
.
└── src
   └── main
       └── java
           ├── core
           │   └── DriverFactory.java
           │   └── BasePage.java
           │   └── BaseTest.java
           │   └── ScreenshotUtility.java
           ├── page
           │   └── CustomersPage.java
           ├── project_constants
           │   ├── AutomationTypeEnum.java
           │   ├── Constants.java
           │   ├── InvestmentProfileEnum.java
           │   └── InvestmentProfileEnum.java
           │── suite
           │   └── TestNG.xml
           │── test
           │   └── CustomersTest.java
           └── util
               ├── ExtentManager.java
               ├── ExtentTestManager.java
               └── TestListener.java
```


We'll break it down below.

#### core.*

**DriverFactory** it is the heart of the project, where the Selenium Driver for the browser defined in the test suite is instantiated.
The other classes extend the base page and test page, which in turn extend the driverfactory, giving access to the entire driver project instantiated here.

**BasePage** is the project DSL, all key methods are placed here and distributed by the project.

**BaseTest** indicates all common actions in the test classes. It is in this class that it receives the browser/device parameters for the selenium driver creation.

**ScreenshotUtility** is the class used for test screenshots.

#### page.*

The **page**` package is the where the logical methods of all pages are indicated.

#### project_constants.*

All static and fixed project data are in this package.

#### suite.TestNG

From the project directory, **maven** performs the tests indicated in this suite.

#### test.*

This package is the key, where all the tests to be performed are described.

#### util.*

The classes in this package are dedicated to test report generation.
