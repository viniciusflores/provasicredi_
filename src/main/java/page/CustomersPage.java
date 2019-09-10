package page;

import org.openqa.selenium.By;

import core.BasePage;

public class CustomersPage extends BasePage {
	public void selectVersionV4Theme() {
		changeSelectByValue(By.id("switch-version-select"), "bootstrap_theme_v4");
	}

	public void accessAddCustomer() {
		click(By.cssSelector("[class='el el-plus']"));
	}

	public void createCustomer(String customerName, String contactLastName, String contactFirstName, String phone,
			String addressLine1, String addressLine2, String city, String state, String postalCode, String country,
			String fromEmployeer, String creditLimit) {
		write(By.id("field-customerName"), customerName);
		write(By.id("field-contactLastName"), contactLastName);
		write(By.id("field-contactFirstName"), contactFirstName);
		write(By.id("field-phone"), phone);
		write(By.id("field-addressLine1"), addressLine1);
		write(By.id("field-addressLine2"), addressLine2);
		write(By.id("field-city"), city);
		write(By.id("field-state"), state);
		write(By.id("field-postalCode"), postalCode);
		write(By.id("field-country"), country);
		scrollToElement(By.id("form-button-save"));
		if (fromEmployeer.equalsIgnoreCase("Fixter")) {
			selectFromEmployeerFixter();
		}
		write(By.id("field-creditLimit"), creditLimit);
		click(By.id("form-button-save"));
	}

	public void selectFromEmployeerFixter() {
		click(By.id("field_salesRepEmployeeNumber_chosen"));
		click(By.cssSelector("[data-option-array-index='8']"));
	}

	public void clickOnGoBackToList() {
		click(By.xpath("//a[@href='/demo/bootstrap_theme_v4/'][contains(.,'Go back to list')]"));
	}

	public void searchCustomer(String param) {
		click(By.xpath("//i[contains(@class,'el el-search')]"));
		write(By.name("search"), param);
		clickOnKeyEnter(By.name("search"));
	}
	
	public void removeAllCustomers() {
		click(By.className("select-all-none"));
		click(By.xpath("(//span[@class='text-danger'][contains(.,'Delete')])[1]"));
		click(By.className("delete-multiple-confirmation-button"));
	}
}
