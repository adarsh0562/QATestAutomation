
package com.adarsh.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adarsh.abstractcommons.AbstractCommons;

public class OrdersHistoryPage extends AbstractCommons{

	WebDriver driver;
	@FindBy(css="tr td:nth-child(3)") List<WebElement> products;
	@FindBy(css="tr td:nth-child(3)") WebElement ordersList;
	
	public OrdersHistoryPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public List<WebElement> getOrderedProductList()
	{
		waitForThingsToAppears(ordersList);
		return products;
	}
	
	
	public Boolean verifyOrderedProductName(String productName)
	{
		return getOrderedProductList().stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		
		
	}
	
}

