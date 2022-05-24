package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;


import static constants.locators.ZigZagCatalogPageLocators.*;

public class ZigZagCatalogPage extends BasePage {
    private By emptyCatalogMessage = By.cssSelector(EMPTY_CATALOG_MESSAGE);
    private By catalogItems = By.className(PRODUCT_BLOCK);
    private By cartLink = By.cssSelector(CART_LINK);
    private By productNames = By.className(PRODUCT_NAMES);
    private By buyProductButtons = By.cssSelector(BUY_PRODUCT);
    private By cartItems = By.cssSelector(CART_ITEM_COUNT);
    private By cartTotalPrice = By.cssSelector(CART_TOTAL);

    public ZigZagCatalogPage(WebDriver driver) {
        super(driver);
    }

    public int getCatalogSize() {
        return getElements(catalogItems).size();
    }

    public List<String> getProductNames() {
        return getElements(productNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getCartTotal() {
        return getElement(cartTotalPrice)
                .getText();
    }

    public ZigZagSingleProductPage clickCatalogItem(int itemIndex) {
        click(catalogItems, itemIndex);
        return new ZigZagSingleProductPage(driver);
    }

    public int getCartItemsCount() {
        String cartItemsCount = getText(cartItems);
        return cartItemsCount.isEmpty() ? 0
                : Integer.parseInt(cartItemsCount);
    }

    public void addItemsToCart() {
        var webElementsList = getElements(buyProductButtons);
        for (int i = 0; i < getCatalogSize(); i++) {
            Actions action = new Actions(driver);
            var element = webElementsList.get(i);
            action.moveToElement(element).perform();
            click(buyProductButtons, i);
            waitForElementTextToBeBack(element, "Գնել");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
            I also tried to wait for state change, but it was not enough and website crashed
            that is why I decided to use Thread.sleep(5000);
            waitForElementTextToBeBack(element, "Գնել");
             */
        }
    }

    public ZigZagCartPage clickCartLink() {
        click(cartLink);
        return new ZigZagCartPage(driver);
    }

    public boolean isCatalogEmpty() {
        return isElementDisplayed(emptyCatalogMessage);
    }

}
