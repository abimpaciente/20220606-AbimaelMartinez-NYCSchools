package com.example.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.toast
import com.example.model.SchoolSat
import com.example.model.StateUI
import com.example.nycscools.R
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


    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        viewModel.scoreLiveData.observe(viewLifecycleOwner) { state ->
            binding.pbLoading.visibility = View.VISIBLE
            val school = viewModel.currentSchoolModel
            when (state) {
                is StateUI.Success<*> -> {
                    val score: SchoolSat? = (state.response as List<SchoolSat>).firstOrNull()
                    binding.tvSchool.text =
                        resources.getString(R.string.school, school?.school_name)
                    if (score == null) {
                        binding.apply {
                            tvMathScores.text = resources.getString(R.string.value_na)
                            tvReadingScores.text = "NA"
                            tvWritingScores.text = "NA"
                            root.context.toast("No data score")
                        }
                    } else {
                        binding.apply {
                            tvMathScores.text = score.mathAvg
                            tvReadingScores.text = score.readingAvg
                            tvWritingScores.text = score.writingAvg
                        }
                    }
                    binding.pbLoading.visibility = View.GONE
                }
                is StateUI.Error -> {
                    binding.apply {
                        binding.root.context.toast("Error: ${state.error.message}")
                        pbLoading.visibility = View.GONE
                    }
                }
                is StateUI.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }

}