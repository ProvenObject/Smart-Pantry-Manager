package com.provenobject.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.provenobject.smartpantrymanager.data.DatabaseHelper;
import com.provenobject.smartpantrymanager.model.Recipe;
import com.provenobject.smartpantrymanager.model.RecipeIngredient;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView tvRecipeName = findViewById(R.id.tvRecipeName);
        TextView tvRecipeIngredients = findViewById(R.id.tvRecipeIngredients);
        TextView tvRecipeSteps = findViewById(R.id.tvRecipeSteps);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        int recipeId = getIntent().getIntExtra("recipe_id", -1);

        if (recipeId == -1) {
            finish();
            return;
        }

        Recipe recipe = databaseHelper.getRecipeById(recipeId);

        if (recipe == null) {
            finish();
            return;
        }

        tvRecipeName.setText(recipe.getName());
        tvRecipeSteps.setText(recipe.getPreparationSteps());

        List<RecipeIngredient> ingredients =
                databaseHelper.getRecipeIngredients(recipeId);

        StringBuilder ingredientText = new StringBuilder();

        for (RecipeIngredient ingredient : ingredients) {

            ingredientText.append("• ")
                    .append(ingredient.getIngredientName())
                    .append(" — ")
                    .append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append("\n");
        }

        tvRecipeIngredients.setText(ingredientText.toString());
    }
}