package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(By by, String text) {
        getElement(by).sendKeys(text);
    }

    public WebElement getElement(By by) {
        return driver.findElement(by);
    }

    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

    public void click(By by) {
        waitForElementToBeClickable(by).click();
    }

    public void click(By by, int index) {
        var element = getElements(by).get(index);
        element.click();
    }

    public String getText(By by) {
        return getElement(by).getText();
    }

    public String getText(By by, int index) {
        return getElements(by).get(index).getText();
    }

    public boolean isElementDisplayed(By by) {
        return getElement(by).isDisplayed();
    }

    public void waitForElementTextToBeBack(By by, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(getElement(by), text));
    }

    void waitForElementTextToBeBack(WebElement e, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.textToBePresentInElement(e, text));
    }

    public WebElement waitForElementToBeClickable(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
    }
    
    public void waitForElementToBeVisible(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    
    

}