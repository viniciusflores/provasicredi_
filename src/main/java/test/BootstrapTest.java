package test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Page.BootstrapPage;
import core.BaseTest;

public class BootstrapTest extends BaseTest {
	BootstrapPage page = new BootstrapPage();
	@Test
	public void desafioUm()  {
		page.changeSelectByValue(By.id("switch-version-select"), "bootstrap_theme_v4");
		page.click(By.cssSelector("[class='el el-plus']"));
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.grocerycrud.com/demo/bootstrap_theme_v4/add"));
	}
}
