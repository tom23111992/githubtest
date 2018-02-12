package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import until.SeleniumExecutor;

import java.util.List;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//*[@class='shelf-content']/a[@href='/new']")
    private WebElement createProjectButton;

    @FindBy(xpath = "//*[@class='boxed-group-action']/*[@href='/new']")
    private WebElement newRepoButton;

    @FindBy(xpath = "//*[@class='logout-form']//button")
    private WebElement signOutButton;

    @FindBy(xpath = "//*[@class='dropdown js-menu-container']")
    private List<WebElement> dropDownList;

    @FindBy(xpath = "//div[@class='container']")
    private WebElement notification;

    //after pull

    @FindBy(xpath = "//button[text()='Create pull request']")
    private WebElement createPullRequestButton;

    @FindBy(xpath = "//button[contains(.,'Merge pull request')]")
    private WebElement merqePullRequestButton;

    @FindBy(xpath = "//button[@value='merge']")
    private WebElement confirmPullRequestButton;

    @FindBy(xpath = "//h4[text()='Pull request successfully merged and closed']")
    private WebElement mergedInfo;

    public HomePage(SeleniumExecutor executor) {
        super(executor);
    }

    public void clickCreatePullRequestButton() {
        wrappedClick(createPullRequestButton);
    }

    public void clickMergePullRequestButton() {
        waitUntil(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(.,'Merge pull request')]")));
        wrappedClick(merqePullRequestButton);
    }

    public boolean isMergePullRequestDisplayed() {
        return elementIsPresent(merqePullRequestButton);
    }

    public void clickConfirmMergeButton() {
        wrappedClick(confirmPullRequestButton);
    }

    public boolean isMergedInfoDisplayed() {
        return elementIsPresent(mergedInfo);
    }

    public String getNotification() {
        return wrappedGetText(notification);
    }

    public boolean isNotificationDisplayed() {
        return elementIsPresent(notification);
    }

    public boolean ifNotificationContains(String text) {
        return getNotification().contains(text);
    }

    public void clickCreateProjectButton() {
        wrappedClick(createProjectButton);
    }

    public void clickNewRepoButton() {
        wrappedClick(newRepoButton);
    }

    private boolean isSignOutButtonDisplayed() {
        wrappedClick(dropDownList.get(2));
        return elementIsPresent(signOutButton);
    }

    public boolean isCreateProjectButtonDisplayed() {
        return elementIsPresent(createProjectButton);
    }

    public boolean isUserLoggedIn() {
        return elementIsPresent(createProjectButton) || elementIsPresent(newRepoButton);
    }
}
