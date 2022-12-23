package com.example.tp_final_clashofbattle.updatePlayer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tp_final_clashofbattle.R
import com.example.tp_final_clashofbattle.capabilities.SelectCapabilityActivity
import com.example.tp_final_clashofbattle.databinding.FragmentUpdateplayerBinding
import com.example.tp_final_clashofbattle.engine.getCapabilitySuffix
import com.example.tp_final_clashofbattle.models.Player
import com.example.tp_final_clashofbattle.utils.getColor
import com.example.tp_final_clashofbattle.utils.getNameId
import com.example.tp_final_clashofbattle.utils.loadImage
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UpdatePlayerFragment : Fragment() {

    private var _binding: FragmentUpdateplayerBinding? = null

    private lateinit var viewModel: UpdatePlayerViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val selectCapabilityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        it.data?.let { intent ->
            val pair = SelectCapabilityActivity.extractResultData(intent)
            when (pair.first){
                1-> {
                    pair.second?.let {it1 -> binding.tvCompetence1.setText(it1.getNameId())}
                    pair.second?.let {it1 -> binding.tvCompetence1.setTextColor(it1.getColor(requireContext()))}
                }
                2-> {
                    pair.second?.let { it1 -> binding.tvCompetence2.setText(it1.getNameId()) }
                    pair.second?.let {it1 -> binding.tvCompetence2.setTextColor(it1.getColor(requireContext()))}

                }
                3-> {
                    pair.second?.let { it1 -> binding.tvCompetence3.setText(it1.getNameId()) }
                    pair.second?.let {it1 -> binding.tvCompetence3.setTextColor(it1.getColor(requireContext()))}

                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdatePlayerViewModel::class.java)
//        viewModel.getPlayer("Edouard")
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
            binding.tvCompetence1.setText(it.capability1.getNameId())
            binding.tvCompetence1.setTextColor(it.capability1.getColor(requireContext()))
            binding.tvCompetence2.setText(it.capability2.getNameId())
            binding.tvCompetence2.setTextColor(it.capability1.getColor(requireContext()))
            binding.tvCompetence3.setText(it.capability3.getNameId())
            binding.tvCompetence3.setTextColor(it.capability1.getColor(requireContext()))
        }

        binding.bModifierCompetence1.setOnClickListener{
            selectCapabilityLauncher.launch(
                SelectCapabilityActivity.newIntent(
                    requireContext(),
                    1
                )
            )
        }

        binding.bModifierCompetence2.setOnClickListener{
            selectCapabilityLauncher.launch(
                SelectCapabilityActivity.newIntent(
                    requireContext(),
                    2
                )
            )
        }

        binding.bModifierCompetence3.setOnClickListener{
            selectCapabilityLauncher.launch(
                SelectCapabilityActivity.newIntent(
                    requireContext(),
                    3
                )
            )
        }

        binding.bValider.setOnClickListener{
            lifecycleScope.launch {
                viewModel.validate(
                    binding.etNomPlayer.text.toString(),
                    binding.etUrlImagePlayer.text.toString()
                )
                findNavController().popBackStack()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}