package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.locators.ZigZagSingleProductPageLocators.*;

public class ZigZagSingleProductPage extends BasePage {
    private By addToCartButton = By.cssSelector(ADD_TO_CART);
    private By cartItems = By.cssSelector(CART_ITEM_COUNT);

    public ZigZagSingleProductPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemsCount() {
        String cartItemsCount = getText(cartItems);
        return cartItemsCount.isEmpty() ? 0
                : Integer.parseInt(cartItemsCount);
    }

    public void addItemToCart() {
        click(addToCartButton);
    }

    
    public void waitUntilItemIsInTheCart() {
        waitForElementTextToBeBack(addToCartButton, "Գնել");
    }


}
