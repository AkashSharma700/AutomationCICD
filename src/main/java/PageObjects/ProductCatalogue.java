package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	@FindBy(id = "toast-container")
	WebElement toastMessage;

	@FindBy(css = ".ngx-spinner-overlay")
	WebElement spinnerOverlay;

	By crntItemNameLocator = By.cssSelector(".mb-3 b");
	By addToCartBtnLocator = By.xpath(".//button[@class='btn w-10 rounded']");

	public void addItemsToCart(List<String> itemsToAdd) throws InterruptedException {

		int count = 0;
		for (int i = 0; i < productList.size(); i++) {

			WebElement we = productList.get(i);
			String cuntItemName = we.findElement(crntItemNameLocator).getText();
			WebElement addToCartBtn = we.findElement(addToCartBtnLocator);

			if (itemsToAdd.contains(cuntItemName)) {

				waitForClickability(addToCartBtn, 5);
				addToCartBtn.click();
				count++;

				waitForInvisibility(spinnerOverlay, 5);
				waitForInvisibility(toastMessage, 5);

				if (count == itemsToAdd.size()) {
					break;
				}
			}
		}
	}

}
