package PageObjectModel.Pages;

import PageObjectModel.BaseClass;
import PageObjectModel.WebdriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;

public class FilesPage extends BaseClass
{
    private WebDriverWait wait = WebdriverFactory.getWebDriverWait();
    private WebDriver driver = WebdriverFactory.getWebDriver();

    //get new folder icon
    private WebElement getNewFolderIcon()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[contains(text(),'create_new_folder')]")));
    }

    //click create new folder icon
    public void clickNewFolderIcon()
    {
        getNewFolderIcon().click();
    }

    //check popup window is present
    public void popUpPresent()
    {
        Assert.assertTrue(getNewFolderIcon().isDisplayed());
    }

    //if popup window appeared switch driver to it
    public void checkPopUpAppeared() throws Exception {
        try
        {
            popUpPresent();
            driver.switchTo().activeElement();
        }
        catch (NoAlertPresentException exception)
        {
            throw new Exception("Popup not present: " + exception);
        }
    }

    //get github username element from popup window
    private WebElement getPopUpNewFolderName()
    {
        return wait.until(ExpectedConditions.elementToBeClickable(By.id("adf-folder-name-input")));
    }

    //insert github name
    public void insertGithubUsername(String githubUsername)
    {
        getPopUpNewFolderName().sendKeys(githubUsername);
    }

    //check github name is entered
    public void checkGithubNameIsAdded(String githubUsername)
    {
        Assert.assertEquals(githubUsername, getPopUpNewFolderName().getAttribute("value"));
    }

    //click create folder button
    public void clickCreateFolderButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("adf-folder-create-button"))).click();
    }

    //check folder with github username was created
    public void checkFolderIsCreated(String githubname)
    {
        WebElement folderWithGithubName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + githubname + "')]")));
        Assert.assertTrue(folderWithGithubName.isDisplayed());
    }

    //click cancel button
    public void clickCancelButton()
    {
        driver.findElement(By.xpath("//span[contains(text(),'Cancel')]")).click();
    }

    //click folder with github username
    public void selectFolderWithGithubUsername(String githubname)
    {
        driver.findElement(By.xpath("//span[contains(text(),'" + githubname + "')]")).click();
    }

    //checkErrorMessage
    public void checkErrorMessage(String errorMessage)
    {
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),\"There's already a folder with this name. Try a dif\")]")));
        Assert.assertEquals(errorMessage, alert.getText());
    }

    //select all created folders
    private Collection<WebElement> createdFolders()
    {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@class='adf-datatable-body']//adf-datatable-row")));
    }

    //click three dots of created folder
    public void clickThreeDots(String folderName) throws InterruptedException {
        int count = 0;
        Collection<WebElement> createdFolders = createdFolders();

        for (WebElement folderElement : createdFolders)
        {
            if (folderElement.getText().contains(folderName))
            {
                folderElement.findElement(By.id("action_menu_right_" + count)).click();
                Thread.sleep(500);
                break;
            }
            count++;
        }
    }

    //delete folder
    public void clickDeleteFolderButton()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Delete')]"))).click();
    }
}
