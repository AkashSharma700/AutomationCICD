package PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.AbstractComponents;

public class OrderSection extends AbstractComponents {

	WebDriver driver;

	public OrderSection(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement inputCountry;

	@FindBy(css = ".ta-results")
	WebElement countryOptionsResult;

	@FindBy(css = ".ta-item")
	List<WebElement> countryList;

	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;

	@FindBy(css = ".hero-primary")
	WebElement orderThankingTxt;

	@FindBy(css = ".em-spacer-1 .ng-star-inserted")
	List<WebElement> orderIds;

	public void placeOrder(String country) {
		inputCountry.sendKeys(country);
		waitForVisibility(countryOptionsResult, 5);
		countryList.stream().filter(s -> s.getText().equalsIgnoreCase(country)).limit(1).forEach(s -> s.click());
		placeOrderBtn.click();
	}

	public List<String> getOrderIds() {
		List<String> orderIdsList = new ArrayList<>();
		orderIds.stream().map(s -> stringCleaner(s.getText(), " ", 1)).forEach(s -> orderIdsList.add(s));
		return orderIdsList;
	}

	// string cleaner method;
	private static String stringCleaner(String str, String splitBy, int getIdx) {
		String[] strArr = str.split(splitBy);
		return strArr[getIdx];
	}

}
