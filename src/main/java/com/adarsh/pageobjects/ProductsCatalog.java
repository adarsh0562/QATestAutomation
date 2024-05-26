package com.adarsh.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adarsh.abstractcommons.AbstractCommons;

public class ProductsCatalog extends AbstractCommons {

	WebDriver driver;
	@FindBy(css=".col-lg-4") List<WebElement> products;
	@FindBy(css=".ng-animating") WebElement toastDisappear;
	By productsBy = By.cssSelector(".col-lg-4");
	By addCartBtn = By.cssSelector(".card-body button:last-child");
	By toastAppear = By.cssSelector("#toast-container");
	
	
	public  ProductsCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getProductList()
	{
		waitForThingsToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductName(String productName)
	{
		return getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	}
	
	public void addToCart(String productName) throws InterruptedException
	{
		
		getProductName(productName).findElement(addCartBtn).click();
		waitForThingsToAppear(toastAppear);
		waitForThingsToDisappear(toastDisappear);
	}
	
	
	
	
	
	
}


