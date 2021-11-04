package id.widianapw.android_utils.extensions

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import coil.load
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.facebook.shimmer.ShimmerFrameLayout
import id.widianapw.android_utils.R

/**
 * Created by Widiana Putra on 04/11/21
 * Copyright (c) Widiana Putra
 */

fun View.onClick(debounceTime: Long = 600L, action: (View) -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}


fun ImageView.loadImage(url: String?) {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(750) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()
    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
    this.load(url) {
        fallback(R.drawable.ic_baseline_image_24)
        placeholder(shimmerDrawable)
    }
}

fun ImageView.loadImageProfile(url: String?) {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(750) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()
    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
    this.load(url) {
        fallback(R.drawable.ic_baseline_account_circle_24)
        placeholder(R.drawable.ic_baseline_account_circle_24)
    }
}

fun ShimmerFrameLayout.showShimmer(isLoading: Boolean, contentView: View?) {
    this.setVisibility(isLoading)
    contentView?.setVisibility(!isLoading)
    if (isLoading) this.showShimmer(isLoading)
    else this.hideShimmer()
}

fun View.animateVisible() {
    this.animate().alpha(1.0f).duration = 500
}

fun View.animateGone() {
    this.animate().alpha(0.0f).duration = 500
}


fun View.setVisibility(value: Boolean) {
    this.visibility = if (value) View.VISIBLE else View.GONE
}

fun View.setInvisible(value: Boolean) {
    this.visibility = if (value) View.VISIBLE else View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}