package com.example.android.tossmultiplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.tossmultiplayer.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHelpBinding>(
            inflater, R.layout.fragment_help, container, false
        )
        binding.homeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_helpFragment_to_homeFragment)
        }
        return binding.root
    }
}
