package com.adarsh.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/java/com/adarsh/cucumber",
    glue = "com/adarsh/stepdefinitions",
    plugin = {"pretty", "html:report/cucumber-reports.html"},
    monochrome = true,
    tags="@ErrorValidation"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests{
}
