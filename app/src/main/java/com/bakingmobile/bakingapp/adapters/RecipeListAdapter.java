package com.bakingmobile.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.models.Recipe;
import com.bakingmobile.bakingapp.utils.ImageUtils;

import java.util.List;

/**
 * Created by blessochampion on 6/29/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private List<Recipe> recipes;
    private Context applicationContext;
    private RecipeItemTouchListener listener;

    public RecipeListAdapter(Context context, List<Recipe> recipes) {
        this.recipes = recipes;
        applicationContext = context;

    }

    public void setRecipeOnTouchListener(RecipeItemTouchListener recipeOnTouchListener) {
        listener = recipeOnTouchListener;
    }

    public static interface RecipeItemTouchListener {
        public void onRecipeItemTouched(Recipe touchedRecipe);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(applicationContext, view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);
        holder.bindView(currentRecipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        TextView mServings;
        ImageView mImageView;
        Context context;
        RecipeItemTouchListener mTouchListener;
        View mItemView;

        public ViewHolder(Context context, View itemView, RecipeItemTouchListener listener) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_name);
            mServings = (TextView)  itemView.findViewById(R.id.tv_servings);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_recipe_image);
            this.context = context;
            mTouchListener = listener;
            mItemView = itemView;


        }

        public void bindView(final Recipe recipe) {
            mName.setText(recipe.getName());
            String servings = context.getString(R.string.servings) +" "+ recipe.getServings();
            mServings.setText(servings);

            if (recipe.hasImage()) {
                ImageUtils.loadImageFromRemoteServerIntoImageView(
                        context, recipe.getImageURL(), mImageView
                );

            } else {
                ImageUtils.loadImageFromResourcesToImageView(
                        context, R.drawable.recipe_image, mImageView
                );
            }

            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTouchListener != null)
                        mTouchListener.onRecipeItemTouched(recipe);
                }
            });
        }


    }



}
