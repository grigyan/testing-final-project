package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.ZigZagContactUsPageLocators.*;

public class ZigZagContactUsPage extends BasePage{
    private By name = By.id(NAME);
    private By email = By.cssSelector(EMAIL);
    private By phone = By.id(PHONE);
    private By comment = By.id(COMMENT);
    private By send = By.cssSelector(SEND_BUTTON);
    private By nameError = By.id(NAME_ERROR);
    private By emailError = By.id(EMAIL_ERROR);
    private By commentError = By.id(COMMENT_ERROR);
    
    public ZigZagContactUsPage(WebDriver driver) {
        super(driver);
    }

    public void inputName(String username) {
        sendKeys(name, username);
    }
    
    public void inputEmail(String userEmail) {
        sendKeys(email, userEmail);
    }
    
    public void inputPhone(String userPhone) {
        sendKeys(phone, userPhone);
    }
    
    public void inputComment(String userComment) {
        sendKeys(comment, userComment);
    }
    
    public boolean nameErrorIsDisplayed() {
        return getElement(nameError).isDisplayed();
    }
    
    public boolean emailErrorIsDisplayed() {
        return getElement(emailError).isDisplayed();
    }
    
    public boolean commentErrorIsDisplayed() {
        return getElement(commentError).isDisplayed();
    }
    
    public String nameErrorText() {
        return getElement(nameError).getText();
    }
    
    public String emailErrorText() {
        return getElement(emailError).getText();
    }
    
    public String commentErrorText() {
        return getElement(commentError).getText();
    }
    
    public ZigZagSuccessfulContactUsPage clickSendButton() {
        click(send);
        return new ZigZagSuccessfulContactUsPage(driver);
    }
}
