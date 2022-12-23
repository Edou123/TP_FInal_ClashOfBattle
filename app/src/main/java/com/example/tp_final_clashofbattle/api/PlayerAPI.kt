package com.example.tp_final_clashofbattle.api

import com.example.tp_final_clashofbattle.models.Player
import com.example.tp_final_clashofbattle.utils.CapabilityMoshiConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface PlayerAPI {
    companion object {
        private const val BASE_URL =
            "https://firechat-dev-da136-default-rtdb.europe-west1.firebasedatabase.app"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(CapabilityMoshiConverter())
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        val service: PlayerAPI by lazy { retrofit.create(PlayerAPI::class.java) }
    }

    @GET("players.json")
    suspend fun getItems(): Map<String, Player>

    @PUT("players/{id}.json")
    suspend fun updateItem(@Path("id") id: String, player: Player): Player

}
