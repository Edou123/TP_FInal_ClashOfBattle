package com.example.tp_final_clashofbattle.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democlashofbattle.models.Player
import com.example.tp_final_clashofbattle.Database.AppDatabase
import com.example.tp_final_clashofbattle.utils.toListOfPlayers
import kotlinx.coroutines.launch

class PlayerViewModel :ViewModel(){

    val api:PlayerAPI =PlayerAPI.service
    var dao = AppDatabase.INSTANCE!!.playerDao()

    var listPlayer = dao.getAll()
    //À l'initialisation, on récupère les joueurs et on les met en BDD

    fun getPlayers(){
        viewModelScope.launch {
            val playerList = api.getItems().toListOfPlayers()
            //Rajouter le replace de la DAO
            dao.replace(playerList)
        }
    }

    fun getPlayersBDD() : LiveData<List<Player>>{
        return  AppDatabase.INSTANCE!!.playerDao().getAll()
    }


}