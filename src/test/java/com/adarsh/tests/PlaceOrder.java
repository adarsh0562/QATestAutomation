package com.adarsh.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.adarsh.pageobjects.CartPage;
import com.adarsh.pageobjects.CheckoutPage;
import com.adarsh.pageobjects.ConfirmationPage;
import com.adarsh.pageobjects.OrdersHistoryPage;
import com.adarsh.pageobjects.ProductsCatalog;
import com.adarsh.tests.components.BaseTest;

public class PlaceOrder extends BaseTest {

    @Test(dataProvider= "getData")
    public void purchaseOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        // Ensure prop is initialized
        Assert.assertNotNull(prop, "Properties object is not initialized.");

        ProductsCatalog productsCatalog = landingPage.userLogin(input.get("loginUserEmail"), input.get("loginUserPassword"));
        productsCatalog.addToCart(input.get("productName"));

        CartPage cartPage = productsCatalog.landingCartPage();
        Assert.assertTrue(cartPage.verifyItems(input.get("productName")));

        CheckoutPage checkoutPage = cartPage.landingCheckoutPage();
        ConfirmationPage confirmationPage = checkoutPage.submitOrder(input.get("countryName"), input.get("cvvNumber"), input.get("nameOnCard"));

        Assert.assertEquals(prop.getProperty("thankYouMSG"), confirmationPage.thankYouPage());
    }

    @Test(dependsOnMethods= {"purchaseOrder"}, dataProvider = "getData")
    public void viewOrderHistory(HashMap<String, String> input) throws IOException {
        ProductsCatalog productsCatalog = landingPage.userLogin(input.get("loginUserEmail"), input.get("loginUserPassword"));
        OrdersHistoryPage orderPlaced = productsCatalog.landingOrdersPage();
        Assert.assertTrue(orderPlaced.verifyOrderedProductName(input.get("productName")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("productName", "ADIDAS ORIGINAL");
//        map.put("countryName", "India");
//        map.put("cvvNumber", "789");
//        map.put("nameOnCard", "Adarsh Raj");
//
//        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("productName", "IPHONE 13 PRO");
//        map1.put("countryName", "India");
//        map1.put("cvvNumber", "123");
//        map1.put("nameOnCard", "Adarsh Gupta");

        List<HashMap<String, String>> data = getJSONDataToMap(System.getProperty("user.dir")+"\\src\\main\\java\\com\\adarsh\\testdata\\test-data.json");
        //return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
        return new Object[][] {{data.get(0)}};
    }
}
