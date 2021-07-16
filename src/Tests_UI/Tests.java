package Tests_UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.*;
import Utilities.Commons;

public class Tests {
	private static WebDriver driver = null;
	private static String productName = "Iphone 11pro 64GB midnight green";
	private static String parentWindow = "";

	@BeforeTest
	public void oneTimeSetUp() {
		System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test(priority = 1, description = "Finding the total price of the Item after increasing the Quantity in Flipkart")
	public void flipkartPrintThePriceOfTheProduct() {

//Launching FlipKart and searching product
		Commons common = new Commons(driver);
		common.navigateTo("https://www.flipkart.com/");
		common.clickElement(FlipkartMainPage.closeLoginPopupButton);
		common.setValueToTextBox(FlipkartMainPage.searchBox, productName);
		common.clickElement(FlipkartMainPage.searchButton);
		common.clickElementFromList(FlipkartMainPage.productList, 0);
		common.switchToNextWindow();

//Printing the Price from product page and Cart(after increasing the quantity)
		System.out.println("Test Case 1 \n");
		System.out.println("***** FlipKart Price*****");
		System.out.println("Price of \"" + productName + "\" at Product Page is: "
				+ common.getTextFromElement(FlipkartProductPage.productPriceText).substring(1));

		common.clickElement(FlipkartProductPage.addToCartButton);
		common.clickElement(FlipkartCartPage.increaseQuantityButton);
		common.WaitUntilTextAppersInElement(FlipkartCartPage.productQuantityText, "2");
		System.out.println("Total Price of \"" + productName + "\" at cart after Increasing quantity is: "
				+ common.getTextFromElement(FlipkartCartPage.totalPriceText).substring(1));

	}

	@Test(priority = 2, description = "Comparing the price of the product between Amazon and Flipkart")
	public void priceComparisionBetweenFlipKartAndAmazon() {

//Launching Flipkart
		Commons common = new Commons(driver);
		common.navigateTo("https://www.flipkart.com/");
		parentWindow = driver.getWindowHandle();
		common.clickElement(FlipkartMainPage.closeLoginPopupButton);
		common.setValueToTextBox(FlipkartMainPage.searchBox, productName);
		common.clickElement(FlipkartMainPage.searchButton);
		common.clickElementFromList(FlipkartMainPage.productList, 0);
		common.switchToNextWindow();

//Printing the price of the product from product page and Cart
		System.out.println("\n\n\nTest Case 2 \n");
		System.out.println("*****Flipkart Price****");
		System.out.println("Price of \"" + productName + "\" at Product Page is: "
				+ common.getTextFromElement(FlipkartProductPage.productPriceText).substring(1));

		common.clickElement(FlipkartProductPage.addToCartButton);
		String FlipkartPrice = common.getTextFromElement(FlipkartCartPage.totalPriceText).substring(1);
		System.out.println("Total price of the Product \"" + productName + "\" at cart is: " + FlipkartPrice+"\n");

		driver.close();
		common.switchToParentWindow(parentWindow);


//Launching Amazon
		common.navigateTo("https://www.amazon.in/");
		common.setValueToTextBox(AmazonMainPage.searchBox, productName);
		common.clickElement(AmazonMainPage.searchButton);
		common.clickElementFromList(AmazonMainPage.productList, 0);

//Printing the price from product age and cart
		common.switchToNextWindow();
		System.out.println("*****Amazon Price****");
		System.out.println("Amazon Price of \"" + productName + "\" is: "
				+ common.getTextFromElement(AmazonProductPage.productPriceText).substring(1));

		common.clickElement(AmazonProductPage.addToCartButton);
		String AmazonPrice = common.getTextFromElement(AmazonCartPage.totalPriceText).substring(1);
		System.out.println("Amazon Price of \"" + productName + "\" after adding to cart is: " + AmazonPrice+"\n");

//Comparing Amazon and Flipkart and Printing the cheaper price
		System.out.println("=====Cheaper Price====");
		System.out.println("====="+common.findCheaperRate(FlipkartPrice, AmazonPrice)+"=====\n");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
