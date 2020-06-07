package com.example.android.tossmultiplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.tossmultiplayer.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(
            inflater, R.layout.fragment_result, container, false
        )
        val args = ResultFragmentArgs.fromBundle(requireArguments())
        binding.wonMessage.text = binding.wonMessage.text.toString().replace("Player",args.winnerName)
        binding.playAgainButton.setOnClickListener{view:View ->
            view.findNavController().navigate(
                ResultFragmentDirections.actionResultFragmentToHomeFragment())
        }
        return binding.root
    }
}
