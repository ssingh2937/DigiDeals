package com.sandhu.digideals;

import android.graphics.Bitmap;

//Object class for storing the home page list items
public class ItemData {
    int itemId;
    Bitmap itemImage;
    String itemName;
    String itemDesc;
    float itemPrice;

    public ItemData(){ }

    public ItemData(int itemId,Bitmap itemImage, String itemName, String itemDesc, float itemPrice) {
        this.itemId = itemId;
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
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

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }
}
