package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import project_constants.AutomationTypeEnum;
import project_constants.Constants;

public class DriverFactory {
	protected static WebDriver driver;

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	protected static WebDriver createDriver(String browser) {
		if (driver == null) {
			try {
				ChromeOptions optionsChrome = new ChromeOptions();
				FirefoxOptions optionsFirefox = new FirefoxOptions();
				switch (AutomationTypeEnum.valueOf(browser)) {
				case CHROME:
					WebDriverManager.chromedriver().setup();
					optionsChrome.addArguments("--window-size=1024,768");
					optionsChrome.addArguments("--start-maximized");
					//optionsChrome.addArguments("--headless");
					driver = new ChromeDriver(optionsChrome);
					driver.get(Constants.URL);
					break;
				case FIREFOX:
					WebDriverManager.firefoxdriver().setup();
					optionsFirefox.addArguments("--headless");
					driver = new FirefoxDriver(optionsFirefox);
					driver.get(Constants.URL);
					break;
				case OPERA:
					WebDriverManager.operadriver().setup();
					driver = new OperaDriver();
					driver.get(Constants.URL);
					break;
				case EDGE:
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					driver.get(Constants.URL);
					break;
				case SAFARI:
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driver;
	}
}
