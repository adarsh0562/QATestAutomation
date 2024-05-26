package com.adarsh.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adarsh.abstractcommons.AbstractCommons;

public class CheckoutPage extends AbstractCommons {

	WebDriver driver;
	
	@FindBy(xpath="//div[contains(text(),'CVV Code')]/following-sibling::input") WebElement cvvInputField;
	@FindBy(xpath="//div[contains(text(),'Name on Card ')]/following-sibling::input") WebElement nameInputField;
	@FindBy(css="[placeholder='Select Country']") WebElement selectCountryInputField;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]") WebElement selectCountry;
	@FindBy(css=".actions a") WebElement submitBtn;
	By results = By.cssSelector(".ta-results");
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCountry(String countryName)
	{
		selectCountryInputField.sendKeys(countryName);
		waitForThingsToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder(String countryName, String cvvNumber, String nameOnCard)
	{
		selectCountry(countryName);
		cvvInputField.sendKeys(cvvNumber);
		nameInputField.sendKeys(nameOnCard);
		submitBtn.click();
		
		return new ConfirmationPage(driver);
	}
	
	
}
