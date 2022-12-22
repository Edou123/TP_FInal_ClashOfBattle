package com.example.tp_final_clashofbattle.Database

import android.text.method.TextKeyListener.clear
import androidx.room.*
import com.example.democlashofbattle.models.Player


@Dao
interface PlayerDAO{

    @Query("SELECT * FROM Player ORDER BY name")
    suspend fun getAll(): List<Player>

    @Update
    suspend fun update(player: Player)

    @Query("SELECT * FROM Player WHERE id= :id")
    suspend fun getUser(id: Long) : Player

    @Insert
    suspend fun insertAll(trips: List<Player>)

    @Query("DELETE FROM Player")
    suspend fun clear()

    @Transaction
    suspend fun replace(players: List<Player>) {
        clear()
        insertAll(players)
    }
}