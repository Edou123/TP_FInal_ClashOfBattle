package com.example.tp_final_clashofbattle.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tp_final_clashofbattle.models.Player


@Dao
interface PlayerDAO{

    @Query("SELECT * FROM Player ORDER BY name")
    fun getAll(): LiveData<List<Player>>

    @Update
    suspend fun update(player: Player)

    @Query("SELECT * FROM Player WHERE name= :name")
    fun getUser(name: String) : LiveData<Player>

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