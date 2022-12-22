package com.example.tp_final_clashofbattle.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democlashofbattle.models.Player
import com.example.democlashofbattle.utils.toListOfPlayers
import kotlinx.coroutines.launch

class PlayerViewModel :ViewModel(){

    val api:PlayerAPI =PlayerAPI.service

    var PlayerList = listOf<Player>()
    //À l'initialisation, on récupère les joueurs et on les met en BDD
    init{
        getPlayers()
    }
    fun getPlayers(){
        viewModelScope.launch {
            PlayerList = api.getItems().toListOfPlayers()
            //Rajouter le replace de la DAO

        }
    }

    fun updatePlayer(player: Player){
        viewModelScope.launch {
            //api.updateItem(player.remoteId,player)
        }
    }
}