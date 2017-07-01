package com.bakingmobile.bakingapp.utils;

import android.util.Log;

import com.bakingmobile.bakingapp.models.Ingredient;
import com.bakingmobile.bakingapp.models.Recipe;
import com.bakingmobile.bakingapp.models.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blessochampion on 7/1/17.
 */

public class RecipeParser {
    private static String KEY_ID = "id";
    private static String KEY_NAME = "name";
    private static String KEY_INGREDIENT = "ingredients";
    private static String KEY_STEPS = "steps";
    private static String KEY_SERVINGS = "servings";
    private static String KEY_IMAGE = "image";

    //key for ingredients
    private static String KEY_INGREDIENTS_QUANTITY = "quantity";
    private static String KEY_INGREDIENTS_MEASURE = "measure";
    private static String KEY_INGREDIENTS_INGREDIENT = "ingredient";

    //key for steps
    private static String KEY_STEPS_ID = "id";
    private static String KEY_STEPS_SHORT_DESCRIPTION = "shortDescription";
    private static String KEY_STEPS_DESCRIPTION = "description";
    private static String KEY_STEPS_VIDEO_URL = "videoURL";
    private static String KEY_STEPS_THUMBNAIL_URL = "thumbnailURL";

    private static String LOG_TAG = RecipeParser.class.getSimpleName();

    public static ArrayList<Recipe> parseRecipe(JSONArray jsonArray) {
        JSONObject recipeJSON;
        ArrayList<Recipe> recipeList = new ArrayList<>();


        int id;
        String name;
        ArrayList<Ingredient> ingredients;
        ArrayList<Step> steps;
        int servings;
        String imageURL;
        Recipe recipe;

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                recipeJSON = jsonArray.getJSONObject(i);
                id = recipeJSON.getInt(KEY_ID);
                name = recipeJSON.getString(KEY_NAME);
                ingredients = parseIngredient(recipeJSON.getJSONArray(KEY_INGREDIENT));
                steps = parseStep(recipeJSON.getJSONArray(KEY_STEPS));
                servings = recipeJSON.getInt(KEY_SERVINGS);
                imageURL = recipeJSON.getString(KEY_IMAGE);
                recipe = new Recipe(id, name, ingredients, steps, servings, imageURL);
                recipeList.add(recipe);

            } catch (JSONException jsonException) {
                Log.e(LOG_TAG, jsonException.getMessage());
            }
        }

        return  recipeList;

    }

    private static ArrayList<Ingredient> parseIngredient(JSONArray ingredientListJSON) throws JSONException {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient recipeIngredient;
        JSONObject ingredientJSON;

        int quantity;
        String measure;
        String ingredient;

        for (int i = 0; i < ingredientListJSON.length(); i++) {

            ingredientJSON = ingredientListJSON.getJSONObject(i);
            quantity = ingredientJSON.getInt(KEY_INGREDIENTS_QUANTITY);
            measure = ingredientJSON.getString(KEY_INGREDIENTS_MEASURE);
            ingredient = ingredientJSON.getString(KEY_INGREDIENTS_INGREDIENT);
            recipeIngredient = new Ingredient(quantity, measure, ingredient);
            ingredients.add(recipeIngredient);

        }

        return ingredients;

    }

    private static ArrayList<Step> parseStep(JSONArray stepListJSON) throws JSONException {

        ArrayList<Step> steps = new ArrayList<>();
        Step recipeStep;
        JSONObject stepJSON;

        int id;
        String shortDescription;
        String description;
        String videoURL;
        String thumbnailURL;

        for(int i = 0 ; i < stepListJSON.length(); i++){
            stepJSON = stepListJSON.getJSONObject(i);
            id = stepJSON.getInt(KEY_STEPS_ID);
            shortDescription = stepJSON.getString(KEY_STEPS_SHORT_DESCRIPTION);
            description = stepJSON.getString(KEY_STEPS_DESCRIPTION);
            videoURL = stepJSON.getString(KEY_STEPS_VIDEO_URL);
            thumbnailURL = stepJSON.getString(KEY_STEPS_THUMBNAIL_URL);

            recipeStep = new Step(id, shortDescription, description, videoURL, thumbnailURL);
            steps.add(recipeStep);

        }

        return  steps;
    }
}
