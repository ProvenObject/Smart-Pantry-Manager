package com.provenobject.smartpantrymanager.data;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.provenobject.smartpantrymanager.model.PantryItem;
import com.provenobject.smartpantrymanager.model.Recipe;
import com.provenobject.smartpantrymanager.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

// Manages the local SQLite database used by Smart Pantry Manager
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 4;

    // Pantry table
    public static final String TABLE_PANTRY = "pantry_items";

    // Pantry columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_EXPIRY_DATE = "expiry_date";

    // Recipe table
    public static final String TABLE_RECIPES = "recipes";
    public static final String RECIPE_ID = "id";
    public static final String RECIPE_NAME = "name";
    public static final String RECIPE_STEPS = "preparation_steps";

    // Recipe ingredient table
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String RECIPE_INGREDIENT_ID = "id";
    public static final String RECIPE_INGREDIENT_RECIPE_ID = "recipe_id";
    public static final String RECIPE_INGREDIENT_NAME = "ingredient_name";
    public static final String RECIPE_INGREDIENT_QUANTITY = "quantity";
    public static final String RECIPE_INGREDIENT_UNIT = "unit";

    // Creates the pantry table when the database is first created
    private static final String CREATE_PANTRY_TABLE = "CREATE TABLE " + TABLE_PANTRY + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_QUANTITY + " REAL NOT NULL, " +
            COLUMN_UNIT + " TEXT NOT NULL, " +
            COLUMN_EXPIRY_DATE + " TEXT" +
            ")";

    private static final String CREATE_RECIPE_TABLE =
            "CREATE TABLE " + TABLE_RECIPES + " (" +
                    RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RECIPE_NAME + " TEXT NOT NULL, " +
                    RECIPE_STEPS + " TEXT NOT NULL" +
                    ")";

    private static final String CREATE_RECIPE_INGREDIENT_TABLE =
            "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                    RECIPE_INGREDIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RECIPE_INGREDIENT_RECIPE_ID + " INTEGER NOT NULL, " +
                    RECIPE_INGREDIENT_NAME + " TEXT NOT NULL, " +
                    RECIPE_INGREDIENT_QUANTITY + " REAL NOT NULL, " +
                    RECIPE_INGREDIENT_UNIT + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + RECIPE_INGREDIENT_RECIPE_ID + ") " +
                    "REFERENCES " + TABLE_RECIPES + "(" + RECIPE_ID + ")" +
                    ")";

    private void seedRecipes(SQLiteDatabase db) {

        addSeedRecipe(
                db,
                "Pancakes",
                "Mix flour, milk, eggs and sugar into a batter. Cook portions in a heated pan until golden on both sides.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "flour", 200, "g"),
                        new RecipeIngredient(0, "milk", 250, "ml"),
                        new RecipeIngredient(0, "eggs", 2, "items"),
                        new RecipeIngredient(0, "sugar", 20, "g")
                }
        );

        addSeedRecipe(
                db,
                "Scrambled Eggs",
                "Beat the eggs and cook them gently in a heated pan until set.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "eggs", 2, "items"),
                        new RecipeIngredient(0, "milk", 30, "ml"),
                        new RecipeIngredient(0, "butter", 10, "g")
                }
        );

        addSeedRecipe(
                db,
                "Tomato Rice",
                "Cook the rice and combine it with cooked tomatoes and onion.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "rice", 200, "g"),
                        new RecipeIngredient(0, "tomato", 2, "items"),
                        new RecipeIngredient(0, "onion", 1, "items")
                }
        );

        addSeedRecipe(
                db,
                "Chicken Rice",
                "Cook the chicken thoroughly and combine it with cooked rice and onion.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "chicken", 300, "g"),
                        new RecipeIngredient(0, "rice", 200, "g"),
                        new RecipeIngredient(0, "onion", 1, "items")
                }
        );

        addSeedRecipe(
                db,
                "Chicken Sandwich",
                "Cook the chicken and place it between slices of bread with tomato.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "chicken", 150, "g"),
                        new RecipeIngredient(0, "bread", 2, "slices"),
                        new RecipeIngredient(0, "tomato", 1, "items")
                }
        );

        addSeedRecipe(
                db,
                "French Toast",
                "Beat the eggs with milk and sugar. Dip the bread into the mixture and cook in a heated pan until golden.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "bread", 2, "slices"),
                        new RecipeIngredient(0, "eggs", 2, "items"),
                        new RecipeIngredient(0, "milk", 100, "ml"),
                        new RecipeIngredient(0, "sugar", 15, "g")
                }
        );

        addSeedRecipe(
                db,
                "Omelette",
                "Beat the eggs and cook them in a heated pan until set.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "eggs", 3, "items"),
                        new RecipeIngredient(0, "milk", 30, "ml"),
                        new RecipeIngredient(0, "butter", 10, "g")
                }
        );

        addSeedRecipe(
                db,
                "Egg Fried Rice",
                "Cook the eggs and rice together in a heated pan and stir until well combined.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "rice", 200, "g"),
                        new RecipeIngredient(0, "eggs", 2, "items"),
                        new RecipeIngredient(0, "onion", 1, "items")
                }
        );

        addSeedRecipe(
                db,
                "Tomato Pasta",
                "Cook the pasta and combine it with cooked tomatoes and onion.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "pasta", 200, "g"),
                        new RecipeIngredient(0, "tomato", 2, "items"),
                        new RecipeIngredient(0, "onion", 1, "items")
                }
        );

        addSeedRecipe(
                db,
                "Chicken Pasta",
                "Cook the pasta and chicken thoroughly, then combine them.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "pasta", 200, "g"),
                        new RecipeIngredient(0, "chicken", 200, "g"),
                        new RecipeIngredient(0, "onion", 1, "items")
                }
        );

        addSeedRecipe(
                db,
                "Grilled Cheese Sandwich",
                "Place cheese between slices of bread and cook in a heated pan until the bread is golden and the cheese melts.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "bread", 2, "slices"),
                        new RecipeIngredient(0, "cheese", 50, "g"),
                        new RecipeIngredient(0, "butter", 10, "g")
                }
        );

        addSeedRecipe(
                db,
                "Chicken Wrap",
                "Cook the chicken thoroughly and wrap it with tomato and onion in a tortilla.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "chicken", 150, "g"),
                        new RecipeIngredient(0, "tortilla", 1, "item"),
                        new RecipeIngredient(0, "tomato", 1, "items"),
                        new RecipeIngredient(0, "onion", 1, "items")
                }
        );

        addSeedRecipe(
                db,
                "Rice and Eggs",
                "Cook the rice and eggs separately, then combine them.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "rice", 200, "g"),
                        new RecipeIngredient(0, "eggs", 2, "items")
                }
        );

        addSeedRecipe(
                db,
                "Chicken and Tomato Rice",
                "Cook the chicken and rice thoroughly, then combine them with tomato.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "chicken", 200, "g"),
                        new RecipeIngredient(0, "rice", 200, "g"),
                        new RecipeIngredient(0, "tomato", 2, "items")
                }
        );

        addSeedRecipe(
                db,
                "Tomato Omelette",
                "Beat the eggs and cook them with tomato in a heated pan.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "eggs", 3, "items"),
                        new RecipeIngredient(0, "tomato", 1, "items"),
                        new RecipeIngredient(0, "butter", 10, "g")
                }
        );

        addSeedRecipe(
                db,
                "Egg Sandwich",
                "Cook the eggs and place them between slices of bread.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "eggs", 2, "items"),
                        new RecipeIngredient(0, "bread", 2, "slices")
                }
        );

        addSeedRecipe(
                db,
                "Simple Pasta",
                "Cook the pasta in boiling water until tender.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "pasta", 200, "g")
                }
        );

        addSeedRecipe(
                db,
                "Chicken Fried Rice",
                "Cook the chicken, rice and eggs together in a heated pan and stir until well combined.",
                new RecipeIngredient[]{
                        new RecipeIngredient(0, "chicken", 150, "g"),
                        new RecipeIngredient(0, "rice", 200, "g"),
                        new RecipeIngredient(0, "eggs", 2, "items"),
                        new RecipeIngredient(0, "onion", 1, "items")
                }
        );
    }

    private void addSeedRecipe(
            SQLiteDatabase db,
            String name,
            String preparationSteps,
            RecipeIngredient[] ingredients
    ) {

        Cursor cursor = db.query(
                TABLE_RECIPES,
                new String[]{RECIPE_ID},
                RECIPE_NAME + " = ?",
                new String[]{name},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            cursor.close();
            return;
        }

        cursor.close();

        Recipe recipe = new Recipe(
                name,
                preparationSteps
        );

        long recipeId = addRecipe(db, recipe);

        if (recipeId == -1) {
            return;
        }

        for (RecipeIngredient ingredient : ingredients) {

            RecipeIngredient recipeIngredient =
                    new RecipeIngredient(
                            (int) recipeId,
                            ingredient.getIngredientName(),
                            ingredient.getQuantity(),
                            ingredient.getUnit()
                    );

            addRecipeIngredient(db, recipeIngredient);
        }
    }

    // Creates a connection to the local pantry database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PANTRY_TABLE);
        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENT_TABLE);

        seedRecipes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {
            db.execSQL(CREATE_RECIPE_TABLE);
            db.execSQL(CREATE_RECIPE_INGREDIENT_TABLE);
            seedRecipes(db);
        }

        if (oldVersion < 3) {
            seedRecipes(db);
        }

        if (oldVersion < 4) {
            seedRecipes(db);
        }
    }

    // Adds a new pantry item to the database

    public long addPantryItem(PantryItem item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY_DATE, item.getExpiryDate());

        long id = db.insert(TABLE_PANTRY, null, values);

        db.close();

        return id;
    }

    // Retrieves all pantry items in the database (list)

    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> pantryItems = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PANTRY,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME + " ASC",
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_UNIT));
            String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPIRY_DATE));

            PantryItem item = new PantryItem(
                    id,
                    name,
                    quantity,
                    unit,
                    expiryDate
            );

            pantryItems.add(item);
        }

        cursor.close();
        db.close();

        return pantryItems;
    }

    public PantryItem getPantryItemById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PANTRY,
                null,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null
        );

        PantryItem item = null;

        if (cursor.moveToFirst()) {

            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_NAME)
                    );

            double quantity =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)
                    );

            String unit =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_UNIT)
                    );

            String expiryDate =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(COLUMN_EXPIRY_DATE)
                    );

            item = new PantryItem(
                    id,
                    name,
                    quantity,
                    unit,
                    expiryDate
            );
        }

        cursor.close();
        db.close();

        return item;
    }

    public long addRecipe(Recipe recipe) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(RECIPE_NAME, recipe.getName());
        values.put(RECIPE_STEPS, recipe.getPreparationSteps());

        long id = db.insert(
                TABLE_RECIPES,
                null,
                values
        );

        db.close();

        return id;
    }
    private long addRecipe(
            SQLiteDatabase db,
            Recipe recipe
    ) {
        ContentValues values = new ContentValues();

        values.put(RECIPE_NAME, recipe.getName());
        values.put(RECIPE_STEPS, recipe.getPreparationSteps());

        return db.insert(
                TABLE_RECIPES,
                null,
                values
        );
    }

    public long addRecipeIngredient(RecipeIngredient ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(
                RECIPE_INGREDIENT_RECIPE_ID,
                ingredient.getRecipeId()
        );

        values.put(
                RECIPE_INGREDIENT_NAME,
                ingredient.getIngredientName()
        );

        values.put(
                RECIPE_INGREDIENT_QUANTITY,
                ingredient.getQuantity()
        );

        values.put(
                RECIPE_INGREDIENT_UNIT,
                ingredient.getUnit()
        );

        long id = db.insert(
                TABLE_RECIPE_INGREDIENTS,
                null,
                values
        );

        db.close();

        return id;
    }
    private long addRecipeIngredient(
            SQLiteDatabase db,
            RecipeIngredient ingredient
    ) {
        ContentValues values = new ContentValues();

        values.put(
                RECIPE_INGREDIENT_RECIPE_ID,
                ingredient.getRecipeId()
        );

        values.put(
                RECIPE_INGREDIENT_NAME,
                ingredient.getIngredientName()
        );

        values.put(
                RECIPE_INGREDIENT_QUANTITY,
                ingredient.getQuantity()
        );

        values.put(
                RECIPE_INGREDIENT_UNIT,
                ingredient.getUnit()
        );

        return db.insert(
                TABLE_RECIPE_INGREDIENTS,
                null,
                values
        );
    }

    public int getRecipeCount() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_RECIPES,
                null
        );

        int count = 0;

        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        db.close();

        return count;
    }

    // Updates an existing pantry item in the database
    public int updatePantryItem(PantryItem item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY_DATE, item.getExpiryDate());

        int rowsUpdated = db.update(
                TABLE_PANTRY,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(item.getId())}
        );

        db.close();

        return rowsUpdated;
    }

    // Deletes a pantry item from the database
    public int deletePantryItem(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_PANTRY,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return rowsDeleted;
    }

    public List<Recipe> getAllRecipes() {

        List<Recipe> recipes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_RECIPES,
                null,
                null,
                null,
                null,
                null,
                RECIPE_NAME + " ASC"
        );

        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(RECIPE_ID)
                    );

            String name =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(RECIPE_NAME)
                    );

            String preparationSteps =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(RECIPE_STEPS)
                    );

            Recipe recipe = new Recipe(
                    id,
                    name,
                    preparationSteps
            );

            recipes.add(recipe);
        }

        cursor.close();
        db.close();

        return recipes;
    }

    public List<RecipeIngredient> getRecipeIngredients(int recipeId) {

        List<RecipeIngredient> ingredients = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_RECIPE_INGREDIENTS,
                null,
                RECIPE_INGREDIENT_RECIPE_ID + " = ?",
                new String[]{String.valueOf(recipeId)},
                null,
                null,
                RECIPE_INGREDIENT_NAME + " ASC"
        );

        while (cursor.moveToNext()) {

            int id =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    RECIPE_INGREDIENT_ID
                            )
                    );

            String ingredientName =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    RECIPE_INGREDIENT_NAME
                            )
                    );

            double quantity =
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    RECIPE_INGREDIENT_QUANTITY
                            )
                    );

            String unit =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    RECIPE_INGREDIENT_UNIT
                            )
                    );

            RecipeIngredient ingredient =
                    new RecipeIngredient(
                            id,
                            recipeId,
                            ingredientName,
                            quantity,
                            unit
                    );

            ingredients.add(ingredient);
        }

        cursor.close();
        db.close();

        return ingredients;
    }
}
