package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_LoginPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;

@Listeners({ITestResultListenerClass.class, IInvokedMethodListenerClass.class})
public class TC01_LoginTest {
    @BeforeTest
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environments", "Browser"));
        LogsUtils.info("Edge Driver is Opened");
        getDriver().get(DataUtils.getPropertyValue("environments", "LOGIN_URL"));
        LogsUtils.info("Page is Redirected to the Login URL");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validTestCase() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUserName(DataUtils.getJsonData("validLoginCredintials", "userName"))
                .enterPassword(DataUtils.getJsonData("validLoginCredintials", "password"))
                .clickLoginBtn();
        Assert.assertTrue(new P01_LoginPage(getDriver())
                .assertLoginTC(DataUtils.getPropertyValue("environments", "BASE_URL")));
    }

    @AfterTest
    public void quite() {
        quiteDriver();
    }

}
