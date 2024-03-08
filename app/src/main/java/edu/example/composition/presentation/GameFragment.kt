package edu.example.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.example.composition.databinding.FragmentGameBinding
import edu.example.composition.domain.entity.GameResult

class GameFragment : Fragment() {

    private val args: GameFragmentArgs by navArgs()

    private val level by lazy { args.level }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private val viewModelFactory by lazy {
        GameViewModelFactory(level, requireActivity().application)
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        parseArgs()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setOnClickListenersToOptions()
    }

    private fun observeViewModel() {
        viewModel.question.observe(viewLifecycleOwner) {
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentOfRightAnswer.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
        viewModel.enoughCount.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.setTextColor(getColorByStat(it))
        }
        viewModel.enoughPercent.observe(viewLifecycleOwner) {
            val color = getColorByStat(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswer.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnClickListenersToOptions() {
        for (options in tvOptions) {
            options.setOnClickListener {
                viewModel.chooseAnswer(options.text.toString().toInt())
            }
        }
    }

    private fun getColorByStat(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

//    private fun parseArgs() {
//        level = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            requireArguments().getParcelable(KEY_LEVEL, Level::class.java)!!
//
//        } else {
//            @Suppress("DEPRECATION") requireArguments().getParcelable<Level>(KEY_LEVEL) as Level
//        }
//    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        val action = GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult)
        findNavController().navigate(action)
//        requireActivity().supportFragmentManager.popBackStack()
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
//            .addToBackStack(null)
//            .commit()
    }

    companion object {

//        const val FRAGMENT_NAME = "GameFragment"

//        private const val KEY_LEVEL = "level"
//        fun newInstance(level: Level): GameFragment {
//
//            return GameFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_LEVEL, level)
//                }
//            }
//        }
    }
}
