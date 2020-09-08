package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverFactory
{
    private static WebDriver driver;
    private static WebDriverWait wait;

    private WebdriverFactory()
    {
        //prevent instantiation
    }

    public static WebDriver getWebDriver()
    {
        if(driver == null)
        {
            System.setProperty("webdriver.chrome.driver","D:\\SeleniumWebdriver\\chromedriver.exe");  //this path has to be updated
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait()
    {
        if(wait == null)
        {
            wait = new WebDriverWait(driver, 30);
        }
        return wait;
    }
}
