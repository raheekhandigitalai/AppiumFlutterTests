package tests;

import helpers.PropertiesReader;
import io.appium.java_client.android.AndroidDriver;
import io.github.ashwith.flutter.FlutterFinder;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class AndroidFlutterTest {

    protected AndroidDriver driver = null;
    protected FlutterFinder find;
    protected DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("testName", "Flutter Android Test");
        dc.setCapability("accessKey", new PropertiesReader().getProperty("ct.accessKey"));
        dc.setCapability("deviceQuery", "@os='android'");
        dc.setCapability("platformName", "Android");
        dc.setCapability("appiumVersion", "2.2.2");
        dc.setCapability("automationName", "Flutter");
        dc.setCapability("app", "cloud:uniqueName=flutter_app_eribank");
        driver = new AndroidDriver(new URL(new PropertiesReader().getProperty("ct.cloudUrl") + "/wd/hub"), dc);
        find = new FlutterFinder(driver);
    }

    @Test
    public void paymentTest() throws InterruptedException {
        switchContext("FLUTTER");
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        find.byValueKey("Username").sendKeys("company");
        find.byValueKey("Password").sendKeys("company");
        find.byValueKey("LoginButton").click();
        find.byValueKey("Make Payment").click();
        find.byValueKey("Phone").sendKeys("123456789");
        find.byValueKey("Name").sendKeys("user12");
        find.byValueKey("Amount").sendKeys("500");
        find.byValueKey("Country").sendKeys("Israel");
        find.byValueKey("Send Payment").click();
        find.byValueKey("yes").click();
        Thread.sleep(10000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
            driver.quit();
        }
    }

    public void switchContext(String context) {
        Set<String> contexts = driver.getContextHandles();
        System.out.println("application contexts:" + contexts);
        for (String appContext : contexts) {
            if (appContext.contains(context)) {
                driver.context(appContext);
                break;
            }
        }
    }

}

