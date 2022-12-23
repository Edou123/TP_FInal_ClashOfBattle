package com.example.tp_final_clashofbattle.battle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.tp_final_clashofbattle.R
import com.example.tp_final_clashofbattle.api.PlayerViewModel
import com.example.tp_final_clashofbattle.databinding.FragmentBattleBinding
import com.example.tp_final_clashofbattle.utils.loadImage


/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BattleFragment : Fragment() {

    private var _binding: FragmentBattleBinding? = null

    companion object {
        const val OPPONENT_ID_ARG = "OPPONENT_ID_ARG"
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

        viewModel.getMonPlayer("Edouard")

        val playerId = arguments?.getLong(OPPONENT_ID_ARG)
            ?: throw IllegalStateException("Should have an opponent id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.monPlayer.observe(viewLifecycleOwner){
            binding.TVNomPerso.setText(it.name)
            loadImage(binding.IVImagePerso,it.imageUrl)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}