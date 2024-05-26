
package com.adarsh.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.adarsh.tests.components.BaseTest;
import com.adarsh.tests.components.Retry;

public class ErrorValidations extends BaseTest{

	
	@Test(retryAnalyzer= Retry.class)
	public void errorHandling() throws IOException, InterruptedException{
		
		landingPage.userLogin(prop.getProperty("loginUserEmail2"),prop.getProperty("loginUserPassword") );
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}
	
	

	
}
