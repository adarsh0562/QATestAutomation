package com.adarsh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adarsh.abstractcommons.AbstractCommons;

public class CartPage extends AbstractCommons {

	WebDriver driver;
	By cartPageItems = By.cssSelector(".cartWrap");
	@FindBy(css=".cartWrap") List<WebElement> cartItems;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getCartItems()
	{
		waitForThingsToAppear(cartPageItems);
		return cartItems;
	}
	
	public Boolean verifyItems(String productName)
	{
		return getCartItems().stream().anyMatch(item->item.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
		
		
	}
}
