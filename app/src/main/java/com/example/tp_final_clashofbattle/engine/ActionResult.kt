package com.example.tp_final_clashofbattle.engine

import com.example.tp_final_clashofbattle.models.Capability

data class ActionResult(
    val damage: Int = 0,
    val defense: Int = 0,
    val heal: Int = 0,
    val usedCapability: Capability? = null,
)