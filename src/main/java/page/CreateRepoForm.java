package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import until.SeleniumExecutor;

public class CreateRepoForm extends AbstractPage {

    @FindBy(id = "repository_name")
    private WebElement repositoryNameField;

    @FindBy(id = "repository_auto_init")
    private WebElement initializeReadmeRadioButton;

    @FindBy(xpath = "//*[@class='with-permission-fields']/button")
    private WebElement confirmCreateRepoButton;

    public CreateRepoForm(SeleniumExecutor executor) {
        super(executor);
    }

    public void clickInitializeReadme() {
        wrappedClick(initializeReadmeRadioButton);
    }

    public void fillRepoName(String name) {
        wrappedSendKeys(repositoryNameField, name);
    }

    public void clickConfirmCreateRepoButton() {
        wrappedClick(confirmCreateRepoButton);
    }

}
