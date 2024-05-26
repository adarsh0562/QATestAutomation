package com.adarsh.abstractcommons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.adarsh.pageobjects.CartPage;
import com.adarsh.pageobjects.CheckoutPage;
import com.adarsh.pageobjects.OrdersHistoryPage;

public class AbstractCommons {

	WebDriver driver;
	@FindBy(css="[routerlink='/dashboard']") WebElement homePage;
	@FindBy(css="[routerlink*='cart']") WebElement cartPage;
	@FindBy(css="[routerlink*='myorders']") WebElement ordersPage;
	@FindBy(xpath="//*[contains(text(), 'Sign Out')]") WebElement signOut;
	@FindBy(xpath="//*[contains(text(), 'Checkout')]") WebElement checkoutBtn;
	
	public AbstractCommons(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForThingsToAppear(By visibileElement) 
	{
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(visibileElement));
	}
	
	public void waitForThingsToAppears(WebElement visibileElement) 
	{
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(visibileElement));
	}
	
	public void waitForThingsToDisappear(WebElement invisibleElement) throws InterruptedException 
	{
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(invisibleElement));
	}
	
	
	public void landingHomePage()
	{
		homePage.click();
	}
	
	
	
	public CartPage landingCartPage()
	{
		cartPage.click();
		return new CartPage(driver);
	}
	
	public OrdersHistoryPage landingOrdersPage()
	{
		ordersPage.click();
		return new OrdersHistoryPage(driver);
	}
	
	public void signOut()
	{
		signOut.click();
	}
	
	public CheckoutPage landingCheckoutPage()
	{
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}
	
}
