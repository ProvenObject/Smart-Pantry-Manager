package com.provenobject.smartpantrymanager.util;

import com.provenobject.smartpantrymanager.model.PantryItem;
import com.provenobject.smartpantrymanager.model.Recipe;
import com.provenobject.smartpantrymanager.model.RecipeIngredient;

import java.util.List;

// Contains the business logic used to determine whether
// a recipe can be prepared from the user's pantry.
public class RecipeMatcher {

    public boolean canMakeRecipe(
            Recipe recipe,
            List<RecipeIngredient> requiredIngredients,
            List<PantryItem> pantryItems
    ) {
        for (RecipeIngredient requiredIngredient : requiredIngredients) {

            String requiredName =
                    normalizeIngredientName(requiredIngredient.getIngredientName());

            double requiredQuantity =
                    convertToBaseUnit(
                            requiredIngredient.getQuantity(),
                            requiredIngredient.getUnit()
                    );

            double availableQuantity = 0;

            for (PantryItem pantryItem : pantryItems) {

                String pantryName =
                        normalizeIngredientName(pantryItem.getName());

                if (requiredName.equals(pantryName)
                        && isCompatibleUnit(
                        pantryItem.getUnit(),
                        requiredIngredient.getUnit()
                )) {

                    availableQuantity += convertToBaseUnit(
                            pantryItem.getQuantity(),
                            pantryItem.getUnit()
                    );
                }
            }

            if (availableQuantity < requiredQuantity) {
                return false;
            }
        }

        return true;
    }

    private String normalizeIngredientName(String name) {
        String normalized = name.trim().toLowerCase();

        if (normalized.endsWith("ies")) {
            return normalized.substring(0, normalized.length() - 3) + "y";
        }

        if (normalized.endsWith("oes")) {
            return normalized.substring(0, normalized.length() - 2);
        }

        if (normalized.endsWith("s") && !normalized.endsWith("ss")) {
            return normalized.substring(0, normalized.length() - 1);
        }

        return normalized;
    }

    private double convertToBaseUnit(
            double quantity,
            String unit
    ) {

        String normalizedUnit =
                unit.trim().toLowerCase();

        switch (normalizedUnit) {

            case "kg":
                return quantity * 1000;

            case "g":
                return quantity;

            case "l":
                return quantity * 1000;

            case "ml":
                return quantity;

            case "items":
            case "item":
            case "slices":
            case "slice":
                return quantity;

            default:
                return quantity;
        }
    }

    private boolean isCompatibleUnit(
            String pantryUnit,
            String requiredUnit
    ) {

        String pantry = pantryUnit.trim().toLowerCase();
        String required = requiredUnit.trim().toLowerCase();

        boolean pantryWeight =
                pantry.equals("g") || pantry.equals("kg");

        boolean requiredWeight =
                required.equals("g") || required.equals("kg");

        if (pantryWeight && requiredWeight) {
            return true;
        }

        boolean pantryVolume =
                pantry.equals("ml") || pantry.equals("l");

        boolean requiredVolume =
                required.equals("ml") || required.equals("l");

        if (pantryVolume && requiredVolume) {
            return true;
        }
        if ((pantry.equals("item") || pantry.equals("items"))
                && (required.equals("item") || required.equals("items"))) {
            return true;
        }

        if ((pantry.equals("slice") || pantry.equals("slices"))
                && (required.equals("slice") || required.equals("slices"))) {
            return true;
        }

        return pantry.equals(required);
    }
}