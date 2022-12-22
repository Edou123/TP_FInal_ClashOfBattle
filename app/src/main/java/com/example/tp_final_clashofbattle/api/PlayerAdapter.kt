package com.example.tp_final_clashofbattle.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.democlashofbattle.models.Job
import com.example.democlashofbattle.models.Player
import com.example.democlashofbattle.utils.loadImage
import com.example.tp_final_clashofbattle.databinding.ViewCardPlayerBinding

class PlayerAdapter(val clickListener: (String) -> Unit) : ListAdapter<Player, PlayerViewHolder>(
    PlayerDiffCallback()
) {
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder.from(parent)
    }
}

class PlayerViewHolder private constructor(val binding: ViewCardPlayerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(clickListener: (String) -> Unit, item: Player) {
        binding.TVNom.text = item.name
        loadImage(binding.IVImage, item.imageUrl)
        val capacite1 = item.capability1.type.name
        val capacite2 = item.capability2.type.name
        val capacite3 = item.capability3.type.name

        var attaque: Int = 0
        var defense: Int = 0
        var soin: Int = 0

        when (capacite1) {

            "ATTACK" -> attaque++
            "DEFENSE" -> defense++
            "HEAL" -> soin++
        }

        when (capacite2) {

            "ATTACK" -> attaque++
            "DEFENSE" -> defense++
            "HEAL" -> soin++
        }

        when (capacite3) {

            "ATTACK" -> attaque++
            "DEFENSE" -> defense++
            "HEAL" -> soin++
        }

        if (attaque == 2) {
            binding.TVNomClasse.text = Job.WARRIOR.name
        } else if (defense == 2) {
            binding.TVNomClasse.text = Job.KNIGHT.name
        } else if (soin == 2) {
            binding.TVNomClasse.text = Job.PRIEST.name
        }else{
            binding.TVNomClasse.text = Job.BARD.name
        }

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


