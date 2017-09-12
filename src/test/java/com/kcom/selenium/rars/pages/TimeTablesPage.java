package com.kcom.selenium.rars.pages;

import kcom.library.GenericLibrary;
import kcom.library.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimeTablesPage extends Page {
    GenericLibrary genericLibrary;

    public TimeTablesPage(WebDriver driver) {
        super(driver);
        genericLibrary = new GenericLibrary();
    }

    public TimeTablesPage clickTimeTableButton(WebDriver driver, String xpTimeTable) {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpTimeTable));
        WebElement timeTableButton = fluentWait(driver, By.xpath(xpTimeTable));
        genericLibrary.clickAction(driver, timeTableButton);
        return this;
    }

    public TimeTablesPage clickFirstButton(WebDriver driver, String xpFirstButton) {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpFirstButton));
        WebElement firstButton = fluentWait(driver, By.xpath(xpFirstButton));
        genericLibrary.clickAction(driver, firstButton);
        return this;

    }

    public TimeTablesPage clickPreviousButton(WebDriver driver, String xpPreviousButton) {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpPreviousButton));
        WebElement previousButton = fluentWait(driver, By.xpath(xpPreviousButton));
        genericLibrary.clickAction(driver, previousButton);
        return this;

    }

    public TimeTablesPage clickLastButton(WebDriver driver, String xpLastButton) {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpLastButton));
        WebElement lastButon = fluentWait(driver, By.xpath(xpLastButton));
        genericLibrary.clickAction(driver, lastButon);
        return this;
    }

}
