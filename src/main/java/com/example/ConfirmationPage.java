package com.example;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ConfirmationPage extends AbstractMethod {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By orderID = By.xpath("//label[@class='ng-star-inserted']");

    @FindBy(css = ".hero-primary")
    WebElement confirmationText;

    @FindBy(xpath = "//td[@class='em-spacer-1'] //label[@class='ng-star-inserted']")
    List<WebElement> orderId;
    List<String> confirmationOrderId = new ArrayList<String>();;

    public void orderConfirmation(String confirmedText) {
        webDriverWaitForVisibilty(orderID);
        String text = confirmationText.getText();
        Assert.assertEquals(confirmedText, text);
    }

    public List<String> printOrderID() {

        for (WebElement webElement : orderId) {
            confirmationOrderId.add(webElement.getText().split(" ")[1].trim());
            System.out.println(webElement.getText().split(" ")[1].trim());
        }
        return confirmationOrderId;
    }

}
