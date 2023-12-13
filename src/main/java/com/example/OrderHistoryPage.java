package com.example;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage extends AbstractMethod {

    WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tbody //th")
    List<WebElement> placedOrderID;

    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement orderHistory;

    By orderid = By.xpath("//tr[@class='ng-star-inserted']");

    By product = By.xpath("//div[contains(@class,'mb-3')]");;
    ConfirmationPage confirmObj = new ConfirmationPage(driver);

    List<String> orderPageID = new ArrayList<String>();

    public void ViewHistory(List<String> confirmID) {

        webDriverWaitForVisibilty(product);
        orderHistory.click();
        webDriverWaitForVisibilty(orderid);
        for (WebElement element : placedOrderID) {
            orderPageID.add(element.getText());
            System.out.println(orderPageID);
        }
        if (orderPageID.containsAll(confirmID))
            System.out.println("Working");
    }
}
