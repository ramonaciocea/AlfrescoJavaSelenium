package PageObjectModel.Pages;

import PageObjectModel.BaseClass;
import PageObjectModel.WebdriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseClass
{
    private WebDriverWait wait = WebdriverFactory.getWebDriverWait();

    //insert username
    public void insertUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).sendKeys(username);
    }

    //insert password
    public void insertPassword(String password){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).sendKeys(password);
    }

    //click login button
    public void clickLoginButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("adf-login-button-label"))).click();
        Thread.sleep(1000);
    }
}
