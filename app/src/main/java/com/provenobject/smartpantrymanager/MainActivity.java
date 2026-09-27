package com.provenobject.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.provenobject.smartpantrymanager.adapter.PantryAdapter;
import com.provenobject.smartpantrymanager.data.DatabaseHelper;
import com.provenobject.smartpantrymanager.model.PantryItem;

import java.util.List;

// Main screen of the Smart Pantry Manager application
// Provides access to the user's pantry and allows them to add new ingredients
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPantry;
    private PantryAdapter pantryAdapter;
    private DatabaseHelper databaseHelper;
    private TextView tvEmptyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPantry = findViewById(R.id.recyclerPantry);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);

        databaseHelper = new DatabaseHelper(this);

        recyclerPantry.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Connect the Add Ingredient button to the layout.
        Button btnAddIngredient = findViewById(R.id.btnAddIngredient);

        // Open the Add/Edit Ingredient screen when clicked.
        btnAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddEditIngredientActivity.class
            );

            startActivity(intent);
        });

        loadPantryItems();
    }

    private void loadPantryItems() {

        List<PantryItem> pantryItems =
                databaseHelper.getAllPantryItems();

        if (pantryItems.isEmpty()) {

            tvEmptyMessage.setVisibility(View.VISIBLE);
            recyclerPantry.setVisibility(View.GONE);

        } else {

            tvEmptyMessage.setVisibility(View.GONE);
            recyclerPantry.setVisibility(View.VISIBLE);

            pantryAdapter = new PantryAdapter(pantryItems);
            recyclerPantry.setAdapter(pantryAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryItems();
    }
}