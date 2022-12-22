package com.example.tp_final_clashofbattle.updatePlayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democlashofbattle.models.Player
import com.example.tp_final_clashofbattle.Database.AppDatabase
import com.example.tp_final_clashofbattle.api.PlayerAPI
import kotlinx.coroutines.launch

class UpdatePlayerViewModel: ViewModel() {

    val api: PlayerAPI = PlayerAPI.service
    var dao = AppDatabase.INSTANCE!!.playerDao()


    fun updatePlayer(player: Player){
        viewModelScope.launch {
            //api.updateItem(player.remoteId,player)
        }
    }
}