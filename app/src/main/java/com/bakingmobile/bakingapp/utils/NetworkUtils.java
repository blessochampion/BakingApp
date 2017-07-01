package com.bakingmobile.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by blessochampion on 7/1/17.
 */

public class NetworkUtils
{
    private static String recipeListFragment = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public static String getRecipeListUrl(){

        return recipeListFragment;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
