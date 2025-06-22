package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static ThreadLocal<WebDriver>
            driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser){
        switch(browser.toLowerCase()){
            case "chrome":
                driverThreadLocal.set(new ChromeDriver(new ChromeOptions().addArguments("--start-maximized")));
                break;
            case "firefox":
                driverThreadLocal.set(new FirefoxDriver(new FirefoxOptions().addArguments("--start-maximized")));
                break;
            default:
                driverThreadLocal.set(new EdgeDriver(new EdgeOptions().addArguments("--start-maximized")));
        }
    }

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static void quiteDriver(){
        getDriver().quit();
        driverThreadLocal.remove();
    }
}
