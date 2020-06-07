package com.example.android.tossmultiplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.android.tossmultiplayer.databinding.FragmentPlayBinding

class PlayFragment : Fragment() {
    private lateinit var binding: FragmentPlayBinding
    private lateinit var args: PlayFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_play, container, false
        )
        args = PlayFragmentArgs.fromBundle(requireArguments())
        binding.configPlayer1Name.text = args.name1.capitalize()
        binding.configPlayer2Name.text = args.name2.capitalize()

        binding.tossButton1.setOnClickListener { view: View ->
            val checkedId = binding.player2Choice.checkedRadioButtonId
            if (checkedId != -1) {
                calculateScore(args.name1.capitalize(), args.name2.capitalize())
                createVisibleOptions()
                if ((binding.score1.text.toString().toInt() >= 10) && args.name1.isNotBlank()) {
                    view.findNavController().navigate(
                        PlayFragmentDirections.actionPlayFragmentToResultFragment(args.name1.capitalize())
                    )
                } else if ((binding.score2.text.toString()
                        .toInt() >= 10) && args.name1.isNotBlank()
                ) {
                    view.findNavController().navigate(
                        PlayFragmentDirections.actionPlayFragmentToResultFragment(args.name2.capitalize())
                    )
                }
            }
            else
                Toast.makeText(context,"${args.name2} select either heads/tails",
                    Toast.LENGTH_SHORT).show()
            binding.invalidateAll()
        }
        binding.tossButton2.setOnClickListener { view: View ->
            val checkedId = binding.player1Choice.checkedRadioButtonId
            if (checkedId != -1) {
                calculateScore(args.name1.capitalize(), args.name2.capitalize())
                createVisibleOptions()
                if ((binding.score1.text.toString().toInt() >= 10) && args.name1.isNotBlank()) {
                    view.findNavController().navigate(
                        PlayFragmentDirections.actionPlayFragmentToResultFragment(args.name1.capitalize())
                    )
                } else if ((binding.score2.text.toString()
                        .toInt() >= 10) && args.name2.isNotBlank()
                ) {
                    view.findNavController().navigate(
                        PlayFragmentDirections.actionPlayFragmentToResultFragment(args.name2.capitalize())
                    )
                }
            }
            else
                Toast.makeText(context,"${args.name1} select either heads/tails",
                    Toast.LENGTH_SHORT).show()
            binding.invalidateAll()
        }
        return binding.root
    }

    private fun createVisibleOptions() {
        var check = binding.turnChecker.text.toString().toInt()
        if (check % 2 != 0) {
            binding.choiceMessage2.text = getString(R.string.empty_string)
            binding.choiceMessage1.text = getString(R.string.choiceMessage)
            binding.player2Choice.visibility = View.INVISIBLE
            binding.player1Choice.visibility = View.VISIBLE
            binding.tossButton2.visibility = View.VISIBLE
            binding.tossButton1.visibility = View.INVISIBLE
        } else {
            binding.choiceMessage2.text = getString(R.string.choiceMessage)
            binding.choiceMessage1.text = getString(R.string.empty_string)
            binding.player2Choice.visibility = View.VISIBLE
            binding.player1Choice.visibility = View.INVISIBLE
            binding.tossButton2.visibility = View.INVISIBLE
            binding.tossButton1.visibility = View.VISIBLE
        }
        binding.turnChecker.text = (check + 1).toString()
        binding.player1Choice.clearCheck()
        binding.player2Choice.clearCheck()
    }

    private fun calculateScore(name1: String, name2: String) {
        val choice = (1..2).random()
        var check = binding.turnChecker.text.toString().toInt()
        if (check % 2 == 0) {
            val headSelected: Boolean = binding.player1Head.isChecked
            val tailSelected: Boolean = binding.player1Tail.isChecked
            if (headSelected || tailSelected) {
                if ((headSelected && choice == 1) || (tailSelected && choice == 2)) {
                    binding.score1.text = (binding.score1.text.toString().toInt() + 1).toString()
                    binding.wonMessage.text =
                        getString(R.string.player_1_win_message).replace("Player 1", name1)
                } else {
                    binding.score2.text = (binding.score2.text.toString().toInt() + 1).toString()
                    binding.wonMessage.text =
                        getString(R.string.player_2_win_message).replace("Player 2", name2)
                }
            }
        } else {
            val headSelected: Boolean = binding.player2Head.isChecked
            val tailSelected: Boolean = binding.player2Tail.isChecked
            if (headSelected || tailSelected) {
                if ((headSelected && choice == 1) || (tailSelected && choice == 2)) {
                    binding.score2.text = (binding.score2.text.toString().toInt() + 1).toString()
                    binding.wonMessage.text =
                        getString(R.string.player_2_win_message).replace("Player 2", name2)
                } else {
                    binding.score1.text = (binding.score1.text.toString().toInt() + 1).toString()
                    binding.wonMessage.text =
                        getString(R.string.player_1_win_message).replace("Player 1", name1)
                }
            }
        }
        if (choice == 1)
            binding.headMessage.setImageResource(R.drawable.heads_image)
        else
            binding.headMessage.setImageResource(R.drawable.tails_image)
        binding.invalidateAll()
    }
}
