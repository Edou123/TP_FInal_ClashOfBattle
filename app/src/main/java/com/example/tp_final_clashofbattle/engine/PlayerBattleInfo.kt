package com.example.tp_final_clashofbattle.engine

import com.example.tp_final_clashofbattle.models.Capability

data class PlayerBattleInfo(
    val remainingCapabilities: List<Capability>,
    var pv: Int = 50
)