package com.basemibrahim.nyt_top_stories.di

import com.basemibrahim.nyt_top_stories.data.remote.RetrofitService
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.API_KEY
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        var interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .readTimeout(50, TimeUnit.SECONDS)
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50,TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor {chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder().addQueryParameter("api-key", API_KEY).build()
                request.url(url)
                val response = chain.proceed(request.build())
                return@addInterceptor response  }
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }




    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): RetrofitService =
        retrofit.create(RetrofitService::class.java)

}