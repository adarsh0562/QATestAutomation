package com.adarsh.stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.adarsh.pageobjects.CartPage;
import com.adarsh.pageobjects.CheckoutPage;
import com.adarsh.pageobjects.ConfirmationPage;
import com.adarsh.pageobjects.LandingPage;
import com.adarsh.pageobjects.ProductsCatalog;
import com.adarsh.tests.components.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseOrderSteps extends BaseTest {

	LandingPage landingPage;
	ProductsCatalog productsCatalog;
	CartPage cartPage;
	ConfirmationPage confirmationPage;
	
    @Given("I landed on Ecommerce Page")
    public void i_landed_on_ecommerce_page() throws IOException {
    	landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) {
    	productsCatalog = landingPage.userLogin(username, password);
    	
    }

    @When("^I added Product (.+) to cart$")
    public void i_added_product_to_cart(String productName) throws InterruptedException {
    	productsCatalog.addToCart(productName);
    	cartPage = productsCatalog.landingCartPage();
    	Assert.assertTrue(cartPage.verifyItems(productName));
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) {
    	CheckoutPage checkoutPage = cartPage.landingCheckoutPage();
    	confirmationPage = checkoutPage.submitOrder(prop.getProperty("countryName"), prop.getProperty("cvvNumber"), prop.getProperty("nameOnCard"));
    }

    @Then("{string} message is displayed on confirmation page")
    public void thank_you_for_the_order_message_is_displayed_on_confirmation_page(String string) {
    	Assert.assertEquals(string, confirmationPage.thankYouPage());
    	
    	driver.quit();
    }
    
    @Then("{string} error message is displayed on login Page")
    public void error_message_is_displayed_on_login_Page(String string)
    {
    	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
    	driver.quit();
    }
}










