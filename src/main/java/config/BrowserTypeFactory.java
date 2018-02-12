package config;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserTypeFactory {
    public static WebDriver getBrowser(String browserName) {
        if (browserName.equals("chrome")) {
            return createChromeDriver();
        } else if (browserName.equals("firefox")) {
            return createFirefoxDriver();
        } else {
            throw new IllegalArgumentException("No driver name provided");
        }
    }

    public static WebDriver createChromeDriver() {
        ChromeDriverManager.getInstance().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-notifications");
        options.addArguments("--ignore-certifcate-errors");
        options.addArguments("--allow-file-access-from-files");
        return new ChromeDriver(options);
    }

    public static WebDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\files\\geckodriver.exe");
        return new FirefoxDriver();
    }
}