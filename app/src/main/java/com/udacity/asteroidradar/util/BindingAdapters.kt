package com.udacity.asteroidradar

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfDay

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("asteroidCodeName")
fun TextView.setAsteroidCodeName(asteroid: Asteroid?) {
    asteroid?.let {
        text = it.codename
    }
}

@BindingAdapter("asteroidApproachDate")
fun TextView.setAsteroidApproachDate(asteroid: Asteroid?) {
    asteroid?.let {
        text = it.closeApproachDate
    }
}


@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("pictureOfDay")
fun bindImagePictureOfDay(imageView: ImageView, data: PictureOfDay?) {
    val context = imageView.context
    data?.let {
        if (it.mediaType == "image") {
            Picasso.with(imageView.context)
                .load(it.url)
                .into(imageView)
            val strFormat = imageView.resources.getString(
                R.string.nasa_picture_of_day_content_description_format
            )

            imageView.contentDescription = String.format(strFormat, it.title)

        }
        else{
            val defaultImg = context.getString(R.string.universe_img)
                .toUri()
                .buildUpon()
                .scheme("https")
                .build()

            Glide.with(imageView.context)
                .load(defaultImg)
                .into(imageView)

        }
    }
}

