package com.example.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.example.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;
    public LoginPage obj;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(
                "C:\\Users\\Prakhar\\Desktop\\Selenium\\seleniumtest\\src\\Resources\\GlobalData.properties");
        prop.load(file);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
                : prop.getProperty("browser");

        if (browserName.contains("Chrome")) {
            ChromeOptions options=new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1440,900));

        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException {

        String jsonContent = FileUtils
                .readFileToString(new File(Filepath),
                        StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {

                });
        return data;
    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\src\\Reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "\\src\\Reports\\" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage launchApplication() throws IOException {
        driver = initializeDriver();
        LoginPage obj = new LoginPage(driver);
        obj.goTo();
        return obj;
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.close();
    }

}
