package com.basemibrahim.nyt_top_stories.data.remote


import com.basemibrahim.nyt_top_stories.data.model.TopStoriesHomeResponse
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.TOP_STORIES_HOME_URL
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET(TOP_STORIES_HOME_URL)
    suspend fun getTopStoriesHomeResponse(): Response<TopStoriesHomeResponse>


}