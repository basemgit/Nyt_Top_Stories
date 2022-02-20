package com.basemibrahim.nyt_top_stories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.basemibrahim.nyt_top_stories.R
import com.basemibrahim.nyt_top_stories.databinding.DetailsFragmentBinding
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.DATE
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.IMG
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.PUBLISHED_BY
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.SECTION
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.SUMMARY
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.TITLE

class DetailsFragment : Fragment() {
    private lateinit var _binding: DetailsFragmentBinding
    private lateinit var title: String
    private lateinit var img: String
    private lateinit var publishedBy: String
    private lateinit var section: String
    private lateinit var summary: String
    private lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString(TITLE).toString()
            img = it.getString(IMG).toString()
            publishedBy = it.getString(PUBLISHED_BY).toString()
            section = it.getString(SECTION).toString()
            summary = it.getString(SUMMARY).toString()
            date = it.getString(DATE).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            _binding.image.load(img) {
                placeholder(R.drawable.ic_baseline_refresh_24)
                error(R.drawable.ic_outline_broken_image_24)
            }
        _binding.titleTxt.text = title
        _binding.publishedByTxt.text = publishedBy
        _binding.sectionTxt.text = section
        _binding.summaryTxt.text = summary
        _binding.DateTxt.text = date
    }

}