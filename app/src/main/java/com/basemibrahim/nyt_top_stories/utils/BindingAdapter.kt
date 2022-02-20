package com.basemibrahim.nyt_top_stories.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (!imgUrl.isNullOrEmpty()) {
        imgView.load(imgUrl)
        imgView.visibility = View.VISIBLE
    } else {
        imgView.visibility = View.GONE
    }
}