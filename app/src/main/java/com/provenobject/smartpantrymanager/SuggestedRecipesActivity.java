package com.provenobject.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.provenobject.smartpantrymanager.adapter.RecipeAdapter;
import com.provenobject.smartpantrymanager.data.DatabaseHelper;
import com.provenobject.smartpantrymanager.model.PantryItem;
import com.provenobject.smartpantrymanager.model.Recipe;
import com.provenobject.smartpantrymanager.model.RecipeIngredient;
import com.provenobject.smartpantrymanager.util.RecipeMatcher;

import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerRecipes;

    private DatabaseHelper databaseHelper;
    private RecipeMatcher recipeMatcher;
    private RecipeAdapter recipeAdapter;

    private TextView tvNoRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        recyclerRecipes = findViewById(R.id.recyclerRecipes);
        tvNoRecipes = findViewById(R.id.tvNoRecipes);

        databaseHelper = new DatabaseHelper(this);
        recipeMatcher = new RecipeMatcher();

        recyclerRecipes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {

        List<Recipe> allRecipes =
                databaseHelper.getAllRecipes();

        List<PantryItem> pantryItems =
                databaseHelper.getAllPantryItems();

        List<Recipe> suggestedRecipes =
                new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            List<RecipeIngredient> requiredIngredients =
                    databaseHelper.getRecipeIngredients(recipe.getId());

            boolean canMake =
                    recipeMatcher.canMakeRecipe(
                            recipe,
                            requiredIngredients,
                            pantryItems
                    );

            if (canMake) {
                suggestedRecipes.add(recipe);
            }
        }
        if (suggestedRecipes.isEmpty()) {
            recyclerRecipes.setVisibility(View.GONE);
            tvNoRecipes.setVisibility(View.VISIBLE);
        } else {
            recyclerRecipes.setVisibility(View.VISIBLE);
            tvNoRecipes.setVisibility(View.GONE);
        }

        recipeAdapter = new RecipeAdapter(
                suggestedRecipes,
                recipe -> {
                    Intent intent = new Intent(
                            SuggestedRecipesActivity.this,
                            RecipeDetailActivity.class
                    );

                    intent.putExtra("recipe_id", recipe.getId());

                    startActivity(intent);
                }
        );

        recyclerRecipes.setAdapter(recipeAdapter);
    }
}