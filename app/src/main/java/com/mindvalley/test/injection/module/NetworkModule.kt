package com.mindvalley.test.injection.module

import com.google.gson.GsonBuilder
import com.mindvalley.test.BuildConfig
import com.mindvalley.test.model.responses.episodes.IconAsset
import com.mindvalley.test.network.MindValleyApi
import com.mindvalley.test.utils.BASE_URL
import com.mindvalley.test.utils.IconAssetTypeAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Module which provides all required dependencies about network
 */

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    private const val NETWORK_CALL_TIMEOUT = 60

    /**
     * Provides the Network service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Article service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideMindVallyApi(retrofit: Retrofit): MindValleyApi {
        return retrofit.create(MindValleyApi::class.java)
    }


    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(IconAsset::class.java, IconAssetTypeAdapter())
        val gson = gsonBuilder.create()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder()

                    .addInterceptor(HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE
                        })
                    .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
                )
//                .addConverterFactory(MoshiConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))

                .build()
    }
}