package test;

import kcom.library.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    public static final Logger logger = LoggerFactory.getLogger(AbstractTest.class);
    public static final String environment = getEnvironment();
    protected WebDriver driver;
    public static String url;
    public static String user;
    public static String password;


    public WebDriver getDriver() {
        return driver;
    }

    public void setUp() throws IOException, InterruptedException {
        getBrowser();
        getConfig();
        openBrowser();
        initializePage();
    }

    private void getConfig() throws IOException {
        Properties properties = readConfiguration("ENV", environment);
        this.url = properties.getProperty("url");
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");
    }

    private void initializePage() {
        switch (environment) {
            case "rarstest":
                loginPage();
                break;
            case "rarsprod":
                break;

            case "rarsdev":
                break;
            default:
                loginPage();
                break;
        }
    }

    private static String getEnvironment() {
        return System.getProperty("environment");
    }

    private void getBrowser() {
        String browser = System.getProperty("browser");
        switch (browser) {
            case "chrome":
                getChromeBrowser();
                break;

            case "firefox":
                getFirefoxBrowser();
                break;

            case "ie":
                getIEBrowser();
                break;
            default:
                getChromeBrowser();
                break;
        }
    }

    private void getChromeBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Page.WAIT_UNTIL_TIMEOUT, TimeUnit.SECONDS);
    }


    private void getIEBrowser() {
        System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/iedriver/IEDriverServer.exe");
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        driver = new InternetExplorerDriver(caps);
        driver.manage().timeouts().implicitlyWait(Page.WAIT_UNTIL_TIMEOUT, TimeUnit.SECONDS);
        maximizeBrowser();

    }

    private void getFirefoxBrowser() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Page.WAIT_UNTIL_TIMEOUT, TimeUnit.SECONDS);
        maximizeBrowser();
        //driver.manage().deleteAllCookies();
    }

    private void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    private void openBrowser() {
        driver.get(url);
    }


    private Properties readConfiguration(String key, String value) throws IOException {
        String env = System.getProperty(key, value);
        final Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream(
                "/config/" + env + ".properties"));
        return properties;
    }

    private void loginPage() {
        logger.info("RARS login Page");
    }

    public void waitInSec(Integer sec) {
        long milisec = sec.longValue() * 1000;
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        driver.close();
    }
}
