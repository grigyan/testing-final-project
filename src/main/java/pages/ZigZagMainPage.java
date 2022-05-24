package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.ZigZagMainPageLocators.*;

public class ZigZagMainPage extends BasePage {
    private By searchBar = By.id(SEARCH_BAR);
    private By searchButton = By.xpath(SEARCH_BUTTON);
    private By contactUsButton = By.xpath(CONTACT_US);

    public ZigZagMainPage(WebDriver driver) {
        super(driver);
    }

    public ZigZagCatalogPage clickSearchButton() {
        click(searchButton);
        return new ZigZagCatalogPage(driver);
    }

    public void searchItemWithName(String itemName) {
        sendKeys(searchBar, itemName);
    }

    public ZigZagContactUsPage clickContactUsButton() {
        click(contactUsButton);
        return new ZigZagContactUsPage(driver);
        
    }

}
