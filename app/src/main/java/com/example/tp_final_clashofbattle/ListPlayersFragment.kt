package com.example.tp_final_clashofbattle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp_final_clashofbattle.api.ClassPlayer
import com.example.tp_final_clashofbattle.api.PlayerAdapter
import com.example.tp_final_clashofbattle.api.PlayerViewModel
import com.example.tp_final_clashofbattle.databinding.FragmentListplayersBinding
import com.example.tp_final_clashofbattle.utils.getColor
import com.example.tp_final_clashofbattle.utils.loadImage

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListPlayersFragment : Fragment() {

    private var _binding: FragmentListplayersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        viewModel.getPlayers()
        viewModel.getMonPlayer("Edouard")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListplayersBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlayerAdapter()

        viewModel.monPlayer.observe(viewLifecycleOwner){
            val capacite1 = it.capability1.type.name
            val capacite2 = it.capability2.type.name
            val capacite3 = it.capability3.type.name

            binding.TVNomPerso.text = it.name
            loadImage(binding.IVImagePerso,it.imageUrl)

            binding.TVNomClassePerso.text = ClassPlayer(capacite1,capacite2,capacite3).name
        }

        binding.BTNModification.setOnClickListener{
            findNavController().navigate(R.id.action_listPlayersFragment_to_updatePlayerFragment)
        }

        binding.rvPlayer.adapter = adapter

        viewModel.listPlayer.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}