package com.bakingmobile.bakingapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bakingmobile.bakingapp.R;

public class RecipeListActivity extends AppCompatActivity {
    private RecyclerView mRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);


        mRecipeList = (RecyclerView) findViewById(R.id.rv_recipe_list);
        mRecipeList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecipeList.setLayoutManager(linearLayoutManager);

        //set Adapter
    }
}
