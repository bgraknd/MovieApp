package com.bugra.movieapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("url")
fun loadImage(imageView: ImageView, url: String) {
    if (url.isEmpty()) {
        return
    }
    Picasso.get().load(url).into(imageView)
}