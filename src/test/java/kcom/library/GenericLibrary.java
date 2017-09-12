package kcom.library;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class GenericLibrary {

    public static final Log logger = LogFactory.getLog(GenericLibrary.class);

    public boolean isWebElementReferenceExists(WebDriver driver, By webElementRef) {
        try {
            driver.findElement(webElementRef);
            return true;

        } catch (NoSuchElementException ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }

    public static void clickAction(WebDriver driver, WebElement objRef) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Page.WAIT_UNTIL_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(objRef));
            objRef.click();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public static void setValue(WebDriver driver, WebElement objRef, String sText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Page.WAIT_UNTIL_TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(objRef));
            objRef.clear();
            objRef.sendKeys(sText);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public static WebElement getAnchorTag(WebDriver driver, String anchorTag) {
        WebElement anchorTagRef = null;
        List<WebElement> anchorTag_Names = driver.findElements(By.tagName("a"));
        for (int i = 0; i < anchorTag_Names.size(); i++) {
            String tagName_found = anchorTag_Names.get(i).getText().trim();
            if (tagName_found.equalsIgnoreCase(anchorTag)) {
                anchorTagRef = anchorTag_Names.get(i);
                break;
            }
        }

        return anchorTagRef;

    }

    public static WebElement getWhenVisible(WebDriver driver, String xpathRef) throws IOException {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Page.WAIT_UNTIL_TIMEOUT);
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRef)));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathRef)));
            element = driver.findElement(By.xpath(xpathRef));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }


}
