package com.bakingmobile.bakingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.adapters.RecipeStepsFragmentAdapter;
import com.bakingmobile.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    ArrayList<Step> mSteps;
    int position = 0;

    public static final String KEY_STEPS = "steps";
    public static final String KEY_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent.hasExtra(KEY_STEPS)){
            mSteps = intent.getParcelableArrayListExtra(KEY_STEPS);
            position = intent.getIntExtra(KEY_POSITION, 0);

        }else {
            finish();
        }

        mTabLayout = (TabLayout) findViewById(R.id.tl_steps);
        mViewPager = (ViewPager) findViewById(R.id.vp_steps);
        mTabLayout.setupWithViewPager(mViewPager);

        String actionBarStepTitle = getString(R.string.current_step);
        getSupportActionBar().setTitle(actionBarStepTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupViewPager();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mSteps != null){
            outState.putParcelableArrayList(KEY_STEPS, mSteps);
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

    private void setupViewPager() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        RecipeStepsFragmentAdapter adapter = new RecipeStepsFragmentAdapter(getApplicationContext(),
                supportFragmentManager,mSteps );
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(position);
    }

}
