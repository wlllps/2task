import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/test/Desktop/JavaAppuimAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        WebElement elementToInitSearch = driver.findElementByXPath("//*[contains(@text,'Search Wikipedia')]");
        elementToInitSearch.click();

        WebElement elementToEnterSearchLine = waitForWebElementPresentByXPath(
                "//*[contains(@text,'Search…')]",
                "Cannot find search  input",
                5
        );

//              driver.findElementByXPath("//*[contains(@text,'Search…')]");
        elementToEnterSearchLine.sendKeys("Appium");
    }
    private WebElement waitForWebElementPresentByXPath(String xpath, String errorMessage, long timeoitInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoitInSeconds);
        wait.withMessage(errorMessage + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedCondition.presenceOfElementLocated(by)
        );


    }
}

