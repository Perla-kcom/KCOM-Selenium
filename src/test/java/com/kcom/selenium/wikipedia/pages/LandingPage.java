package com.kcom.selenium.wikipedia.pages;
import kcom.library.GenericLibrary;
import kcom.library.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;

public class LandingPage extends Page {
    GenericLibrary genericLibrary;

    public LandingPage(WebDriver driver) {
        super(driver);
        genericLibrary = new GenericLibrary();
    }

    public LandingPage goToWikiPediaPage(WebDriver driver, String url) {
        driver.get(url);
        return this;
    }

    public LandingPage setInputSearchTextValue(WebDriver driver, String xpSearchTextBox, String searchtext) {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpSearchTextBox));
        WebElement searchBox = fluentWait(driver, By.xpath(xpSearchTextBox));
        genericLibrary.setValue(driver, searchBox, searchtext);
        return this;
    }

    public LandingPage selectEnglishLangauge(WebDriver driver, String xpSerachLanguageRef, String xpLangSysmbolRef) throws IOException {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpSerachLanguageRef));
        WebElement languageSymbolRef = fluentWait(driver, By.xpath(xpSerachLanguageRef));
        genericLibrary.clickAction(driver, languageSymbolRef);
        Select selectElement = new Select(driver.findElement(By.xpath(xpSerachLanguageRef)));
        selectElement.selectByVisibleText("English");
        return this;
    }

    public EnglishPage clickSearchButton(WebDriver driver, String xpSearchButton) throws IOException {
        genericLibrary.isWebElementReferenceExists(driver, By.xpath(xpSearchButton));
        WebElement searchButton = fluentWait(driver, By.xpath(xpSearchButton));
        genericLibrary.clickAction(driver, searchButton);
        return new EnglishPage(driver);
    }
}
