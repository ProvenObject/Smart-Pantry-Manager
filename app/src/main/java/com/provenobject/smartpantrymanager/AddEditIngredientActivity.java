package com.provenobject.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.provenobject.smartpantrymanager.data.DatabaseHelper;
import com.provenobject.smartpantrymanager.model.PantryItem;

// Activity collects ingredient information from the user,
// validates the input and saves the pantry item to the local SQLite database

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText etIngredientName;
    private EditText etQuantity;
    private EditText etUnit;
    private EditText etExpiryDate;

    private Button btnSaveIngredient;

    private DatabaseHelper databaseHelper;

    private int editingItemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        // Connect Java variables to the views in the XML layout
        etIngredientName = findViewById(R.id.etIngredientName);
        etQuantity = findViewById(R.id.etQuantity);
        etUnit = findViewById(R.id.etUnit);
        etExpiryDate = findViewById(R.id.etExpiryDate);

        btnSaveIngredient = findViewById(R.id.btnSaveIngredient);

        // Create the database helper used to store pantry items
        databaseHelper = new DatabaseHelper(this);

        editingItemId = getIntent().getIntExtra("pantry_item_id", -1);

        if (editingItemId != -1) {
            TextView tvFormTitle = findViewById(R.id.tvFormTitle);
            tvFormTitle.setText("Edit Ingredient");
        }

        if (editingItemId != -1) {

            PantryItem item =
                    databaseHelper.getPantryItemById(editingItemId);

            if (item != null) {

                etIngredientName.setText(item.getName());

                etQuantity.setText(
                        String.valueOf(item.getQuantity())
                );

                etUnit.setText(item.getUnit());

                if (item.getExpiryDate() != null) {
                    etExpiryDate.setText(item.getExpiryDate());
                }
            }
        }

        // Save the ingredient when the user presses the button
        btnSaveIngredient.setOnClickListener(v -> saveIngredient());
    }

    // Validates the ingredient form and saves the pantry item to the local SQLite database

    private void saveIngredient() {

        String name = etIngredientName.getText().toString().trim();
        String quantityText = etQuantity.getText().toString().trim();
        String unit = etUnit.getText().toString().trim();
        String expiryDate = etExpiryDate.getText().toString().trim();

        // Validate the ingredient name
        if (name.isEmpty()) {
            etIngredientName.setError("Enter an ingredient name");
            etIngredientName.requestFocus();
            return;
        }

        // Validate that a quantity was entered
        if (quantityText.isEmpty()) {
            etQuantity.setError("Enter a quantity");
            etQuantity.requestFocus();
            return;
        }

        // Validate the unit
        if (unit.isEmpty()) {
            etUnit.setError("Enter a unit");
            etUnit.requestFocus();
            return;
        }

        double quantity;

        // Convert the quantity from text to a number
        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException e) {
            etQuantity.setError("Enter a valid quantity");
            etQuantity.requestFocus();
            return;
        }

        // Prevent zero or negative quantities
        if (quantity <= 0) {
            etQuantity.setError("Quantity must be greater than zero");
            etQuantity.requestFocus();
            return;
        }

        // Create a PantryItem object from the form data
        PantryItem item = new PantryItem(
                name,
                quantity,
                unit,
                expiryDate.isEmpty() ? null : expiryDate
        );

        // Save the pantry item to SQLite
        long id = databaseHelper.addPantryItem(item);

        if (id != -1) {
            Toast.makeText(
                    this,
                    "Ingredient saved",
                    Toast.LENGTH_SHORT
            ).show();

            // Close this activity and return to the previous screen
            finish();

        } else {
            Toast.makeText(
                    this,
                    "Failed to save ingredient",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}