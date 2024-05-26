package com.adarsh.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adarsh.abstractcommons.AbstractCommons;

public class LandingPage extends AbstractCommons {

	WebDriver driver;
	@FindBy(id="userEmail") WebElement userEmail;
	@FindBy(id="userPassword") WebElement userPassword;
	@FindBy(id="login") WebElement loginBtn;
	@FindBy(css="[class*='flyInOut']") WebElement errorMsg;
	
	
	public  LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void getUrl(String baseUrl) 
	{
		driver.get(baseUrl);
	}

	public ProductsCatalog userLogin(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		
		return new ProductsCatalog(driver);
	}
	
	public String getErrorMsg() 
	{
		waitForThingsToAppears(errorMsg);
		return errorMsg.getText();
	}
}
