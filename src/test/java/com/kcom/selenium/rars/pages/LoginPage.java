package com.kcom.selenium.rars.pages;

import kcom.library.GenericLibrary;
import kcom.library.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class LoginPage extends Page {
    GenericLibrary genericLibrary;

    public LoginPage(WebDriver driver) {
        super(driver);
        genericLibrary = new GenericLibrary();
    }

    public LoginPage setUserName(WebDriver driver, String xpUserName) {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpUserName));
        WebElement userid = fluentWait(driver, By.xpath(xpUserName));
        genericLibrary.setValue(driver, userid, "abc");
        return this;

    }

    public LoginPage setPassword(WebDriver driver, String xpPassword) {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpPassword));
        WebElement password = fluentWait(driver, By.xpath(xpPassword));
        genericLibrary.setValue(driver, password, "abc");
        return this;
    }

    public LoginPage clickLogInButton(WebDriver driver, String xpLogin) throws IOException {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpLogin));
        waitUntil(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpLogin)));
        WebElement loginButton = genericLibrary.getWhenVisible(driver, xpLogin);
        genericLibrary.clickAction(driver, loginButton);
        waitInSec(5);
        return this;
    }
}
