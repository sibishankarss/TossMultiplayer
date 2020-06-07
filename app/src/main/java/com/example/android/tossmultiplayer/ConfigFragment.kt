package com.example.android.tossmultiplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.tossmultiplayer.databinding.FragmentConfigBinding

class ConfigFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentConfigBinding>(
            inflater, R.layout.fragment_config, container, false
        )

        binding.startButton.setOnClickListener { view: View ->
                if ( binding.configPlayer1Name.text.toString().isNotBlank() &&
                    binding.configPlayer2Name.text.toString().isNotBlank() )
                view.findNavController().navigate(
                    ConfigFragmentDirections.actionConfigFragmentToPlayFragment(
                        binding.configPlayer1Name.text.toString(),
                        binding.configPlayer2Name.text.toString()))
                else
                    Toast.makeText(context,"Both fields should not be empty",
                        Toast.LENGTH_SHORT).show()
            }
        return binding.root
    }
}
