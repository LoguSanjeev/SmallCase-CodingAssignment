package PageObjects;

import org.openqa.selenium.By;

public class FlipkartCartPage {
	public static By increaseQuantityButton= By.xpath("//button[text()='+']");
	public static By totalPriceText= By.xpath("//span[text()='Price details']//following-sibling::div//child::span//following-sibling::span");
	public static By productQuantityText= By.xpath("(//input[@type='text'])[3]");

	
}
