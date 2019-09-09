package core;

import java.util.List;
import java.util.NoSuchElementException;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import project_constants.Constants;


public class BasePage extends DriverFactory {
	private JavascriptExecutor js = (JavascriptExecutor) driver;

	public Boolean waitElementToBeClickable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			driver.get(Constants.URL);
			throw new SkipException("ELEMENT ISN'T CLICKABLE: " + e.getMessage());
		}
	}

	public Boolean waitElementToBeEnable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			driver.get(Constants.URL);
			throw new SkipException("ELEMENT DON'T BE VISIBLE: " + e.getMessage());
		}
	}

	public void write(By by, String texto) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by).sendKeys(texto);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public String getText(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return driver.findElement(by).getText();
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void clearTextField(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by).clear();
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void click(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by).click();
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void clickByText(String text) {
		click(By.xpath("//*[contains(.,'" + text + "')]"));
	}

	public void clickByTextWithTag(String tagHTML, String text) {
		click(By.xpath("//" + tagHTML + "[contains(.,'" + text + "')]"));
	}

	public boolean existElement(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public boolean existElementByText(String text) {
		return existElement(By.xpath("//*[contains(.,'" + text + "')]"));
	}

	public boolean existElementWithReturn(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean elementIsClickable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public void waitingForTheElementToLoad(By by) {
		while (!driver.findElement(by).isEnabled()) {
			for (int i = 0; i < 60; i++) {
				waitFixed(500);
			}
		}
	}

	public void scrollToElement(By by) {
		WebElement element = driver.findElement(by);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	public void scrollToElementByText(String text) {
		scrollToElement(By.xpath("//*[contains(.,'" + text + "')]"));
	}

	public void scroolToElementWithJS(By by) {
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void commandJS(String command, String argument) {
		js.executeScript(command, argument);
	}

	/**
	 * Utilizado para executar comandos JS
	 * 
	 * @param command
	 */
	public void commandJS(String command) {
		js.executeScript(command, "");
	}

	/**
	 * Busca o atributo de um campo na interface
	 * 
	 * @param by
	 * @param atribute
	 * @return
	 */
	public String getAtributeFromHtml(By by, String atribute) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return driver.findElement(by).getAttribute(atribute);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	/**
	 * Metodo usado para tratar uma string retornando somente os numericos
	 * 
	 * @param idCompleto
	 * @return
	 */
	public int returnOnlyNumbers(String word) {
		return Integer.valueOf(word.replaceAll("[^\\d.]", ""));
	}

	public static void waitFixed(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Metodos que enviam comandos de tecla
	 * 
	 */
	public void clickOnKeyEnter(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void switchTo(int value) {
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[value]);
	}

	public Boolean elementIsDisabled(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		if (driver.findElement(by).getAttribute("disabled") != null) {
			return true;
		}
		return false;
	}
}
