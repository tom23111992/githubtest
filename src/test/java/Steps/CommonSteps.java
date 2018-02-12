package Steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.RandomStringGenerator;
import helpers.enums.Messages;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import page.*;
import until.SeleniumExecutor;

import static until.SeleniumExecutor.driver;

public class CommonSteps {

    private SeleniumExecutor executor;
    private LoginPage loginPage;
    private HomePage homePage;
    private CodeBookmark codeBookmark;
    private CreateRepoForm createRepoForm;
    private CreateFile createFile;
    private SettingsBookmark settingsBookmark;
    private String repoName;
    private String fileName;
    private String branchName;

    public CommonSteps() {
        this.executor = SeleniumExecutor.getExecutor();
        loginPage = new LoginPage(executor);
        homePage = new HomePage(executor);
        createRepoForm = new CreateRepoForm(executor);
        createFile = new CreateFile(executor);
        codeBookmark = new CodeBookmark(executor);
        settingsBookmark = new SettingsBookmark(executor);
        fileName = "test" + RandomStringGenerator.randomString(3);
    }

    @Given("^I login into Github \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iLoginIntoGithubAnd(String userName, String password) {
        loginPage.openPage();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickSignInButton();
        Assert.assertTrue("User is not logged", homePage.isUserLoggedIn());
    }

    @When("^I create a repository$")
    public void createARepository() {
        repoName = "test" + RandomStringGenerator.randomString(5);

        if (homePage.isCreateProjectButtonDisplayed()) {
            homePage.clickCreateProjectButton();
        } else homePage.clickNewRepoButton();

        createRepoForm.fillRepoName(repoName);
        createRepoForm.clickInitializeReadme();
        createRepoForm.clickConfirmCreateRepoButton();
    }

    @Then("^Should be content displayed$")
    public void shouldBeContentDisplayed() {
        Assert.assertTrue("Repo content is not displayed - code bookmark", codeBookmark.isRepoContentDisplayed());
    }

    @And("^I click create file$")
    public void iClickCreateFile() {
        codeBookmark.clickCreateNewFile();
        createFile.fillFileName(fileName);
        createFile.clickSubmitCommit();
    }

    @Then("^Should be file displayed on the list$")
    public void shouldBeFileDisplayedOnTheList() {
        Assert.assertTrue(codeBookmark.isFileDisplayed(fileName));
    }

    @And("^I delete a repository$")
    public void iDeleteARepository() {
        codeBookmark.clickSettingsButton();
        settingsBookmark.clickDeleteRepoButton();
        settingsBookmark.clickConfirmationDeleteRepo(repoName);
        Assert.assertTrue("Notification about deleted repo has not been displayed ", homePage.ifNotificationContains(Messages.REPO_DELETED.toString()));
    }

    @And("^I create new branch$")
    public void iCreateNewBranch() {
        branchName = "branch" + RandomStringGenerator.randomString(3);
        codeBookmark.clickCreateBranch(branchName);

        Assert.assertEquals(Messages.BRANCH_CREATED.toString(), homePage.getNotification());
        Assert.assertEquals(codeBookmark.getCurrentBranchDropdownList(), branchName);
    }

    @And("^I create pull request$")
    public void iCreatePullRequest() {
        codeBookmark.clickNewPullRequest();
        homePage.clickCreatePullRequestButton();
    }

    @Then("^Should be merge request button displayed$")
    public void shouldBeMergeRequestButtonDisplayed() {
        Assert.assertTrue("Merge pull request button is not displayed", homePage.isMergePullRequestDisplayed());
    }

    @And("^I click merge pull request$")
    public void iClickMergePullRequest() {
        homePage.clickMergePullRequestButton();
        homePage.clickConfirmMergeButton();
    }

    @Then("^Should be merged info displayed$")
    public void shouldBeMergedInfoDisplayed() {
        Assert.assertTrue("Merged info is not displayed", homePage.isMergedInfoDisplayed());
    }

    @After
    public void exitMethod(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] myScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(myScreenshot, "image/png");

            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
        SeleniumExecutor.stop();
    }
}
