package com.basemibrahim.nyt_top_stories.utils


class Constants {
    companion object {
        const val API_KEY = "QvVp6CKK8uRZFCanFy0ZuvbzJrIH8doD"
        const val BASE_URL = "https://api.nytimes.com/"
        const val TOP_STORIES_HOME_URL = "svc/topstories/v2/home.json?"

        const val PHOTOS_URL =
            "services/rest/?method=flickr.photos.search&format=json&nojsoncallback=50&text=Color&per_page=20"
        const val NETWORK_TAG = "NETWORK"
        const val NORMAL_TYPE = 0
        const val AD_TYPE = 1
        const val LIST_AD_DELTA = 6
        const val IMG = "img"

    }
}