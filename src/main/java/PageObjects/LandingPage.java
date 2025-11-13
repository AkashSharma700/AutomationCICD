package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginBtn;

	@FindBy(id = "toast-container")
	WebElement toastMessage;

	public ProductCatalogue login(String email, String pwd) {
		username.sendKeys(email);
		password.sendKeys(pwd);
		loginBtn.click();

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public String getToastMessage() {
		waitForVisibility(toastMessage, 5);
		return toastMessage.getText();
	}

}
