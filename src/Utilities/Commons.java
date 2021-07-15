package Utilities;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commons {

	private static final int TIMEOUT = 60;
	private static final int POLLING = 500;
	private static WebDriver driver = null;

	public Commons(WebDriver driver) {
		this.driver = driver;
	}

	public void waitUntilVisible(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT, POLLING);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void WaitUntilTextAppersInElement(By by, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIMEOUT, POLLING);
			wait.until(ExpectedConditions.attributeContains(by, "value", text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigateTo(String url) {
		driver.get(url);
	}

	public void clickElement(By by) {
		try {
			waitUntilVisible(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickElementFromList(By by, int id) {
		try {
			waitUntilVisible(by);
			List<WebElement> productList = driver.findElements(by);
			productList.get(id).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setValueToTextBox(By by, String value) {
		try {
			waitUntilVisible(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switchToNextWindow() {
		try {
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchToParentWindow(String parentWindow) {
		try {
			driver.switchTo().window(parentWindow);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTextFromElement(By by) {
		String textFromElement = "No Text";
		try {
			waitUntilVisible(by);
			textFromElement = driver.findElement(by).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textFromElement;
	}
	
	public float findCheaperRate(String FlipkartPrice, String AmazonPrice) {
		Float flipkartPrice=Float.parseFloat(FlipkartPrice.replaceAll("[^0-9.]",""));
		Float amazonPrice =Float.parseFloat(AmazonPrice.replaceAll("[^0-9.]", ""));
		
		if(flipkartPrice> amazonPrice) {
			System.out.println("The Cheaper Price is from Amazon: ");
			return amazonPrice;
			
		}
		else {
			System.out.println("the cheaper price is from Flipkart: ");
			return flipkartPrice;

		}
	}

}
