package Tests;

import PageObjectModel.BaseClass;
import PageObjectModel.Pages.FilesPage;
import PageObjectModel.Pages.LoginPage;
import PageObjectModel.Pages.SettingsPage;
import PageObjectModel.Parameters;
import PageObjectModel.WebdriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


public class AlfrescoTest
{
    private BaseClass baseClass = new BaseClass();

    private FilesPage filesPage = new FilesPage();
    private SettingsPage settingsPage = new SettingsPage();
    private LoginPage loginPage =  new LoginPage();

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void startUpBrowser()
    {
        driver = WebdriverFactory.getWebDriver();
        wait = WebdriverFactory.getWebDriverWait();
    }

    @Test
    public void TestSteps() throws Exception
    {
        //Navigate to http://qaexercise.envalfresco.com/settings
        baseClass.navigateTo(Parameters.settingsURL, Parameters.settingsTitle);

        //Change Provider to ECM
        settingsPage.clickProviderDropdown();
        settingsPage.selectProviderByText("ECM");

        //Click Apply
        settingsPage.clickApplyButton();

        //Navigate to http://qaexercise.envalfresco.com/login
        baseClass.navigateTo(Parameters.loginURL, Parameters.loginTitle);

        //Insert Username
        loginPage.insertUsername(Parameters.username);

        //Insert Password
        loginPage.insertPassword(Parameters.password);

        //Click Login
        loginPage.clickLoginButton();

        //Navigate to http://qaexercise.envalfresco.com/files
        baseClass.navigateTo(Parameters.filesURL, Parameters.filesTitle);

        //Click on 'create new folder' icon.
        filesPage.clickNewFolderIcon();

        //New folder dialog is displayed
        filesPage.checkPopUpAppeared();

        //Introduce Github username
        filesPage.insertGithubUsername(Parameters.githubName);

        //Check name has been added
        filesPage.checkGithubNameIsAdded(Parameters.githubName);

        //Click on 'Create' button
        filesPage.clickCreateFolderButton();

        //Folder with your Github username is created in the current folder
        filesPage.checkFolderIsCreated(Parameters.githubName);

        //Click on 'create new folder' icon.
        filesPage.clickNewFolderIcon();

        //New folder dialog is displayed
        filesPage.checkPopUpAppeared();

        //Introduce Github username
        filesPage.insertGithubUsername(Parameters.githubName);

        //Check name has been added
        filesPage.checkGithubNameIsAdded(Parameters.githubName);

        //Click on 'Create' button
        filesPage.clickCreateFolderButton();

        //The message "There's already a folder with this name. Try a different name" is displayed.
        filesPage.checkErrorMessage(Parameters.errorMessage);

        //The dialog is not closed
        filesPage.popUpPresent();

        //Click on 'Cancel' button
        filesPage.clickCancelButton();

        //Select the folder with your Github username
        filesPage.selectFolderWithGithubUsername(Parameters.githubName);

        //Open Options window (3 dots)
        filesPage.clickThreeDots(Parameters.githubName);

        //Click Delete
        filesPage.clickDeleteFolderButton();


    }

    @AfterMethod
    public void closeBrowser()
    {
        driver.close();
    }
}
