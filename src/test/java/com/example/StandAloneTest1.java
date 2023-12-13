package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.AbstractMethod;
import com.example.Cart;
import com.example.ConfirmationPage;
import com.example.LoginPage;
import com.example.OrderHistoryPage;
import com.example.PaymentPage;
import com.example.ProductPage;
import com.example.TestComponents.BaseTest;

public class StandAloneTest1 extends BaseTest {

    List<String> confirmationOrderID;

    @Test
    public void submitOrder() throws IOException {

        LoginPage obj = new LoginPage(driver);
        obj.Login("rahul312@gmail.com", "Alohomora@312");
        AbstractMethod absobj = new AbstractMethod(driver);

        String[] LP = { "IPHONE 13 PRO", "ADIDAS ORIGINAL" };
        ProductPage obj1 = new ProductPage(driver);
        List<String> listOfProducts = new ArrayList<String>();
        for (String t : LP) {
            listOfProducts.add(t);
        }
        for (int i = 0; i < LP.length; i++) {
            String word = listOfProducts.get(i);

            obj1.getProductByName(word);
            obj1.addProductToCart(word);
            // absobj.webDriverWaitForVisibilty(By.id("toast-container"));

        }

        obj1.addToCartButton();

        Cart cartObj = new Cart(driver);
        List<WebElement> cartProducts = cartObj.cartProducts();
        List<String> cprod = new ArrayList<>();
        for (WebElement word : cartProducts) {
            cprod.add(word.getText());
        }

        Assert.assertTrue(cprod.containsAll(listOfProducts));
        absobj.waitForElementClickable(By.cssSelector(".totalRow button"));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", driver.findElement(By.cssSelector(".totalRow button")));

        String word = "Ind";
        PaymentPage paymentObj = new PaymentPage(driver);

        paymentObj.SelectCountry(word);
        paymentObj.PlaceOrder();

        ConfirmationPage confirmObj = new ConfirmationPage(driver);

        String confirmedText = "THANKYOU FOR THE ORDER.";
        confirmObj.orderConfirmation(confirmedText);
        confirmationOrderID = confirmObj.printOrderID();

    }

    @Test(dependsOnMethods = { "submitOrder" })
    public void orderHistory() {

        LoginPage obj = new LoginPage(driver);
        obj.Login("rahul312@gmail.com", "Alohomora@312");
        OrderHistoryPage obj1 = new OrderHistoryPage(driver);
        obj1.ViewHistory(confirmationOrderID);

    }
}

// System.setProperty("webdriver.chrome.driver",
// "C:\\Users\\prakhar.f.jain\\Downloads\\chromedriver\\chromedriver-win64\\chromedriver.exe");
// ChromeOptions ops = new ChromeOptions();
// ops.setBinary("C:\\Users\\prakhar.f.jain\\Downloads\\chrome\\chrome-win64\\chrome.exe");
// WebDriver driver = new ChromeDriver();
// driver.manage().window().maximize();

// LoginPage obj = new LoginPage(driver);
// obj.goTo("https://rahulshettyacademy.com/client");
// obj.Login("rahul312@gmail.com", "Alohomora@312");