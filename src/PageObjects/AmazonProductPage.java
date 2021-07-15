package PageObjects;

import org.openqa.selenium.By;

public class AmazonProductPage {

	public static By productPriceText = By.xpath("//span[contains(@id,'priceblock')]");
	public static By addToCartButton = By.id("add-to-cart-button");

}
