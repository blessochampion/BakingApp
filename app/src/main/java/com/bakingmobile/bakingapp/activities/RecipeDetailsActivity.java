package com.bakingmobile.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.fragments.RecipeDetailsFragment;
import com.bakingmobile.bakingapp.fragments.RecipeStepFragment;
import com.bakingmobile.bakingapp.models.Recipe;
import com.bakingmobile.bakingapp.models.Step;

public class RecipeDetailsActivity extends AppCompatActivity{

    public static final String KEY_RECIPE = "recipe";
    public static final String KEY_POSITION = "position";
    Recipe mRecipe;

    private int mSelectedStepPosition = 0;
    private boolean isTablet = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //get references to views
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_RECIPE)) {
            mRecipe = savedInstanceState.getParcelable(KEY_RECIPE);
            mSelectedStepPosition = savedInstanceState.getInt(KEY_POSITION);
        } else {

            Intent intentThatStartedThisActivity = getIntent();
            if (intentThatStartedThisActivity.hasExtra(KEY_RECIPE)) {
                mRecipe = intentThatStartedThisActivity.getParcelableExtra(KEY_RECIPE);
                showRecipeDetailsFragment();

            } else {
                finish();
            }

        }

        getSupportActionBar().setTitle(mRecipe.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        showRecipeSteps();

    }



    private void showRecipeSteps() {

        RecipeDetailsFragment recipeDetailsFragment = showRecipeDetailsFragment();

        isTablet = findViewById(R.id.fl_recipe_details_container_step) != null;

        if (isTablet) {
            showStepFragment();

            recipeDetailsFragment.setListener(new RecipeDetailsFragment.FragmentDetailsStepSelectedListener() {
                @Override
                public void recipeStepSelected(int position) {
                    mSelectedStepPosition = position;
                    showStepFragment();

                }
            });

        }

    }

    private RecipeDetailsFragment showRecipeDetailsFragment() {
        RecipeDetailsFragment recipeDetailsFragment = RecipeDetailsFragment.getNewInstance(mRecipe);
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fl_recipe_details_container,
                recipeDetailsFragment,
                null
        ).commit();
        return recipeDetailsFragment;
    }

    private void showStepFragment() {
        Step currentStep = mRecipe.getSteps().get(mSelectedStepPosition);
        RecipeStepFragment fragment = RecipeStepFragment.getNewInstance(currentStep);

        getSupportFragmentManager().beginTransaction().replace(
                R.id.fl_recipe_details_container_step,
                fragment
                ,
                null
        ).commit();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRecipe != null) {
            outState.putParcelable(KEY_RECIPE, mRecipe);
            outState.putInt(KEY_POSITION, mSelectedStepPosition);
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
