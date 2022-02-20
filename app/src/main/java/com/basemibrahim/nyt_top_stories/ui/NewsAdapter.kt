package com.basemibrahim.nyt_top_stories.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.basemibrahim.nyt_top_stories.R
import com.basemibrahim.nyt_top_stories.data.model.Result
import com.basemibrahim.nyt_top_stories.databinding.GridViewItemBinding


class NewsAdapter : ListAdapter<Result,
        NewsAdapter.NewsViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        return NewsViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class NewsViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Result) {
            binding.article = article
            val author = if (article.byline.length > 3) article.byline
            else binding.root.resources.getString(R.string.unknown)

            binding.root.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailsFragment(
                    title = article.title,
                    img = article.multimedia[0].url,
                    publishedBy = author,
                    section = article.section,
                    summary = article.abstract,
                    date = article.published_date.substring(0, 10)
                )
                binding.root.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url

        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title
        }
    }
}