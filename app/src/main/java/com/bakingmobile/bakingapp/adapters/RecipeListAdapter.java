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

    public RecipeListAdapter(Context context, List<Recipe> recipes) {
        this.recipes = recipes;
        applicationContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder( applicationContext, view);
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

        public ViewHolder(Context context, View itemView) {

            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.tv_name);
            mServings = (TextView)  itemView.findViewById(R.id.tv_servings);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_recipe_image);
            this.context = context;

        }

        public void bindView(Recipe recipe) {
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
        }


    }



}
