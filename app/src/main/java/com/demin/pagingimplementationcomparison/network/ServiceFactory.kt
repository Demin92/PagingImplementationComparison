package com.demin.pagingimplementationcomparison.network

import com.demin.pagingimplementationcomparison.BuildConfig
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceFactory {
    private val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    val gitHubService: GitHubService by lazy { retrofit.create(GitHubService::class.java) }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .build()
    }

    private fun getLoggingInterceptor(): Interceptor {
        return LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .request("Request")
                .response("Response")
                .build()
    }
}