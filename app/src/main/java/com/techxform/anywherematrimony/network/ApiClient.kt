package com.techxform.anywherematrimony.network


import com.google.gson.GsonBuilder
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.utils.DataCaching
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private val BASE_URL = "https://nearu.live/Anywhere_matrimony/api/"
    private var retrofit: Retrofit? = null
    private var retrofitWithAuthorization: Retrofit? = null
    val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .connectTimeout(120, TimeUnit.SECONDS) //Backend is really slow
        .writeTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()


    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder()
                                .setLenient()
                                .create()
                        )
                    )
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }

    fun getClientWithAuthorization(dataCaching: DataCaching): Retrofit?{
        val okHttpClientWithAuthorization = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("Authorization1", dataCaching.getAccessToken().safeGet())
                return@Interceptor chain.proceed(builder.build())
            })
            .build()
        if (retrofitWithAuthorization == null) {
            retrofitWithAuthorization = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setLenient()
                            .create()
                    )
                )
                .client(okHttpClientWithAuthorization)
                .build()
        }
        return retrofitWithAuthorization
    }

}
