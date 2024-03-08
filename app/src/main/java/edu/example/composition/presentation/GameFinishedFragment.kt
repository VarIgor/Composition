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

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        parseArgs()
//    }

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
//        onBackPressed()
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


//    private fun parseArgs() {
//        gameResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            requireArguments().getParcelable(KEY_GAME_RESULT, GameResult::class.java)!!
//        } else {
//            @Suppress("DEPRECATION") requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT) as GameResult
//        }
//    }

    private fun getSmileResId(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

//    private fun onBackPressed() {
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    retryGame()
//                }
//            })
//    }

    private fun retryGame() {
        findNavController().popBackStack()
//        requireActivity().supportFragmentManager
//            .popBackStack(
//                GameFragment.FRAGMENT_NAME,
//                FragmentManager.POP_BACK_STACK_INCLUSIVE
//            )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
//        private const val KEY_GAME_RESULT = "game_result"
//        fun newInstance(gameResult: GameResult): GameFinishedFragment {
//            return GameFinishedFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_GAME_RESULT, gameResult)
//                }
//            }
//        }
    }
}