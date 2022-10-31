package com.app.myorisapptest.utilities.network

import com.app.myorisapptest.models.userDetailsPOJO.UserDetailsPOJO
import com.app.myorisapptest.models.usersPojo.UserPOJO
import com.app.myorisapptest.utilities.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {

    companion object{
        operator fun invoke() : RetrofitService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RetrofitService::class.java)
        }
    }

    @GET("/api/users")
    suspend fun getUsers(
        @Query("per_page")size : Int
    ) : UserPOJO


    @GET("/api/users/")
    suspend fun getUsersDetails(
        @Query("id") id: Int,
    ) : UserDetailsPOJO


}