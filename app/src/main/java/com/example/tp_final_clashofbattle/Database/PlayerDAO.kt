package com.example.tp_final_clashofbattle.Database

import android.text.method.TextKeyListener.clear
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.democlashofbattle.models.Player


@Dao
interface PlayerDAO{

    @Query("SELECT * FROM Player ORDER BY name")
    fun getAll(): LiveData<List<Player>>

    @Update
    suspend fun update(player: Player)

    @Query("SELECT * FROM Player WHERE name= :name")
    suspend fun getUser(name: String) : Player

    @Insert
    suspend fun insertAll(trips: List<Player>)

    @Query("DELETE FROM Player")
    suspend fun clear()

    @Transaction
    suspend fun replace(players: List<Player>) {
        clear()
        insertAll(players)
    }

    @Query("SELECT * FROM Player WHERE remoteId= :remoteId")
    fun getPlayerByData(remoteId : String): LiveData<Player>
}