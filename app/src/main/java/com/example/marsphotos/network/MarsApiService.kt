package com.example.marsphotos.network

import retrofit2.Call
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET

//class MarsApiService {
//    private val BASE_URL ="https://android-kotlin-fun-mars-server.appspot.com"
//    val retrofit= Retrofit.Builder()
//        .addConverterFactory(ScalarsConverterFactory.create())
//        ///"ScalarsConverterFactory" is used to convert the response into a string
//        //This is because the response from the server is a string
//        .baseUrl(BASE_URL)
//        .build()
//        ///If the response would have been a JSON object,
//        // we would have used "GsonConverterFactory" instead of "ScalarsConverterFactory"
//}
interface MarsApiInterface{
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
object MarsApiService{
    private const val BASE_URL ="https://android-kotlin-fun-mars-server.appspot.com"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    private val retrofit= Retrofit.Builder()
    ///"ScalarsConverterFactory" is used to convert the response into a string
    //This is because the response from the server is a string
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
    ///If the response would have been a JSON object,
    // we would have used "GsonConverterFactory" instead of "ScalarsConverterFactory"
    val retrofitService : MarsApiInterface by lazy {
        retrofit.create(MarsApiInterface::class.java)
    }
}