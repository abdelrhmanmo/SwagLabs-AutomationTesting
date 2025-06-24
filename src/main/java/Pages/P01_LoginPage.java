package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {
    private final By userName = By.xpath("//*[@id=\"user-name\"]");
    private final By password = By.xpath("//*[@id=\"password\"]");
    private final By loginBtn = By.xpath("//*[@id=\"login-button\"]");
    private final WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public P01_LoginPage enterUserName(String userNameTxt) {
        Utility.sendData(driver, userName, userNameTxt);
        return this;
    }

    public P01_LoginPage enterPassword(String passwordTxt) {
        Utility.sendData(driver, password, passwordTxt);
        return this;
    }

    public P02_LandingPage clickLoginBtn() {
        Utility.clickingOnElement(driver, loginBtn);
        return new P02_LandingPage(driver);
    }

    public boolean assertLoginTC(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }
}
