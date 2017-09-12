package kcom.library;

import org.openqa.selenium.JavascriptExecutor;
import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public abstract class Page {

    public WebDriver driver;
    protected JavascriptExecutor  jsExecutor;
    public static final int WAIT_UNTIL_TIMEOUT = 15;
    public static final Log logger = LogFactory.getLog(Page.class);


    private WebDriver getElementLocatorFactory(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor ) driver;
        return driver;
    }

    public Page(WebDriver driver) {
        PageFactory.initElements(getElementLocatorFactory(driver), this);

    }

    protected void waitUntil(ExpectedCondition<?> expectedCondition, int timeoutSeconds) {
        logger.info("waiting until: : " + expectedCondition);
        new WebDriverWait(driver, timeoutSeconds).until(expectedCondition);
    }

    protected void waitUntil(ExpectedCondition<?> expectedCondition) {
        waitUntil(expectedCondition, WAIT_UNTIL_TIMEOUT);
    }


    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return jsExecutor.executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, WAIT_UNTIL_TIMEOUT);
        wait.until(pageLoadCondition);
    }


    public WebElement fluentWait(WebDriver driver, final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(WAIT_UNTIL_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                logger.info("waiting: " + locator);
                return driver.findElement(locator);
            }
        });

        return foo;
    }


    public void switchToTab(int windowNumber) {
        List<String> windowHandles;
        int i = 0;
        do {
            i++;
            windowHandles = new ArrayList<>(driver.getWindowHandles());
        } while (windowHandles.size() < windowNumber && i < 10);

        driver.switchTo().window(windowHandles.get(windowNumber - 1));
    }

    public void repeatUntil(Runnable action, ExpectedCondition<?> expectedCondition, int numberOfRepeats, int timeToWaitInSeconds) {
        int numberOfTries = 0;
        do {
            numberOfTries++;
            action.run();
            try {
                waitUntil(expectedCondition, timeToWaitInSeconds);
                if (numberOfTries > 1) {
                    logger.warn(format("worked after %s tries", numberOfTries));
                }
                return;
            } catch (Exception e) {
                // ignore
            }
        } while (numberOfTries < numberOfRepeats);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void waitInSec(Integer sec) {
        long milisec = sec.longValue() * 1000;
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public void scrollIntoView(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void scrollToTheTop() {
        jsExecutor.executeScript("window.scrollBy(0,-500)", "");
    }


    public Page goToPreviousPage() {
        driver.navigate().back();
        return this;
    }


    public void getscreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("screenshots\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isWebElementReferenceExists(By webElementRef) {
        try {
            driver.findElement(webElementRef);
            return true;

        } catch (NoSuchElementException ex) {
            logger.error(ex.getMessage());
            return false;
        }
    }


}
