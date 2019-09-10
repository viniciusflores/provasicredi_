package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import core.BaseTest;
import page.CustomersPage;

public class CustomersTest extends BaseTest {
	CustomersPage page = new CustomersPage();

	@DataProvider(name = "Customer")
	public Object[][] customer() {
		return new Object[][] { { "Teste Sicredi", "Teste", "Vinicius Flores", "51 9999-9999", "Av Assis Brasil, 3970",
				"Torre D", "Porto Alegre", "RS", "91000-000", "Brasil", "Fixter", "200" } };
	}

	@Test(priority = 1, dataProvider = "Customer")
	public void desafioUm(String customerName, String contactLastName, String contactFirstName, String phone,
			String addressLine1, String addressLine2, String city, String state, String postalCode, String country,
			String fromEmployeer, String creditLimit) {
		page.selectVersionV4Theme();
		page.accessAddCustomer();
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://www.grocerycrud.com/demo/bootstrap_theme_v4/add"));
		page.createCustomer(customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city,
				state, postalCode, country, fromEmployeer, creditLimit);
		assertEquals(page.getText(By.xpath("//div[@id='report-success']/p")),
				"Your data has been successfully stored into the database. Edit Customer or Go back to list");
	}

	@Test(priority = 2, dependsOnMethods = { "desafioUm" })
	public void desafioDois() {
		page.clickOnGoBackToList();
		page.searchCustomer("Teste Sicredi");
		page.waitFixed(2000);
		page.removeAllCustomers();
		page.waitFixed(2000);
		assertEquals(page.getText(By.xpath("//span[contains(@data-growl,'message')]/p")),
				"Your data has been successfully deleted from the database.");
	}
}
