package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Page.BootstrapPage;
import core.BaseTest;

public class BootstrapTest extends BaseTest {
	BootstrapPage page = new BootstrapPage();

	@Test(priority = 1)
	public void desafioUm() {
		page.changeSelectByValue(By.id("switch-version-select"), "bootstrap_theme_v4");
		page.click(By.cssSelector("[class='el el-plus']"));
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.grocerycrud.com/demo/bootstrap_theme_v4/add"));
		page.write(By.id("field-customerName"), "Teste Sicredi");
		page.write(By.id("field-contactLastName"), "Teste");
		page.write(By.id("field-contactFirstName"), "Vinicius Flores");
		page.write(By.id("field-phone"), "51 9999-9999");
		page.write(By.id("field-addressLine1"), "Av Assis Brasil, 3970");
		page.write(By.id("field-addressLine2"), "Torre D");
		page.write(By.id("field-city"), "Porto Alegre");
		page.write(By.id("field-state"), "RS");
		page.write(By.id("field-postalCode"), "91000-000");
		page.write(By.id("field-country"), "Brasil");
		page.scrollToElement(By.id("form-button-save"));
		page.click(By.id("field_salesRepEmployeeNumber_chosen"));
		page.click(By.cssSelector("[data-option-array-index='8']"));
		page.write(By.id("field-creditLimit"), "200");
		page.click(By.id("form-button-save"));
		assertEquals(page.getText(By.xpath("//div[@id='report-success']/p")),
				"Your data has been successfully stored into the database. Edit Customer or Go back to list");
	}
	
	@Test(priority = 2, dependsOnMethods =  { "desafioUm" })
	public void desafioDois() {
		page.click(By.xpath("//a[@href='/demo/bootstrap_theme_v4/'][contains(.,'Go back to list')]"));
		page.click(By.xpath("//i[contains(@class,'el el-search')]"));
		page.write(By.name("search"), "Teste Sicredi");
		page.clickOnKeyEnter(By.name("search"));
		page.waitFixed(2000);
		page.click(By.className("select-all-none"));
		page.click(By.xpath("(//span[@class='text-danger'][contains(.,'Delete')])[1]"));
		page.click(By.className("delete-multiple-confirmation-button"));
		page.waitFixed(2000);
		assertEquals(page.getText(By.xpath("//span[contains(@data-growl,'message')]/p")), "Your data has been successfully deleted from the database.");
	}
}
