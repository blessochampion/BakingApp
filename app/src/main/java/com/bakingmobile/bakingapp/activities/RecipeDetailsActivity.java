package com.bakingmobile.bakingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.adapters.RecipeListDetailsAdapter;
import com.bakingmobile.bakingapp.models.Ingredient;
import com.bakingmobile.bakingapp.models.Recipe;
import com.bakingmobile.bakingapp.models.Step;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeListDetailsAdapter.RecipeStepItemTouchListener {

    public static final String KEY_RECIPE = "recipe";
    Recipe mRecipe;

    private RecyclerView mRecipeListSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        mRecipeListSteps = (RecyclerView) findViewById(R.id.rv_recipe_list_steps);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecipeListSteps.setLayoutManager(linearLayoutManager);


        //get references to views
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecipeListSteps = (RecyclerView) findViewById(R.id.rv_recipe_list_steps);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_RECIPE)) {
            mRecipe = savedInstanceState.getParcelable(KEY_RECIPE);
        } else {

            Intent intentThatStartedThisActivity = getIntent();
            if (intentThatStartedThisActivity.hasExtra(KEY_RECIPE)) {
                mRecipe = intentThatStartedThisActivity.getParcelableExtra(KEY_RECIPE);

            } else {
                finish();
            }
        }

        getSupportActionBar().setTitle(mRecipe.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        showRecipeDetails();

    }

    private void showRecipeDetails() {
        ArrayList<Ingredient> ingredients = mRecipe.getIngredients();
        ArrayList<Step> steps =  mRecipe.getSteps();
        Context appContext = getApplicationContext();

        RecipeListDetailsAdapter recipeStepsAdapter = new RecipeListDetailsAdapter(appContext, ingredients, steps);
        recipeStepsAdapter.setRecipeStepOnTouchListener(this);
        mRecipeListSteps.setAdapter(recipeStepsAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRecipe != null) {
            outState.putParcelable(KEY_RECIPE, mRecipe);
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


    @Override
    public void onRecipeStepItemTouched(Step touchedRecipeStep) {
        Intent intent = new Intent(RecipeDetailsActivity.this, RecipeStepsActivity.class);
        intent.putExtra(RecipeStepsActivity.KEY_STEPS, mRecipe.getSteps());
        startActivity(intent);
    }
}
