package com.example;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.LoginPage;
import com.example.ProductPage;
import com.example.TestComponents.BaseTest;

public class ErrorValidtion extends BaseTest {

    @Test(groups = { "Errorhandling" })
    public void ErrorChecking() throws InterruptedException {

        LoginPage obj = new LoginPage(driver);
        obj.Login("rahul312@gmail.com", "Alohomora312");
        String actualString = "Incorrect email or password.";
        System.out.println(obj.ErrorMessage());
        Assert.assertEquals(actualString, obj.ErrorMessage());
    }

    @Test
    public void ProductErrorValidation() {

        LoginPage obj = new LoginPage(driver);
        obj.Login("rahul312@gmail.com", "Alohomora@312");
        String[] LP = { "IPHONE 13 PRO", "ADIDAS ORIGINAL" };
        List<String> listOfProducts = new ArrayList<String>();
        for (String t : LP) {
            listOfProducts.add(t);
        }
        for (int i = 0; i < LP.length; i++) {
            String word = listOfProducts.get(i);
            ProductPage obj1 = new ProductPage(driver);
            obj1.getProductByName(word);
            obj1.addProductToCart(word);
            // absobj.webDriverWaitForVisibilty(By.id("toast-container"));

        }
    }
}
