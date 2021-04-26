package com.sandhu.digideals.Fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandhu.digideals.Adapters.CartAdapter;
import com.sandhu.digideals.Adapters.HomeAdapter;
import com.sandhu.digideals.CartData;
import com.sandhu.digideals.DBHelper;
import com.sandhu.digideals.ItemData;
import com.sandhu.digideals.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        dbHelper = new DBHelper(getContext());

        recyclerView = v.findViewById(R.id.cart_recycler);
        getAllData();

        return v;
    }

    //getting all data and inflating to a recyclerView
    private void getAllData() {
        ArrayList<CartData> gList = new ArrayList<>();
        Cursor cursorObj = dbHelper.getCartData();

        if(cursorObj.moveToFirst()) {
            while (!cursorObj.isAfterLast()) {
                CartData itemData = new CartData();
                itemData.setItemId(cursorObj.getInt(cursorObj.getColumnIndex("cartid")));
                itemData.setItemName(cursorObj.getString(cursorObj.getColumnIndex("itemName")));
                itemData.setItemDesc(cursorObj.getString(cursorObj.getColumnIndex("itemDesc")));
                itemData.setItemQuantity(cursorObj.getString(cursorObj.getColumnIndex("itemQty")));
                itemData.setItemPrice(cursorObj.getFloat(cursorObj.getColumnIndex("itemPrice")));
                byte[] bitmap = cursorObj.getBlob(cursorObj.getColumnIndex("itemImage"));
                Bitmap image = BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);

                itemData.setItemImage(image);
                gList.add(itemData);
                cursorObj.moveToNext();
            }
        }
        cursorObj.close();

        //add recycle View to adapter
        RecyclerView.LayoutManager layoutManagerObj=new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManagerObj);
        CartAdapter gAdapter = new CartAdapter(gList,getContext());
        recyclerView.setAdapter(gAdapter);
        gAdapter.notifyDataSetChanged();
    }
}