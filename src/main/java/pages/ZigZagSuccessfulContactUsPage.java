package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.ZigZagSuccessfulContactUsPageLocators.SUCCESS_MESSAGE;

public class ZigZagSuccessfulContactUsPage extends BasePage {
    public ZigZagSuccessfulContactUsPage(WebDriver driver) {
        super(driver);
    }

    private By successMessage = By.cssSelector(SUCCESS_MESSAGE);

    public String getSuccessMessage() {
        waitForElementToBeVisible(successMessage);
        return getElement(successMessage).getText();
    }
}
