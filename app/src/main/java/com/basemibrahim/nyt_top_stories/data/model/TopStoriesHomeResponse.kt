package com.basemibrahim.nyt_top_stories.data.model

data class TopStoriesHomeResponse(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Result>,
    val section: String,
    val status: String
)