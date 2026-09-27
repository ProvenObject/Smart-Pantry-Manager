package com.provenobject.smartpantrymanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.provenobject.smartpantrymanager.R;
import com.provenobject.smartpantrymanager.model.PantryItem;

import java.util.List;

// Adapter responsible for displaying pantry items inside the RecyclerView
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> pantryItems;

    // Creates the adapter with the pantry items that should be displayed
    public PantryAdapter(List<PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
    }

    // Creates a new ViewHolder when the RecyclerView needs a new item view
    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    // Places pantry data into the views for a particular RecyclerView item
    @Override
    public void onBindViewHolder(
            @NonNull PantryViewHolder holder,
            int position
    ) {
        PantryItem item = pantryItems.get(position);

        holder.tvIngredientName.setText(item.getName());

        String quantityText = item.getQuantity()
                + " "
                + item.getUnit();

        holder.tvIngredientQuantity.setText(quantityText);

        if (item.getExpiryDate() == null ||
                item.getExpiryDate().isEmpty()) {

            holder.tvIngredientExpiry.setText(
                    "Expiry: Not specified"
            );

        } else {

            holder.tvIngredientExpiry.setText(
                    "Expiry: " + item.getExpiryDate()
            );
        }
    }

    // Returns the number of pantry items currently displayed by the adapter
    @Override
    public int getItemCount() {
        return pantryItems.size();
    }

    // Holds references to the views used by one pantry item
    public static class PantryViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvIngredientName;
        TextView tvIngredientQuantity;
        TextView tvIngredientExpiry;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIngredientName =
                    itemView.findViewById(R.id.tvIngredientName);

            tvIngredientQuantity =
                    itemView.findViewById(R.id.tvIngredientQuantity);

            tvIngredientExpiry =
                    itemView.findViewById(R.id.tvIngredientExpiry);
        }
    }
}