package PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart .infoWrap")
	List<WebElement> addedcartItems;

	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	WebElement checkOutBtn;

	By cartItemName = By.cssSelector(".cart .infoWrap h3");

	public List<String> getCartItems() {
		List<String> addedcartItemNames = addedcartItems.stream().map(s -> s.findElement(cartItemName).getText())
				.collect(Collectors.toList());
		return addedcartItemNames;
	}

	public OrderSection checkOut() {
		waitForVisibility(checkOutBtn, 5);
		checkOutBtn.click();
		OrderSection orderSection = new OrderSection(driver);
		return orderSection;
	}

}
