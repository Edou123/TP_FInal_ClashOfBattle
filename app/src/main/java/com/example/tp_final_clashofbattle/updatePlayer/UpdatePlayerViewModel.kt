package com.example.tp_final_clashofbattle.updatePlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_final_clashofbattle.models.Player
import com.example.tp_final_clashofbattle.Database.AppDatabase
import com.example.tp_final_clashofbattle.api.PlayerAPI
import kotlinx.coroutines.launch

class UpdatePlayerViewModel: ViewModel() {

    val api: PlayerAPI = PlayerAPI.service
    var dao = AppDatabase.INSTANCE!!.playerDao()

    lateinit var unPlayer: LiveData<Player>

    fun getPlayer(nom:String){
        unPlayer = dao.getPlayerByData(nom)
    }

    fun updatePlayer(player: Player){
        viewModelScope.launch {
            dao.update(player)
            if(player.remoteId!= null) {
                api.updateItem(player.remoteId, player)
            }
        }
    }
}