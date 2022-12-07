package com.bodomlake.jetpack.databinding;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.bodomlake.helloworld.R;

public class ImageViewBindingAdapter {

    @BindingAdapter(value = {"image", "defaultImage"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, Integer resId) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_background).into(imageView);
        } else {
            // imageView.setBackgroundColor(Color.GRAY);
            imageView.setImageResource(resId);
        }
    }
}
