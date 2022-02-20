package com.basemibrahim.nyt_top_stories.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.basemibrahim.nyt_top_stories.R
import com.basemibrahim.nyt_top_stories.data.model.TopStoriesHomeResponse
import com.basemibrahim.nyt_top_stories.ui.NewsAdapter


@BindingAdapter("list")
fun bindRecyclerView(recyclerView: RecyclerView, list: NetworkResult<TopStoriesHomeResponse>?) {
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(list?.data?.results)
}

@BindingAdapter("title")
fun bindTitle(textView: TextView, title: String?) {
    title?.let {
        textView.text = title
    }
}

@BindingAdapter("author")
fun bindAuthor(textView: TextView, author: String?) {
    author?.let {
        textView.text = if (author.isNotEmpty()) author
        else textView.context.resources.getString(R.string.unknown)
    }
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (!imgUrl.isNullOrEmpty()) {
        imgView.load(imgUrl) {
            placeholder(R.drawable.ic_baseline_refresh_24)
            error(R.drawable.ic_outline_broken_image_24)
        }
        imgView.visibility = View.VISIBLE
    } else {
        imgView.visibility = View.GONE
    }
}