package com.bakingmobile.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by blessochampion on 6/30/17.
 */

public class Recipe  implements Parcelable{
    private int id;
    private String name;
    private List<Ingredient> ingredients;
    private List<Step> steps;
    private String servings;
    private String imageURL;

    public Recipe(int id, String name, List<Ingredient> ingredients, List<Step> steps, String servings, String imageURL) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.imageURL = imageURL;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        steps = in.createTypedArrayList(Step.CREATOR);
        servings = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public String getServings() {
        return servings;
    }

    public String getImageURL() {
        return imageURL;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeTypedList(steps);
        dest.writeString(servings);
        dest.writeString(imageURL);
    }

    public boolean hasImage() {
        return !getImageURL().isEmpty();
    }
}
