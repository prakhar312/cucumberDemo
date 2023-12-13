package com.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
    public static void main(String args[]) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\prakhar.f.jain\\Downloads\\chromedriver\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions ops = new ChromeOptions();
        ops.setBinary("C:\\Users\\prakhar.f.jain\\Downloads\\chrome\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver(ops);

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("rahul312@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Alohomora@312");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'mb-3')]")));
        List<WebElement> products = driver.findElements(By.xpath("//*[contains(@class,'mb-3')]"));
        String[] LP = { "IPHONE 13 PRO", "ADIDAS ORIGINAL" };
        List<String> listOfProducts = new ArrayList<String>();
        for (String t : LP) {
            listOfProducts.add(t);
        }
        for (int i = 0; i < LP.length; i++) {
            String word = listOfProducts.get(i);
            WebElement prod = products.stream()
                    .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(word))
                    .findFirst().orElse(null);
            prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        }

        driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        List<String> cprod = new ArrayList<>();
        for (WebElement word : cartProducts) {
            cprod.add(word.getText());
        }
        // System.out.println(cprod);
        // System.out.println(listOfProducts);
        Assert.assertTrue(cprod.containsAll(listOfProducts));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", driver.findElement(By.cssSelector(".totalRow button")));

        String word = "Ind";
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys(word);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-item']")));
        List<WebElement> dropdown = driver.findElements(By.cssSelector("[class*='ta-item'] span"));
        jse.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//a[text()='Place Order ']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Place Order ']")));
        for (WebElement option : dropdown) {
            if (option.getText().equalsIgnoreCase("India")) {
                // System.out.println(option.getText());
                option.click();
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order ']")));
        driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='ng-star-inserted']")));
        String confirmedText = "THANKYOU FOR THE ORDER.";
        String confirmationText = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(confirmedText, confirmationText);

        List<WebElement> orderId = driver
                .findElements(By.xpath("//td[@class='em-spacer-1'] //label[@class='ng-star-inserted']"));
        for (WebElement webElement : orderId) {
            System.out.println(webElement.getText());
        }
    }
}
