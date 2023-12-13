package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractMethod {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement Email;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String ErrorMessage() throws InterruptedException {
        WaitForWebElementVisibilty(errorMessage);
        return errorMessage.getText();
    }

    public ProductPage Login(String email, String password) {
        Email.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        ProductPage obj = new ProductPage(driver);
        return obj;

    }
}
