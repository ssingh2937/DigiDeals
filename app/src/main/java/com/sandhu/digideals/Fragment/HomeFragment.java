package com.sandhu.digideals.Fragment;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sandhu.digideals.ItemData;
import com.sandhu.digideals.DBHelper;
import com.sandhu.digideals.Adapters.HomeAdapter;
import com.sandhu.digideals.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    DBHelper dbHelper;
    Boolean insertDbState;
    Bitmap bitmap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.item_recycler);
        dbHelper = new DBHelper(getContext());

        Cursor cursorObj = dbHelper.getData();
        if(cursorObj.getCount() == 0) {

            String productName[] = {"Camera", "Charger Cables", "Headphones", "Keyboards", "Laptop", "Phone", "Projector", "Ram", "Tablets", "TV"};
            float productPrice[] = {270f, 100f, 278.60f, 500f, 200f, 1190f, 2110f, 1270f, 2270f, 6220f};
            int productImage[] = {R.drawable.camera, R.drawable.chargercable, R.drawable.headphone, R.drawable.keyboard, R.drawable.laptop,
                    R.drawable.phone, R.drawable.projector, R.drawable.ram, R.drawable.tablet, R.drawable.tv};
            String productDesc[] = {"A DX-format 20.9-Megapixel CMOS sensor delivers superior image quality, sharpness, color and tones to document it all, even in low light",
                    " What you will get: 5 pack Phone charger in assorted Lengths (3FT, 3FT, 6FT, 6FT, 10FT) for different occasions",
                    "Good product for purchase", "Good product for purchase", "Good product for purchase", "Good product for purchase"
                    , "Good product for purchase", "Good product for purchase", "Good product for purchase", "Good product for purchase"};

            for (int i = 0; i < 10; i++) {
                insertDbState = dbHelper.InsertItems(convertDrawableToBase64(productImage[i]), productName[i], productDesc[i], productPrice[i]);
            }
        }
        getImageFromDB();

        return v;
    }

    private void getImageFromDB() {
       ArrayList<ItemData> gList = new ArrayList<>();
        Cursor cursorObj = dbHelper.getData();

        if(cursorObj.moveToFirst()) {
            while (!cursorObj.isAfterLast()) {
                ItemData itemData = new ItemData();
                itemData.setItemId(cursorObj.getInt(cursorObj.getColumnIndex("itemId")));
                itemData.setItemName(cursorObj.getString(cursorObj.getColumnIndex("itemName")));
                itemData.setItemDesc(cursorObj.getString(cursorObj.getColumnIndex("itemDesc")));
                itemData.setItemPrice(cursorObj.getFloat(cursorObj.getColumnIndex("itemPrice")));
                byte[] bitmap = cursorObj.getBlob(1);
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
        HomeAdapter gAdapter = new HomeAdapter(gList,getContext());
        recyclerView.setAdapter(gAdapter);
        gAdapter.notifyDataSetChanged();

    }

    //convert image to byte for storing in data
    byte[] convertDrawableToBase64(int image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return imageBytes;
    }
}