package com.lego.billing;


import com.badlogic.gdx.utils.Array;
import com.lego.billing.product.Product;

/**
 * Created by sargis on 10/27/14.
 */
public interface Store {

    void requestProducts(String[] productIds);

    void restoreTransactions();

    void purchaseProduct(String id);

    void consumePurchase(String id);

    void initialize();

    Product getProductById(String id);

    void addListener(StoreListener storeListener);

    void removeListener(StoreListener storeListener);

    void requestProducts(Array<Product> productList);
}
