package com.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends AbstractMethod {

    WebDriver driver;

    public Cart(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> CartElementList;

    By cartElements = By.cssSelector(".cartSection h3");

    public List<WebElement> cartProducts() {
        webDriverWaitForVisibilty(cartElements);
        return CartElementList;
    }

}
