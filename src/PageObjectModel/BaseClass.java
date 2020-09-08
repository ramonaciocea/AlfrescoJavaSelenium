package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClass
{
    private WebDriver driver = WebdriverFactory.getWebDriver();
    private WebDriverWait wait = WebdriverFactory.getWebDriverWait();

    //navigate to given url
    public void navigateTo(String Url, String pageTitle) throws Exception
    {
        driver.navigate().to(Url);
        ensurePageLoaded(Url, pageTitle);
    }

    //ensure page is loaded after navigation
    public boolean ensurePageLoaded(String Url, String pageTitle) throws Exception
    {
        boolean pageIsLoaded = wait.until(ExpectedConditions.titleIs(pageTitle)) && wait.until(ExpectedConditions.urlToBe(Url));
        Thread.sleep(500);

        if (!pageIsLoaded)
        {
            throw new Exception("Failed to load page " + driver.getCurrentUrl());
        }
        return true;
    }
}
