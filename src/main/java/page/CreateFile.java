package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import until.SeleniumExecutor;

public class CreateFile extends AbstractPage {

    @FindBy(name = "filename")
    private WebElement fileNameField;

    @FindBy(id = "submit-file")
    private WebElement submitCommitButton;

    public CreateFile(SeleniumExecutor executor) {
        super(executor);
    }

    public void fillFileName(String fileName) {
        wrappedSendKeys(fileNameField, fileName + ".txt");
    }

    public void clickSubmitCommit() {
        wrappedClick(submitCommitButton);
    }
}
