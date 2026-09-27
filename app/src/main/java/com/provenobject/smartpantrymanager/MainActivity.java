package com.provenobject.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main screen of the Smart Pantry Manager application.
 *
 * Provides access to the user's pantry and allows them
 * to add new ingredients.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}