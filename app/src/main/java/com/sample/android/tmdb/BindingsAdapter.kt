package com.sample.android.tmdb

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.CardView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition

object BindingsAdapter {

    private const val BASE_POSTER_PATH = "http://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(cardView: CardView, url: String?) {

        Glide.with(cardView.context)
                .asBitmap()
                .load("$BASE_POSTER_PATH$url")
                .apply(RequestOptions().centerCrop())
                .into(object : BitmapImageViewTarget(cardView.findViewById(R.id.movie_poster)) {
                    override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                        super.onResourceReady(bitmap, transition)
                        Palette.from(bitmap).generate { palette ->
                            val color = palette!!.getVibrantColor(
                                    ContextCompat.getColor(cardView.context,
                                            R.color.black_translucent_60))

                            cardView.findViewById<View>(R.id.title_background).setBackgroundColor(color)
                        }
                    }
                })
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: AppCompatImageView, url: String?) {

        Glide.with(imageView.context)
                .load("$BASE_POSTER_PATH$url")
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, url: String?) {

        Glide.with(imageView.context)
                .load("$BASE_BACKDROP_PATH$url")
                .into(imageView)
    }
}