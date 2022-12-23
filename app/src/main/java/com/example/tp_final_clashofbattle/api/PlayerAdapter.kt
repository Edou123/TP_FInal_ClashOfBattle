package com.example.tp_final_clashofbattle.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_final_clashofbattle.models.Job
import com.example.tp_final_clashofbattle.models.Player
import com.example.tp_final_clashofbattle.databinding.ViewCardPlayerBinding
import com.example.tp_final_clashofbattle.utils.loadImage

class PlayerAdapter: ListAdapter<Player, PlayerViewHolder>(
    PlayerDiffCallback()
) {
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder.from(parent)
    }
}

class PlayerViewHolder private constructor(val binding: ViewCardPlayerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Player) {
        binding.TVNom.text = item.name
        loadImage(binding.IVImage, item.imageUrl)
        val capacite1 = item.capability1.type.name
        val capacite2 = item.capability2.type.name
        val capacite3 = item.capability3.type.name

        binding.TVNomClasse.text = ClassPlayer(capacite1,capacite2,capacite3).name

    }

    companion object {
        fun from(parent: ViewGroup): PlayerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewCardPlayerBinding.inflate(layoutInflater, parent, false)
            return PlayerViewHolder(binding)
        }
    }
}

class PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}

fun ClassPlayer(capa1: String,capa2: String,capa3: String): Job {
    var attaque: Int = 0
    var defense: Int = 0
    var soin: Int = 0

    var classPlayer : Job

    when (capa1) {

        "ATTACK" -> attaque++
        "DEFENSE" -> defense++
        "HEAL" -> soin++
    }

    when (capa2) {

        "ATTACK" -> attaque++
        "DEFENSE" -> defense++
        "HEAL" -> soin++
    }

    when (capa3) {

        "ATTACK" -> attaque++
        "DEFENSE" -> defense++
        "HEAL" -> soin++
    }

    if (attaque >= 2) {
        classPlayer = Job.WARRIOR
    } else if (defense >= 2) {
        classPlayer = Job.KNIGHT
    } else if (soin >= 2) {
        classPlayer = Job.PRIEST
    }else{
        classPlayer = Job.BARD
    }

    return  classPlayer
}

