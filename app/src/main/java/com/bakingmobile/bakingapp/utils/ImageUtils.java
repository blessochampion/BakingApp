package com.bakingmobile.bakingapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by blessochampion on 6/30/17.
 */

public class ImageUtils
{

    public static void loadImageFromResourcesToImageView(Context context, int resourceId, ImageView imageView){
        Picasso.with(context).load(resourceId).fit().into(imageView);
    }

    public static void loadImageFromRemoteServerIntoImageView(Context context, String imageURL, ImageView imageView){
        Picasso.with(context).load(imageURL).fit().into(imageView);
    }
}
