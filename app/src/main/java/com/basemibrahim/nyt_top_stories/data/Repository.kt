package com.basemibrahim.nyt_top_stories.data

import com.basemibrahim.nyt_top_stories.data.model.TopStoriesHomeResponse
import com.basemibrahim.nyt_top_stories.data.remote.RemoteDataSource
import com.basemibrahim.nyt_top_stories.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getTopStoriesHomeResponse(): Flow<NetworkResult<TopStoriesHomeResponse>> {
        return flow<NetworkResult<TopStoriesHomeResponse>> {
            emit(safeApiCall { remoteDataSource.getTopStoriesHomeResponse() })
        }.flowOn(Dispatchers.IO)
    }


}