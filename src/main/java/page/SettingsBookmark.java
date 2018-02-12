package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import until.SeleniumExecutor;

public class SettingsBookmark extends AbstractPage {

    @FindBy(xpath = "//button[contains(.,'Delete')]")
    private WebElement deleteRepoButton;

    @FindBy(xpath = "//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")
    private WebElement confirmDeleRepoNameField;

    @FindBy(xpath = "//button[text()='I understand the consequences, delete this repository']")
    private WebElement confirmDeleteRepoButton;

    public SettingsBookmark(SeleniumExecutor executor) {
        super(executor);
    }

    public void clickConfirmationDeleteRepo(String repoName) {
        wrappedSendKeys(confirmDeleRepoNameField, repoName);
        wrappedClick(confirmDeleteRepoButton);
    }

    public void clickDeleteRepoButton() {
        wrappedClick(deleteRepoButton);
    }
}
