package com.bakingmobile.bakingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bakingmobile.bakingapp.AppController;
import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.adapters.RecipeListAdapter;
import com.bakingmobile.bakingapp.models.Recipe;
import com.bakingmobile.bakingapp.utils.NetworkUtils;
import com.bakingmobile.bakingapp.utils.RecipeParser;

import org.json.JSONArray;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray> {
    private RecyclerView mRecipeListRecyclerView;
    private RecipeListAdapter mRecipeListAdapater;
    private JsonArrayRequest mArrayRequest;
    private List<Recipe> mRecipeList;

    TextView mErrorMessageTextView;
    ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecipeListRecyclerView = (RecyclerView) findViewById(R.id.rv_recipe_list);
        mRecipeListRecyclerView.setHasFixedSize(true);

        mErrorMessageTextView = (TextView) findViewById(R.id.tv_error_message);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecipeListRecyclerView.setLayoutManager(linearLayoutManager);
        makeNetworkRequestForRecipes();

    }

    private void makeNetworkRequestForRecipes() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        String recipeListUrl = NetworkUtils.getRecipeListUrl();

        mArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                recipeListUrl
                , null, this, this
        );

        AppController.getInstance().addToRequestQueue(mArrayRequest);

    }

    private void showError(String errorMessage){
        mLoadingIndicator.setVisibility(View.GONE);
        mRecipeListRecyclerView.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setText(errorMessage);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mArrayRequest != null) {
            mArrayRequest.cancel();
        }
    }

    @Override
    public void onResponse(JSONArray response) {



        mRecipeList = RecipeParser.parseRecipe(response);
        mLoadingIndicator.setVisibility(View.GONE);
        mErrorMessageTextView.setVisibility(View.GONE);
        mRecipeListRecyclerView.setVisibility(View.VISIBLE);
        mRecipeListAdapater = new RecipeListAdapter(getApplicationContext(), mRecipeList);
        mRecipeListRecyclerView.setAdapter(mRecipeListAdapater);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String networkError = getString(R.string.networ_error_message);
        showError(networkError);
    }



}
