package com.example.tp_final_clashofbattle.battle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp_final_clashofbattle.Database.AppDatabase
import com.example.tp_final_clashofbattle.engine.*
import com.example.tp_final_clashofbattle.models.Capability
import com.example.tp_final_clashofbattle.models.Player
import kotlinx.coroutines.launch
import java.lang.Integer.max

class BattleViewModel: ViewModel() {
    var dao = AppDatabase.INSTANCE!!.playerDao()
    var battle :BattleEngine = BattleEngine(RandomGeneratorImpl())

    val myPlayerInfo = MutableLiveData<Player>()
    val opponentInfo = MutableLiveData<Player>()

    val myPlayerBattleInfo = MutableLiveData<PlayerBattleInfo>()
    val opponentBattleInfo = MutableLiveData<PlayerBattleInfo>()

    val roundCount = MutableLiveData(0)

    val lastPlayerResult = MutableLiveData<ActionResult>()
    val lastOpponentResult = MutableLiveData<ActionResult>()

    val winner = MutableLiveData<String>()

    fun init(opponentId: Long) {
        viewModelScope.launch {
            val myPlayer = dao.getPlayerByData("Edouard")
            val opponent = dao.getPlayerByData("Adrien")
            myPlayerInfo.value = myPlayer.value
            opponentInfo.value = opponent.value

            myPlayerBattleInfo.value = PlayerBattleInfo(
                remainingCapabilities = myPlayer.value!!.capabilities
            )
            opponentBattleInfo.value = PlayerBattleInfo(
                remainingCapabilities = opponent.value!!.capabilities
            )
        }
    }
    fun round(cap:Capability?){
        //roundCount+=1

        if(myPlayerBattleInfo.value!!.pv <= 0) {
            winner.value = opponentInfo.value!!.name

        } else if(opponentBattleInfo.value!!.pv <= 0) {
            winner.value = myPlayerInfo.value!!.name
        }

    }

    //Fonction donnée par le prof
    private fun updatePlayer(
        player: PlayerBattleInfo,
        playerResult: ActionResult,
        opponentResult: ActionResult
    ): PlayerBattleInfo {
        var newPlayer = player

        val realDamage = max(0, opponentResult.damage - playerResult.defense)
        val heal = playerResult.heal

        playerResult.usedCapability?.let {
            val newCapabilitiesList = player.remainingCapabilities.toMutableList()
            newCapabilitiesList.remove(it)
            newPlayer = newPlayer.copy(remainingCapabilities = newCapabilitiesList)
        }

        val modifiedPv = newPlayer.pv - realDamage + heal
        val pv = Integer.min(50, Integer.max(0, modifiedPv))

        return newPlayer.copy(pv = pv)
    }
}