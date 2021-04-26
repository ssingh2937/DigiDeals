package com.sandhu.digideals.Activities;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sandhu.digideals.DBHelper;
import com.sandhu.digideals.ItemData;
import com.sandhu.digideals.R;

import java.util.ArrayList;

public class DescriptionRecord extends AppCompatActivity {

    DBHelper dbHelper;
    Spinner quantity;
    String qty[] = {"1","2","3","4","5","6","7","8"};
    Button sumbitbutn;
    String name, desc;
    Float price;
    byte[] image;
    Boolean insertDbState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_description);

        dbHelper = new DBHelper(getApplicationContext());
        quantity = (Spinner) findViewById(R.id.quantity);
        sumbitbutn = (Button) findViewById(R.id.submit);

        //use array adapter to fill Quantity spinner
        ArrayAdapter adt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,qty);
        adt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        quantity.setAdapter(adt);

        //getting the data from intent
        Bundle itemName = getIntent().getExtras();
        if(itemName != null){
            name = itemName.getString("name");
            getItemDescription(name);
        }

        OnClickSubmitButton();
    }

    //On submit click
    private void OnClickSubmitButton() {
        sumbitbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productqty = quantity.getSelectedItem().toString();
                insertDbState =  dbHelper.InsertIntoCart(name, productqty, image, desc, price);
                if(insertDbState == true){
                    Toast.makeText(getApplicationContext(),"Product " + name +" added to cart",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Product " + name +" donot added to cart",Toast.LENGTH_LONG).show();
            }
        });
    }

    //getting item description
    private void getItemDescription(String name) {
       TextView itemname,itemprice,itemdesc;
        ImageView imageView;

        itemname = (TextView) findViewById(R.id.name);
        itemprice = (TextView) findViewById(R.id.desc_price);
        itemdesc = (TextView) findViewById(R.id.desc);
        imageView = (ImageView) findViewById(R.id.desc_image);

        Cursor cursorObj = dbHelper.getItemData(name);

        desc = cursorObj.getString(cursorObj.getColumnIndex("itemDesc"));
        price = cursorObj.getFloat(cursorObj.getColumnIndex("itemPrice"));

        itemname.setText(cursorObj.getString(cursorObj.getColumnIndex("itemName")));
        itemdesc.setText(cursorObj.getString(cursorObj.getColumnIndex("itemDesc")));
        itemprice.setText("$"+String.valueOf(cursorObj.getFloat(cursorObj.getColumnIndex("itemPrice"))));


        //converting the byte to bitmap to set it up on the imageView
        byte[] bitmap = cursorObj.getBlob(1);
        image = bitmap;
        Bitmap image = BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);

        imageView.setImageBitmap(image);
    }
}
