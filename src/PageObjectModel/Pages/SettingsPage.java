package PageObjectModel.Pages;

import PageObjectModel.BaseClass;
import PageObjectModel.WebdriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;

public class SettingsPage extends BaseClass
{
    private WebDriverWait wait = WebdriverFactory.getWebDriverWait();

    //click provider dropdown
    public void clickProviderDropdown()
    {
         wait.until(ExpectedConditions.elementToBeClickable(
                By.id("adf-provider-selector"))).click();
    }

   //get items from provider dropdown
    private Collection<WebElement> providerDropdownElements()
    {
       return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.className("mat-option-text")));
    }

    //select provider by given text
    public void selectProviderByText(String providerText) throws InterruptedException
    {
        Collection<WebElement> providerDropdownElements = providerDropdownElements();
        for (WebElement providerElement : providerDropdownElements)
        {
            if (providerElement.getText().equals(providerText))
            {
                providerElement.click();
                Thread.sleep(300);
                break;
            }
        }
        
    }

    //click apply button
    public void clickApplyButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'APPLY')]"))).click();
    }
}
