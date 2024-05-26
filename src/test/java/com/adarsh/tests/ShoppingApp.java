package com.adarsh.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
public class ShoppingApp {

	public static void main(String[] args) throws IOException {
		
		WebDriverManager.firefoxdriver().setup();
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		ShoppingApp app = new ShoppingApp();
		ExtentReports extent = app.config();
		ExtentTest test = extent.createTest("Order Placing");
		
		try {
			driver.get("https://rahulshettyacademy.com/client");
            // Perform some actions
            driver.findElement(By.id("userEmail")).sendKeys("rajadarsh711@gmail.com");
    		driver.findElement(By.id("userPassword")).sendKeys("Qwerty12345@");
    		driver.findElement(By.id("login")).click();

    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
    		
    		test.pass("User Login Successful");
    		test.addScreenCaptureFromPath(app.captureScreenshot(driver, "Passed_screenshot1_"+timestamp+".png"));
    		
    		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
    		
    		WebElement prod = 	products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
    		
    		prod.findElement(By.cssSelector(".card-body button:last-child")).click();
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
    		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
    		
    		driver.findElement(By.cssSelector("[routerlink*=\"cart\"]")).click();
    		test.pass("User able to add product into cart");
    		test.addScreenCaptureFromPath(app.captureScreenshot(driver, "Passed_screenshot2_"+timestamp+".png"));
    		
    		List<WebElement> items = driver.findElements(By.cssSelector(".cartWrap"));
    		assertTrue(items.stream().anyMatch(item->item.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase("ADIDAS ORIGINAL")));
    		test.pass("Added product matched");
            test.addScreenCaptureFromPath(app.captureScreenshot(driver, "Passed_screenshot3_"+timestamp+".png"));
            
        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            test.addScreenCaptureFromPath(app.captureScreenshot(driver, "Failed_screenshot1_"+timestamp+".png"));
        } finally {
            // Cleanup
            driver.quit();
            extent.flush();
        }
	}

	
	public ExtentReports config()
	{
		String path = System.getProperty("user.dir")+"//report//index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Adarsh Raj Testing Academy");
		reporter.config().setReportName("Shopping App Testing");
		
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA", "Mr. Adarsh Raj Gupta");
		
		return extent;
		
	}
	
	public String captureScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		// Capture screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/"+screenshotName;
        Files.createDirectories(Paths.get("target"));  // Ensure the target directory exists
        Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        
        return System.getProperty("user.dir")+"\\target\\"+screenshotName;
       
        
	}
}


