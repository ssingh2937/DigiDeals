package com.sandhu.digideals.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandhu.digideals.Activities.MapActivity;
import com.sandhu.digideals.CartData;
import com.sandhu.digideals.ItemData;
import com.sandhu.digideals.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    ArrayList<CartData> itemList;

    public CartAdapter(ArrayList<CartData> itemList, Context context) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.name.setText(itemList.get(position).getItemName());
        holder.desc.setText(itemList.get(position).getItemQuantity() + " quantity");
        holder.price.setText("$"+String.valueOf(itemList.get(position).getItemPrice()));
        holder.image.setImageBitmap(itemList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,desc;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            desc = (TextView) itemView.findViewById(R.id.item_desc);
            price = (TextView) itemView.findViewById(R.id.item_price);
            image = (ImageView) itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name  = itemList.get(getAdapterPosition()).getItemName();
                    Intent i = new Intent(context, MapActivity.class);
                    i.putExtra("name",name);
                    context.startActivity(i);
                }
            });
        }
    }
}
