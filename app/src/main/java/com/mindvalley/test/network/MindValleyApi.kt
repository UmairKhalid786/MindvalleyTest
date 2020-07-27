package com.mindvalley.test.network

import com.mindvalley.test.model.responses.categories.CategoriesResponse
import com.mindvalley.test.model.responses.channels.ChannelsResponse
import com.mindvalley.test.model.responses.episodes.EpisodesResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface MindValleyApi {
    /**
     * Get the list of the Categories from the API
     */
    @GET("A0CgArX3")
    fun getCategories() : Observable<CategoriesResponse>

    /**
     * Get the list of the Channels from the API
     */
    @GET("Xt12uVhM")
    fun getChannels() : Observable<ChannelsResponse>

    /**
     * Get the list of the Episodes from the API
     */
    @GET("z5AExTtw")
    fun getEpisodes() : Observable<EpisodesResponse>
}