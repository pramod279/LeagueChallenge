package life.league.challenge.java.common.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import life.league.challenge.java.LeagueApp;

/**
 * Created by Pramod Selvaraj on 03-07-2021.
 * This is the Glide Image Loader Utility
 */
public class Glider {
    /**
     * Function for Showing Round User Avatar
     *
     * @param imageURL
     * @param placeHolderID
     * @param imageView
     */
    public static void loadRoundGlideImage(String imageURL, int placeHolderID, final ImageView imageView) {
        if (LeagueApp.getInstance() == null) return;
        Glide.with(LeagueApp.getInstance())
                .asBitmap()
                .load(imageURL)
                .placeholder(placeHolderID)
                .error(placeHolderID)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory
                                .create(LeagueApp.getInstance().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}