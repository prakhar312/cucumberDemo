package com.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractMethod {

    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'mb-3')]")
    List<WebElement> ProductList;

    @FindBy(css = "[routerlink='/dashboard/cart']")
    WebElement addToCartButton;

    By product = By.xpath("//div[contains(@class,'mb-3')]");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By spinner = By.cssSelector(".ng-animating");

    public List<WebElement> ListOfProduct() {
        webDriverWaitForVisibilty(product);
        return ProductList;
    }

    public WebElement getProductByName(String word) {
        WebElement prod = ListOfProduct().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(word))
                .findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String word) {
        WebElement prod = getProductByName(word);
        prod.findElement(addToCart).click();
        webDriverWaitForInvisibilty(spinner);
    }

    public void addToCartButton() {
        addToCartButton.click();
    }
}
