package com.example.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.toast
import com.example.model.SchoolSatResponse
import com.example.model.StateUI
import com.example.nycscools.databinding.ScoreSchoolsFragmentBinding
import com.example.viewModel.FragmentViewModel

class ScoreSchoolsFragment : FragmentViewModel() {

    private val binding by lazy {
        ScoreSchoolsFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initObservers()

        return binding.root
    }


    private fun initObservers() {

        viewModel.scoreLiveData.observe(viewLifecycleOwner) { state ->
            binding.pbLoading.visibility = View.VISIBLE
            val school = viewModel.currentSchool
            when (state) {
                is StateUI.Success<*> -> {
                    val score: SchoolSatResponse? =
                        (state.response as List<SchoolSatResponse>).firstOrNull()
                    if (score == null) {
                        binding.apply {
                            pbLoading.visibility = View.GONE
                        }
                    } else {
                        binding.apply {
                            pbLoading.visibility = View.GONE
                            tvSatScores.text = school?.school_name
                            tvMathScores.text = score.mathAvg
                            tvReadingScores.text = score.readingAvg
                            tvWritingScores.text = score.writingAvg
                        }
                    }
                }
                is StateUI.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        binding.root.context.toast("Error: ${state.error.message}")
                    }
                }
                is StateUI.Loading -> {
                    viewModel.getSchoolsSat(viewModel.currentSchool?.dbn.toString())
                }
                else -> {}
            }
        }
    }

}