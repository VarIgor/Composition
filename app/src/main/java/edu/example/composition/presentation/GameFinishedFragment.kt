package edu.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.example.composition.R
import edu.example.composition.databinding.FragmentGameFinishedBinding


class GameFinishedFragment : Fragment() {
    private val args: GameFinishedFragmentArgs by navArgs()
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private val gameResult by lazy { args.gameResult }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertValuesGameResult()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun insertValuesGameResult() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())

            tvRequiredAnswers.text = String.format(
                getString(R.string.required_answers),
                gameResult.gameSettings.minCountOfRightAnswers.toString()
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswer.toString()
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers.toString()
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                gameResult.percentOfRightAnswers
            )
        }
    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
