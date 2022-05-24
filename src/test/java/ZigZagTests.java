import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

import static constants.messages.ErrorMessages.*;

/*
1. Test Search Functionality (non-logged in user) 
2. Test Add to cart functionality 
3. Test Change Quantity in Cart functionality 
4. Test Contact with us functionality 
5. Screenshots for failed tests
 */

public class ZigZagTests extends BaseTest {
    // Test Search Functionality
    // search not found
    @Test
    void shouldNotFindSearch() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("adidas");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        Assert.assertTrue(catalogPage.isCatalogEmpty(), NO_SEARCH_RESULTS);
    }

    // Test Search Functionality
    // finds something when searching
    @Test
    void shouldFindSearch() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        Assert.assertTrue(catalogPage.getCatalogSize() != 0, SUCCESSFULL_SEARCH);
    }

    // Test Search Functionality
    // finds search-related products
    @Test
    void shouldFindSearchRelated() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("pizza pan");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        List<String> itemNames = catalogPage.getProductNames();
        for (String name : itemNames) {
            Assert.assertTrue(containsIgnoreCase(name, "pizza", "pan"));
        }
    }

    // Test Change Quantity in Cart functionality
    // add items from catalog page, changes cart's number that is on top right corner
    @Test
    void shouldAddItemsFromCatalogPage() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        catalogPage.addItemsToCart();
        int catalogSize = catalogPage.getCatalogSize();
        int cartItems = catalogPage.getCartItemsCount();

        Assert.assertEquals(catalogSize, cartItems);
    }

    // Test Change Quantity in Cart functionality
    // add items one by one from single-product page, changes cart's number that is on top right corner
    @Test
    void shouldAddItemToCart() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();

        for (int i = 0; i < 5; i++) {
            ZigZagSingleProductPage productPage = catalogPage.clickCatalogItem(i);
            productPage.addItemToCart();
            productPage.waitUntilItemIsInTheCart();
            driver.navigate().back();
        }

        ZigZagCartPage cartPage = catalogPage.clickCartLink();
        Assert.assertEquals(cartPage.getCartItemsCount(), 5);
    }

    // Test Change Quantity in Cart functionality
    // add items from catalog, check that many products occur on the cart page
    @Test
    void shouldAddAllToCart() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        int catalogSize = catalogPage.getCatalogSize();
        catalogPage.addItemsToCart();

        ZigZagCartPage cart = catalogPage.clickCartLink();
        int cartSize = cart.getCartItemsCount();

        Assert.assertEquals(catalogSize, cartSize);
    }

    // Test Add to cart functionality
    // add items to the cart, test that total price is correct
    @Test
    void shouldHaveCorrectTotalPrice() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        catalogPage.addItemsToCart();
        var totalPrice = catalogPage.getCartTotal();

        ZigZagCartPage cartPage = catalogPage.clickCartLink();
        var totalCartPrice = cartPage.getCartTotalPrice();

        Assert.assertEquals(totalPrice, totalCartPrice);
    }

    // Test Add to cart functionality
    // add items to the cart test that product names are correct
    @Test
    void shouldHaveCorrectNames() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        catalogPage.addItemsToCart();
        var catalogItemNames = catalogPage.getProductNames();

        ZigZagCartPage cartPage = catalogPage.clickCartLink();
        var cartItemNames = cartPage.getNames();
        Assert.assertEquals(catalogItemNames.size(), cartItemNames.size());

        for (int i = 0; i < cartItemNames.size(); i++) {
            Assert.assertEquals(cartItemNames.get(i), catalogItemNames.get(i));
        }
    }


    // Test Contact with us functionality
    // when all fields are empty, error messages should show
    @Test
    void shouldNotSendContactForm1() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        ZigZagContactUsPage contactUsPage = mainPage.clickContactUsButton();
        contactUsPage.clickSendButton();

        Assert.assertTrue(contactUsPage.commentErrorIsDisplayed(), String.format(ERROR_NOT_DISPLAYED, "Comment"));
        Assert.assertTrue(contactUsPage.nameErrorIsDisplayed(), String.format(ERROR_NOT_DISPLAYED, "Name"));
        Assert.assertTrue(contactUsPage.emailErrorIsDisplayed(), String.format(ERROR_NOT_DISPLAYED, "Email"));
        Assert.assertEquals(contactUsPage.commentErrorText(), "Սա պարտադիր դաշտ է:");
        Assert.assertEquals(contactUsPage.nameErrorText(), "Սա պարտադիր դաշտ է:");
        Assert.assertEquals(contactUsPage.emailErrorText(), "Սա պարտադիր դաշտ է:");
    }

    // Test Contact with us functionality
    // when inputted email is not in correct form
    @Test
    void shouldNotSendContactForm2() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        ZigZagContactUsPage contactUsPage = mainPage.clickContactUsButton();
        contactUsPage.inputName("John Doe");
        contactUsPage.inputEmail("wrong_email");
        contactUsPage.inputComment("no comment");

        contactUsPage.clickSendButton();

        Assert.assertTrue(contactUsPage.emailErrorIsDisplayed(), String.format(ERROR_NOT_DISPLAYED, "Email"));
        Assert.assertEquals(contactUsPage.emailErrorText(), "Խնդրում ենք մուտքագրել վավեր էլ-փոստի հասցե");
    }

    // Test Contact with us functionality
    // when all is good
    @Test
    void shouldSendFeedback() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        ZigZagContactUsPage contactUsPage = mainPage.clickContactUsButton();
        contactUsPage.inputName("John Doe");
        contactUsPage.inputEmail("gabasob884@duetube.com");
        contactUsPage.inputComment("Appreciation to the zigzag team!");
        contactUsPage.inputPhone("+374 99 929445");

        Assert.assertTrue(true);
        // commented for not spamming
//        ZigZagSuccessfulContactUsPage successfulContactUsPage = contactUsPage.clickSendButton();
//        var successMessage = successfulContactUsPage.getSuccessMessage();
//        Assert.assertEquals(successMessage, "Thanks for contacting us with your comments and questions. We'll respond to you very soon.");
    }
    
    @Test
    void shouldFail() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        ZigZagSingleProductPage productPage = catalogPage.clickCatalogItem(0);
        productPage.addItemToCart();
        productPage.waitUntilItemIsInTheCart();
        
        Assert.assertTrue(true);
        // uncomment below line for taking screenshot
        // Assert.assertTrue(productPage.getCartItemsCount() == 2);
    }

    private boolean containsIgnoreCase(String name, String... keywords) {
        String nameLowerCase = name.toLowerCase();
        for (String keyword : keywords) {
            if (nameLowerCase.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
