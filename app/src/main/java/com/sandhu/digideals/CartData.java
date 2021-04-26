package com.sandhu.digideals;

import android.graphics.Bitmap;

//Object class for storing cart list data
public class CartData {
    int itemId;
    Bitmap itemImage;
    String itemName;
    String itemDesc;
    String quantity;
    float itemPrice;

    public CartData(){ }

    public CartData(int itemId, Bitmap itemImage, String itemName, String itemDesc, float itemPrice, String quantity) {
        this.itemId = itemId;
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }


    public void setItemId(int itemId){
        this.itemId = itemId;
    }

    public Bitmap getItemImage() {
        return itemImage;
    }

    public void setItemImage(Bitmap itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemQuantity() {
        return quantity;
    }

    public void setItemQuantity(String quantity) {
        this.quantity = quantity;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }
}
