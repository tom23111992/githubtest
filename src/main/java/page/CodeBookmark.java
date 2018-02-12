package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import until.SeleniumExecutor;

import java.util.List;

public class CodeBookmark extends AbstractPage {

    @FindBy(xpath = "//*[@id='js-repo-pjax-container']//*[contains(@href,'settings')]")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[@aria-label='Switch branches or tags']")
    private WebElement switchBranchButton;

    @FindBy(id = "context-commitish-filter-field")
    private WebElement branchNameField;

    @FindBy(xpath = "//*[@data-tab-filter='branches']//*[@class='select-menu-item-text']")
    private WebElement newBranchButton;

    @FindBy(xpath = "//*[@class='js-select-button css-truncate-target']")
    private WebElement currentBranch;

    @FindBy(xpath = "//a[contains(.,'New pull request')]")
    private WebElement newPullRequestButton;

    @FindBy(xpath = "//*[@class='repository-content ']")
    private WebElement repoContent;

    @FindBy(xpath = "//*[@class='BtnGroup-form']//button")
    private WebElement createNewFile;

    @FindBy(xpath = "//*[@class='file-wrap']//*[@class='content']/*[@class='css-truncate css-truncate-target']")
    private List<WebElement> fileNameList;

    public CodeBookmark(SeleniumExecutor executor) {
        super(executor);
    }

    public boolean isFileDisplayed(String fileName) {
        boolean value = false;
        String name;
        for (int i = 0; i < fileNameList.size(); i++) {
            name = wrappedGetText(fileNameList.get(i)).split("\\.")[0];
            if (fileName.equals(name)) {
                value = true;
                break;
            }
        }
        return value;
    }

    public boolean isRepoContentDisplayed() {
        return elementIsPresent(repoContent);
    }

    public void clickCreateNewFile() {
        wrappedClick(createNewFile);
    }

    public void clickCreateBranch(String branchName) {
        wrappedClick(switchBranchButton);
        wrappedSendKeys(branchNameField, branchName);
        wrappedClick(newBranchButton);
    }

    public void clickSettingsButton() {
        wrappedClick(settingsButton);
    }

    public String getCurrentBranchDropdownList() {
        return wrappedGetText(currentBranch);
    }

    public void clickNewPullRequest() {
        wrappedClick(newPullRequestButton);
    }
}
