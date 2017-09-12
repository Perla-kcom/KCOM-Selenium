package com.kcom.selenium.wikipedia.pages;

import kcom.library.GenericLibrary;
import kcom.library.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EnglishPage extends Page {
    public String title = null;

    public EnglishPage(WebDriver driver) {
        super(driver);
    }

    public EnglishPage verifyPageTitle(WebDriver driver, String expectedString, String xpFirstHeadingRef) throws IOException {
        logger.info(expectedString);
        assertEquals(expectedString, getPageTitle(driver, xpFirstHeadingRef));
        return this;
    }

    public String getPageTitle(WebDriver driver, String xpFirstHeadingRef) throws IOException {
        WebElement firstHeadingRef = fluentWait(driver, By.xpath(xpFirstHeadingRef));
        title = firstHeadingRef.getText().trim();
        return title;
    }

    public EnglishPage verifyTheSearchResultsPageExists(WebDriver driver, String searchText) {
        WebElement searchText_ref = GenericLibrary.getAnchorTag(driver, searchText);
        String searchText_Link_Exists = searchText_ref.getText();
        assertEquals(searchText, searchText_Link_Exists);
        return this;
    }
}
