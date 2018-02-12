package until;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static config.BrowserTypeFactory.createChromeDriver;
import static config.BrowserTypeFactory.createFirefoxDriver;

public class SeleniumExecutor {
    private final static int TIMEOUT = 120; //seconds
    public static WebDriver driver;
    public static String url;
    private static SeleniumExecutor executor;
    private static WebDriverWait waitDriver;
    public Configurations configurations;


    public SeleniumExecutor()  {
        Configurations.setProperties();

        try {
            this.driver = createDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.url = Configurations.defaultUrl;
    }

    public static void stop() {
        driver.close();
        driver.quit();
        executor = null;
    }

    private static void start()  {
        if (executor == null)
            executor = new SeleniumExecutor();
    }

    public static SeleniumExecutor getExecutor() {
        start();
        return executor;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWaitDriver() {
        return waitDriver;
    }


    private static WebDriver createDriver() throws MalformedURLException {

        String browser;

        if (System.getProperty("browser") == null) {
            browser = "chrome";
        } else {
            browser = System.getProperty("browser");
        }

        String hub = "http://192.168.99.100:4444//wd/hub";
        DesiredCapabilities desiredCapabilities;

        String system = System.getProperty("os.name");
        System.out.println(system);

        if (browser.equals("chrome")) {
            return createChromeDriver();
        } else if (browser.equals("firefox")) {
            return createFirefoxDriver();
        }

        waitDriver = new WebDriverWait(driver, TIMEOUT);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);

        return driver;
    }

    public static String getTitle() {
        return driver.getTitle();
    }

    public static String getUrl() {
        return driver.getCurrentUrl();
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    public void openPage(String url) {

        driver.manage().window().maximize();
        driver.navigate().to(url);
    }
}