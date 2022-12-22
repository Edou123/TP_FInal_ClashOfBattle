package com.example.tp_final_clashofbattle.updatePlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tp_final_clashofbattle.databinding.FragmentUpdateplayerBinding
import com.example.tp_final_clashofbattle.utils.getColor
import com.example.tp_final_clashofbattle.utils.loadImage

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UpdatePlayerFragment : Fragment() {

    private var _binding: FragmentUpdateplayerBinding? = null

    private lateinit var viewModel: UpdatePlayerViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdatePlayerViewModel::class.java)
        viewModel.getPlayer("Edouard")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateplayerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.unPlayer.observe(viewLifecycleOwner){
            loadImage(binding.ivPlayer, it.imageUrl)
            binding.etNomPlayer.setText(it.name)
            binding.etUrlImagePlayer.setText(it.imageUrl)
            binding.tvCompetence1.text = it.capability1.name
            binding.tvCompetence1.setTextColor(it.capability1.getColor(requireContext()))
            binding.tvCompetence2.text = it.capability2.name
            binding.tvCompetence2.setTextColor(it.capability1.getColor(requireContext()))
            binding.tvCompetence3.text = it.capability3.name
            binding.tvCompetence3.setTextColor(it.capability1.getColor(requireContext()))

        }


//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}