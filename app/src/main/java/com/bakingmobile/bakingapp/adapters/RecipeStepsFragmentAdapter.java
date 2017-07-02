package com.bakingmobile.bakingapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.fragments.RecipeDetailsFragment;
import com.bakingmobile.bakingapp.fragments.RecipeStepFragment;
import com.bakingmobile.bakingapp.models.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blessochampion on 6/29/17.
 */

public class RecipeStepsFragmentAdapter extends FragmentPagerAdapter {
    private List<Step> mSteps = new ArrayList<>();
    Context mContext;

    public RecipeStepsFragmentAdapter(Context context, FragmentManager manager, List<Step> steps) {
        super(manager);
        mSteps = steps;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        return  RecipeStepFragment.getNewInstance(mSteps.get(position));
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String step =  mContext.getString(R.string.step);
        return  step + " " + position;
    }
}
