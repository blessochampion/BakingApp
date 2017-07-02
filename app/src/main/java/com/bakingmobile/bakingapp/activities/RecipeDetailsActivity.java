package com.bakingmobile.bakingapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.adapters.RecipeListDetailsAdapter;
import com.bakingmobile.bakingapp.fragments.RecipeDetailsFragment;
import com.bakingmobile.bakingapp.models.Ingredient;
import com.bakingmobile.bakingapp.models.Recipe;
import com.bakingmobile.bakingapp.models.Step;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity{

    public static final String KEY_RECIPE = "recipe";
    Recipe mRecipe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //get references to views
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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

        showRecipeIngredientAndSteps();

    }

    private void showRecipeIngredientAndSteps() {

        getSupportFragmentManager().beginTransaction().replace(
                R.id.fl_recipe_details_container,
                RecipeDetailsFragment.getNewInstance(mRecipe),
                null
        ).commit();

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


}
