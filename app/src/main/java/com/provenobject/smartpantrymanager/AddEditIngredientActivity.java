package com.provenobject.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.provenobject.smartpantrymanager.data.DatabaseHelper;
import com.provenobject.smartpantrymanager.model.PantryItem;

// Activity collects ingredient information from the user,
// validates the input and saves the pantry item to the local SQLite database

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText etIngredientName;
    private EditText etQuantity;
    private Spinner spUnit;
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
        spUnit = findViewById(R.id.spUnit);
        etExpiryDate = findViewById(R.id.etExpiryDate);

        btnSaveIngredient = findViewById(R.id.btnSaveIngredient);

        // Create the database helper used to store pantry items
        databaseHelper = new DatabaseHelper(this);

        String[] units = {
                "g",
                "kg",
                "ml",
                "l",
                "item",
                "slice"
        };

        ArrayAdapter<String> unitAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        units
                );

        unitAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spUnit.setAdapter(unitAdapter);

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

                int unitPosition = unitAdapter.getPosition(item.getUnit());

                if (unitPosition >= 0) {
                    spUnit.setSelection(unitPosition);
                }

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
        String unit = spUnit.getSelectedItem().toString();
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
        if (editingItemId == -1) {

            // No existing ID means we are adding a new ingredient.
            long id = databaseHelper.addPantryItem(item);

            if (id != -1) {
                Toast.makeText(
                        this,
                        "Ingredient added successfully",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            } else {
                Toast.makeText(
                        this,
                        "Failed to save ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } else {

            // Existing ID means we are updating an existing ingredient.
            item.setId(editingItemId);

            int rowsUpdated =
                    databaseHelper.updatePantryItem(item);

            if (rowsUpdated > 0) {
                Toast.makeText(
                        this,
                        "Ingredient updated successfully",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            } else {
                Toast.makeText(
                        this,
                        "Failed to update ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}