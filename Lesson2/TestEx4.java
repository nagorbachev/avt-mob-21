import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import io.appium.java_client.AppiumDriver;
        import io.appium.java_client.android.AndroidDriver;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import java.net.URL;
        import java.util.List;
        import java.util.Arrays;

public class TestEx4 {
private AppiumDriver driver;

@Before
public void setUp() throws Exception {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "AndroidTestDevice");
        desiredCapabilities.setCapability("platformVersion", "8.0");
        desiredCapabilities.setCapability("automationName", "Appium");
        desiredCapabilities.setCapability("appPackage", "org.wikipedia");
        desiredCapabilities.setCapability("appActivity", ".main.MainActivity");
        desiredCapabilities.setCapability("app", "/Users/revenge/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        }

@After
public void tearDown() {
        driver.quit();
        }




@Test
public void Test4() {
        waitForElementAndClick(
        By.id("org.wikipedia:id/search_container"),
        "Cannot find 'Search Wikipedia' input",
        5
        );

        waitForElementAndSendKeys(
        By.xpath("//*[contains(@text,'Search…')]"),
        "SQL",
        "Cannot find search input",
        20
        );

    List<WebElement> elements = waitForElementsPresent(
            By.id("org.wikipedia:id/page_list_item_title"),
            "Cannot find item id",
            20
    );

    for (WebElement element : elements ) {
        String title = element.getText();
        System.out.println("Есть ли слово \"SQL\" в результатах поиска? Ответ: " + title.contains("SQL"));
    }

        }


private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
        {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
        ExpectedConditions.presenceOfElementLocated(by)
        );
        }


private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
        {
        WebElement element = waitForElementPresent(by,error_message, timeoutInSeconds);
        element.click();
        return element;

        }


private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
        {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;

        }



    private List<WebElement> waitForElementsPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(by)
        );
    }
    
        }