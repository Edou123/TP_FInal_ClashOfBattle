package com.example.tp_final_clashofbattle.updatePlayer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_final_clashofbattle.models.Player
import com.example.tp_final_clashofbattle.Database.AppDatabase
import com.example.tp_final_clashofbattle.api.PlayerAPI
import com.example.tp_final_clashofbattle.models.Capability
import kotlinx.coroutines.launch

class UpdatePlayerViewModel: ViewModel() {

    val api: PlayerAPI = PlayerAPI.service
    var dao = AppDatabase.INSTANCE!!.playerDao()

    val unPlayer = MutableLiveData<Player>()

    init{
        viewModelScope.launch {
            unPlayer.value = dao.getPlayerByData("Edouard")
        }
    }

//    fun getPlayer(nom:String){
//        unPlayer = dao.getPlayerByData(nom)
//    }

    fun updateCapability(index: Int, capability: Capability?){
        capability?.let {
            val player = checkNotNull(unPlayer.value)
            unPlayer.value = when(index){
                0->player.copy(capability1 = capability)
                1->player.copy(capability1 = capability)
                2->player.copy(capability3 = capability)
                else -> throw IllegalStateException("Pas de capacité pour cet index")
            }
        }
    }

    suspend fun validate(name:String, imageUrl: String){

        unPlayer.value?.let { player ->
            val modifierPlayer = player.copy(
                name=name,
                imageUrl = imageUrl
            )
            val remoteId = checkNotNull(modifierPlayer.remoteId)
            api.updateItem(remoteId, modifierPlayer)
            dao.update(modifierPlayer)
        }
//        viewModelScope.launch {
//            dao.update(player)
//            if(player.remoteId!= null) {
//                api.updateItem(player.remoteId, player)
//            }
//        }
    }
}