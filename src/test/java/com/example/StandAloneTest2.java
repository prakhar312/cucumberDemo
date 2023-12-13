package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.AbstractMethod;
import com.example.Cart;
import com.example.ConfirmationPage;
import com.example.LoginPage;
import com.example.OrderHistoryPage;
import com.example.PaymentPage;
import com.example.ProductPage;
import com.example.TestComponents.BaseTest;

public class StandAloneTest2 extends BaseTest {

    List<String> confirmationOrderID;

    @Test(dataProvider = "getData", groups = { "PurchaseOrder" })
    public void submitOrder(HashMap<String, String> input) throws IOException {

        LoginPage obj = new LoginPage(driver);
        obj.Login(input.get("email"), input.get("password"));
        AbstractMethod absobj = new AbstractMethod(driver);

        String[] LP = { input.get("product") };
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

    // public File getScreenShot(String testCaseName) throws IOException {

    // TakesScreenshot ts = (TakesScreenshot) driver;
    // File source = ts.getScreenshotAs(OutputType.FILE);
    // File file = new File(System.getProperty("user.dir") + "\\src\\Reports\\" +
    // testCaseName + ".png");
    // FileUtils.copyFile(source, file);
    // return file;
    // }

    @Test(dependsOnMethods = { "submitOrder" }, groups = { "PurchaseOrder" })
    public void orderHistory() {

        LoginPage obj = new LoginPage(driver);
        obj.Login("rahul312@gmail.com", "Alohomora@312");
        OrderHistoryPage obj1 = new OrderHistoryPage(driver);
        obj1.ViewHistory(confirmationOrderID);

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "\\src\\jsonFiles\\OrderItems.json");

        return new Object[][] { { data.get(0) }, { data.get(1) } };
        // return new Object[][] { { "rahul312@gmail.com", "Alohomora@312", "IPHONE 13
        // PRO" },
        // { "rohit312@gmail.com", "Rohit@312", "ADIDAS ORIGINAL" } };

    }
}

// HashMap<String, String> map = new HashMap<String, String>();
// map.put("email", "rahul312@gmail.com");
// map.put("password", "Alohomora@312");
// map.put("product", "IPHONE 13 PRO");

// HashMap<String, String> map1 = new HashMap<String, String>();
// map1.put("email", "rohit312@gmail.com");
// map1.put("password", "Rohit@312");
// map1.put("product", "ADIDAS ORIGINAL");

// System.setProperty("webdriver.chrome.driver",
// "C:\\Users\\prakhar.f.jain\\Downloads\\chromedriver\\chromedriver-win64\\chromedriver.exe");
// ChromeOptions ops = new ChromeOptions();
// ops.setBinary("C:\\Users\\prakhar.f.jain\\Downloads\\chrome\\chrome-win64\\chrome.exe");
// WebDriver driver = new ChromeDriver();
// driver.manage().window().maximize();

// LoginPage obj = new LoginPage(driver);
// obj.goTo("https://rahulshettyacademy.com/client");
// obj.Login("rahul312@gmail.com", "Alohomora@312");