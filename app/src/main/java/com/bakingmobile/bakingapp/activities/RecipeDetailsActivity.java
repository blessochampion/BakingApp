package com.bakingmobile.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.models.Ingredient;
import com.bakingmobile.bakingapp.models.Recipe;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    public static final String KEY_RECIPE = "recipe";
    Recipe mRecipe;
    private TextView mIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //get references to views
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mIngredients = (TextView) findViewById(R.id.tv_ingredients);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra(KEY_RECIPE)){

            mRecipe = intentThatStartedThisActivity.getParcelableExtra(KEY_RECIPE);
            getSupportActionBar().setTitle(mRecipe.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            showIngredients();

        }else{
            finish();
        }




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return  true;
        }
        return  super.onOptionsItemSelected(item);
    }

    private void showIngredients() {

        ArrayList<Ingredient> ingredients = mRecipe.getIngredients();
        String formattedIngredient;
        int quantity;
        String measure;
        String ingredientDetails;

        for (Ingredient ingredient : ingredients){
            formattedIngredient  = getString(R.string.bullet);
            quantity = ingredient.getQuantity();
            measure = ingredient.getMeasure();
            ingredientDetails = ingredient.getIngredient();
            formattedIngredient += " " + ingredientDetails + " ("+ quantity +" " + measure + ")";
            mIngredients.append(formattedIngredient+ "\n\n");

        }


    }

}
