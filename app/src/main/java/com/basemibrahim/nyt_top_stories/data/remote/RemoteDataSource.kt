package com.basemibrahim.nyt_top_stories.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val retrofitService: RetrofitService) {

    suspend fun getTopStoriesHomeResponse() =
        retrofitService.getTopStoriesHomeResponse()
}