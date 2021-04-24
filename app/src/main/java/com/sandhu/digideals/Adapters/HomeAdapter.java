package com.sandhu.digideals.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandhu.digideals.Activities.DescriptionRecord;
import com.sandhu.digideals.Activities.MapActivity;
import com.sandhu.digideals.ItemData;
import com.sandhu.digideals.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<ItemData> itemList;
    Context context;

    //constructor
    public HomeAdapter(ArrayList<ItemData> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(itemList.get(position).getItemName());
        holder.price.setText("$"+String.valueOf(itemList.get(position).getItemPrice()));
        holder.image.setImageBitmap(itemList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name  = itemList.get(getAdapterPosition()).getItemName();
                    Intent i = new Intent(context, DescriptionRecord.class);
                    i.putExtra("name",name);
                    context.startActivity(i);
                }
            });
        }
    }
}
