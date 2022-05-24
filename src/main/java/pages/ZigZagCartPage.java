package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static constants.locators.ZigZagCartPageLocators.*;

public class ZigZagCartPage extends BasePage {
    private By cartItems = By.cssSelector(CART_ITEM);
    private By totalPrice = By.cssSelector(TOTAL_PRICE);
    private By productNames = By.cssSelector(PRODUCT_NAMES);
    
    public ZigZagCartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemsCount() {
        return getElements(cartItems).size();
    }
    
    public String getCartTotalPrice() {
        waitForElementToBeClickable(totalPrice);
        return getElement(totalPrice).getText();
    }
    
    public List<String> getNames() {
        return getElements(productNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
