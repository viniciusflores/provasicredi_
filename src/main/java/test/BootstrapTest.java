package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import core.BaseTest;
import project_constants.Constants;

public class BootstrapTest extends BaseTest {
	@Test
	public void openSite() throws InterruptedException {
		Thread.sleep(5000);
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(Constants.URL));
	}
}
