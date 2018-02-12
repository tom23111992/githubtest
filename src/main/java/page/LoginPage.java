package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import until.SeleniumExecutor;

public class LoginPage extends AbstractPage {

    @FindBy(id = "login_field")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@name='commit']")
    private WebElement signInButton;

    public LoginPage(SeleniumExecutor executor) {
        super(executor);
    }

    public void fillLoginForm(String userName, String password) {
        wrappedSendKeys(userNameField, userName);
        wrappedSendKeys(passwordField, password);
    }

    public void clickSignInButton() {
        wrappedClick(signInButton);
    }
}
