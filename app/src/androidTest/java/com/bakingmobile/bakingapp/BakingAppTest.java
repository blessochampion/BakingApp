package com.bakingmobile.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bakingmobile.bakingapp.activities.RecipeListActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Blessing.Ekundayo on 7/4/2017.
 */
@RunWith(AndroidJUnit4.class)
public class BakingAppTest {
    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule = new ActivityTestRule<>(RecipeListActivity.class);
    private BakingIdlingResource idlingResource;
    @Before
    public void registerIntentServiceIdlingResource() {
        RecipeListActivity activity = mActivityTestRule.getActivity();
        idlingResource = new BakingIdlingResource(activity);
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void browniesShowsInActionBar(){
        // First scroll to the position that needs to be matched and click on it.
        final int BROWNIES_POSITION = 1;
        final String BROWNIES = "Brownies";
        onView(withId(R.id.rv_recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(BROWNIES_POSITION, click()));
        onView(withText(BROWNIES) ).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);

    }
    }
