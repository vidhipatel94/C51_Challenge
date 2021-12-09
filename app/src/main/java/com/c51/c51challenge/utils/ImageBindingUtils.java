package com.c51.c51challenge.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.c51.c51challenge.R;

public class ImageBindingUtils {

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_local_offer_grey_500_24dp)
                .into(imageView);
    }
}
