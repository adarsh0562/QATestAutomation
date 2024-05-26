package com.adarsh.tests.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.adarsh.pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
    public WebDriver driver;
    public Properties prop;
    public LandingPage landingPage;

    public Properties loadProperties() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//adarsh//resources//GlobalData.properties");
        prop.load(fis);
        return prop;
    }

    public WebDriver initializeDriver() throws IOException {
        loadProperties();
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

        if (browserName.contains("chrome")) {
        	
        	System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
           
            if (browserName.equalsIgnoreCase("chromeHeadless")) {
            	options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
            
            
        } else if (browserName.contains("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (browserName.equalsIgnoreCase("firefoxHeadless")) {
            	options.addArguments("--headless");
            }
            System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");
            driver = new FirefoxDriver(options);
        } else if (browserName.contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (browserName.equalsIgnoreCase("edgeHeadless")) {
                options.addArguments("--headless");
            }
            System.setProperty("webdriver.edge.driver", "C:\\driver\\msedgedriver.exe");
            driver = new EdgeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.getUrl(prop.getProperty("baseUrl"));
        return landingPage;
    }

    @AfterMethod
    public void closeApplication() {
        if (driver != null) {
            driver.quit();
        }
    }

    public List<HashMap<String, String>> getJSONDataToMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
    }

    public String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "target/" + screenshotName + ".png";
        Files.createDirectories(Paths.get("target"));
        Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
        return System.getProperty("user.dir")+"//"+screenshotPath;
    }
}
