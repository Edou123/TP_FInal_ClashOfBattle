package com.example.tp_final_clashofbattle.engine

import com.example.democlashofbattle.models.Capability

data class PlayerBattleInfo(
    val remainingCapabilities: List<Capability>,
    val pv: Int = 50
)