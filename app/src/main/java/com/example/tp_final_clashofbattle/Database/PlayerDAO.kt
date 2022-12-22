package com.example.tp_final_clashofbattle.Database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.democlashofbattle.models.Player


@Dao
interface PlayerDAO{

    @Query("SELECT * FROM Player ORDER BY name")
    suspend fun getAll(): List<Player>

    @Update
    suspend fun update(player: Player)

    @Query("SELECT * FROM Player WHERE id= :id")
    suspend fun getUser(id: Long) : Player
}