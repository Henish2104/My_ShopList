package com.example.mymovie.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.mymovie.Models.Response;
import com.example.mymovie.databinding.SingleRawResponcemodelBinding;

public class ResponceAdapter extends RecyclerView.Adapter<ResponceAdapter.viewHolder> {
    Response list;


    Context context;

    public ResponceAdapter(Response list, Context context) {
      this.list=list;
        this.context = context;
    }




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(SingleRawResponcemodelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.binding.productId.setText("Id: " + list.getProducts().get(position).getId());
        holder.binding.productTitle.setText("Title: "+ list.getProducts().get(position).getTitle());
        holder.binding.productDescription.setText("Desc: "+ list.getProducts().get(position).getDescription());
        holder.binding.productPrice.setText("Price: " + list.getProducts().get(position).getPrice());
        holder.binding.productDiscountprice.setText("Discount Price: " + list.getProducts().get(position).getDiscountPercentage());
        holder.binding.productRating.setText("Rating: " + list.getProducts().get(position).getRating());
        holder.binding.productStock.setText("Stock: " + list.getProducts().get(position).getStock());
        holder.binding.productBrand.setText("Brand: "+list.getProducts().get(position).getBrand());
        holder.binding.productCategory.setText("Category: "+list.getProducts().get(position).getCategory());

        Glide.with(holder.binding.productId.getContext()).load(list.getProducts().get(position).getImages().get(0)).into(holder.binding.demoImage);
    }


    @Override
    public int getItemCount() {
        return list.getProducts().size();
    }




       public class viewHolder extends RecyclerView.ViewHolder {

            public SingleRawResponcemodelBinding binding;

            public viewHolder(@NonNull SingleRawResponcemodelBinding itemView) {
                super(itemView.getRoot());
                binding = itemView;


            }
        }
    }



