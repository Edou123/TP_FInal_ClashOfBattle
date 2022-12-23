package com.example.tp_final_clashofbattle.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_final_clashofbattle.models.Player
import com.example.tp_final_clashofbattle.Database.AppDatabase
import com.example.tp_final_clashofbattle.utils.toListOfPlayers
import kotlinx.coroutines.launch

class PlayerViewModel :ViewModel(){

    val api:PlayerAPI =PlayerAPI.service
    var dao = AppDatabase.INSTANCE!!.playerDao()

    var listPlayer = dao.getAll()

    lateinit var monPlayer:LiveData<Player>
    //À l'initialisation, on récupère les joueurs et on les met en BDD

    fun getPlayers(){
        viewModelScope.launch {
            val playerList = api.getItems().toListOfPlayers()
            //Rajouter le replace de la DAO
            dao.replace(playerList)
        }
    }

    fun getMonPlayer(nom:String)
    {
        viewModelScope.launch {
            monPlayer = dao.getUser(nom)
        }
    }


}