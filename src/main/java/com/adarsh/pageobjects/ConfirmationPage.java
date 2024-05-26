package com.adarsh.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.adarsh.abstractcommons.AbstractCommons;

public class ConfirmationPage extends AbstractCommons{

	WebDriver driver;
	@FindBy(css=".hero-primary") WebElement thankyouMsg;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String thankYouPage()
	{
		System.out.print(thankyouMsg.getText());
		
		return thankyouMsg.getText();
	}
	
}

