package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractMethod {

    WebDriver driver;

    public AbstractMethod(WebDriver driver) {
        this.driver = driver;
    }

    public void webDriverWaitForVisibilty(By product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(product));
    }

    public void WaitForWebElementVisibilty(WebElement product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOf(product));
    }

    public void webDriverWaitForInvisibilty(By product) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(product));
    }

    public void waitForElementClickable(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
