package com.example.tp_final_clashofbattle.utils

import com.example.democlashofbattle.models.Player

fun Map<String, Player>.toListOfPlayers() = entries.map {
    it.value.copy(remoteId = it.key)
}