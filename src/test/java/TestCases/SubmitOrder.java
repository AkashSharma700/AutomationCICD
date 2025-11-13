package TestCases;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.LandingPage;
import PageObjects.OrderSection;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import Utility.AbstractComponents;

public class SubmitOrder extends BaseTest {

	String username = "akash007@gmail.com";
	String password = "Test@123";
	static String country = "India";

	@Test()
	private void submitOrder() throws InterruptedException {

		List<String> itemsToAdd = new ArrayList<>();
		itemsToAdd.add("ZARA COAT 3");
		itemsToAdd.add("IPHONE 13 PRO");

		AbstractComponents abstractComponents = new AbstractComponents(driver);

		// landing page;
		LandingPage landingPage = new LandingPage(driver);
		ProductCatalogue productCatalogue = landingPage.login(username, password);
		Thread.sleep(1000);
		productCatalogue.addItemsToCart(itemsToAdd);

		// cart section
		Thread.sleep(1000);
		CartPage cartPage = abstractComponents.gotoCart();
		OrderSection orderSection = cartPage.checkOut();

		// order section;
		orderSection.placeOrder(country);
	}

}
