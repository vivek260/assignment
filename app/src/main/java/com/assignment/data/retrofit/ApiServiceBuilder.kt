package com.assignment.data.retrofit

import com.google.gson.Gson
import com.assignment.BuildConfig
import com.assignment.data.interceptor.InternetConnectivityInterceptor
import com.assignment.presentation.utilities.LiveConnectivityMonitor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceBuilder {
    private const val CONNECT_TIMEOUT_SECONDS = 30L
    private const val READ_TIMEOUT_SECONDS = 30L
    var service: ApiService? = null

    fun build(): ApiService {
        val baseUrl ="https://api.github.com/"
        if (service == null) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            }

            val builder = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(InternetConnectivityInterceptor(LiveConnectivityMonitor()))
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)

            service = Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(ApiService::class.java)
        }
        return service!!
    }
}