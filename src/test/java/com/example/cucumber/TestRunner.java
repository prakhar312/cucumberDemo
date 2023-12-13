package com.example.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\com\\example\\cucumber",glue = "src\\test\\java\\com\\example\\Stepdefination.java",monochrome = true,plugin = {"html:target/cucumber.html"})

public class TestRunner extends AbstractTestNGCucumberTests{
    
    
}
