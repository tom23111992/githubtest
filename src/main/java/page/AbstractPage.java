package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import until.Configurations;
import until.SeleniumExecutor;

import java.util.List;

import static until.SeleniumExecutor.driver;


public class AbstractPage {

    public SeleniumExecutor executor;
    private String url;

    public AbstractPage(SeleniumExecutor executor) {
        this.executor = executor;
        this.url = SeleniumExecutor.getUrl();
        PageFactory.initElements(new AjaxElementLocatorFactory(executor.getDriver(), 5), this);
    }

    public void wrappedClick(WebElement element) {
        waitUntil(ExpectedConditions.visibilityOf(element));
        waitUntil(ExpectedConditions.elementToBeClickable(element));
        scrollToElementToDown(element);
        element.click();
    }

    public void wrappedSendKeys(WebElement element, String keys) {
        waitUntil(ExpectedConditions.visibilityOf(element));
        waitUntil(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(keys);
    }

    public String wrappedGetText(WebElement element) {
        waitUntil(ExpectedConditions.visibilityOf(element));
        waitUntil(ExpectedConditions.elementToBeClickable(element));
        return element.getText();
    }

    public boolean elementIsPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void scrollToElementToDown(Object locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", locator);
    }

    public <A> void waitUntil(ExpectedCondition<A> conditions) {
        WebDriverWait w8 = new WebDriverWait(executor.getDriver(), 15);
        w8.until(conditions);
    }

    public void openPage() {
        executor.openPage(Configurations.defaultUrl);
    }
}
