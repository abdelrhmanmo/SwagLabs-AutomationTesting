package Tests;

import Pages.P01_LoginPage;
import Utilities.DataUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.setupDriver;

public class TC01_LoginTest {
    @BeforeTest
    public void setup() throws IOException {
        setupDriver(DataUtils.getPropertyValue("environments", "Browser"));
        getDriver().get(DataUtils.getPropertyValue("environments", "LOGIN_URL"));
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

    }

}
