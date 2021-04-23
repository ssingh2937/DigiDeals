package com.sandhu.digideals.Fragment;

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

import com.sandhu.digideals.ItemData;
import com.sandhu.digideals.Adapters.HomeAdapter;
import com.sandhu.digideals.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ItemData> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = v.findViewById(R.id.item_recycler);

        itemList = new ArrayList<>();

        itemList.add(new ItemData(convertDrawableToBase64(R.drawable.logo), "Camera", "Used to click photos", "$700"));

        HomeAdapter adapter = new HomeAdapter(itemList, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);

        return v;
    }


    String convertDrawableToBase64(int image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), image);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageString;
    }
}