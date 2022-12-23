package com.example.tp_final_clashofbattle.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tp_final_clashofbattle.models.Player
import com.example.tp_final_clashofbattle.utils.CapabilityRoomConverter

@Database(entities = arrayOf(Player::class), version = 1)
@TypeConverters(CapabilityRoomConverter::class)
abstract class AppDatabase : RoomDatabase() {



    abstract fun playerDao(): PlayerDAO

    companion object {


        var INSTANCE: AppDatabase? = null

        fun init(context: Context){

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "la_base_de_donnees"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

    }

}