package core;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners({ ScreenshotUtility.class })
public class BaseTest extends DriverFactory {

	@BeforeClass
	@Parameters({ "browser" })
	public static void setupDriver(String browser) {
		setProperty("BROWSER", browser);
		createDriver(getProperty("BROWSER"));
	}

	@AfterClass
	public static void tearDown() {
		DriverFactory.killDriver();
	}

}
