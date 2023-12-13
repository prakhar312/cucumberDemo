package com.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractMethod {

    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement CountryName;

    @FindBy(css = "[class*='ta-item'] span")
    List<WebElement> dropdownElements;

    By dropdownList = By.cssSelector("[class*='ta-item']");
    By placeOrderButton = By.xpath("//a[text()='Place Order ']");

    @FindBy(xpath = "//a[text()='Place Order ']")
    WebElement PlaceOrderButton;

    public void SelectCountry(String word) {
        CountryName.sendKeys(word);
        webDriverWaitForVisibilty(dropdownList);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();",
                PlaceOrderButton);
        webDriverWaitForVisibilty(placeOrderButton);
        for (WebElement option : dropdownElements) {
            if (option.getText().equalsIgnoreCase("India")) {
                // System.out.println(option.getText());
                option.click();
                break;
            }
        }
    }

    public void PlaceOrder() {
        waitForElementClickable(placeOrderButton);
        PlaceOrderButton.click();
    }
}
