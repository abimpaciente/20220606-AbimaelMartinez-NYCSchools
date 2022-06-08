package com.example.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.toast
import com.example.model.School
import com.example.model.StateUI
import com.example.nycscools.R
import com.example.nycscools.databinding.SchoolsListFragmentBinding
import com.example.view.adapters.SchoolsAdapter
import com.example.viewModel.FragmentViewModel

class ItemsSchoolsFragment : FragmentViewModel() {

    private val binding by lazy {
        SchoolsListFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initObservers()

        binding.lyRecyclers.setOnRefreshListener {
            viewModel.getSchools()
        }

        return binding.root
    }


    private fun initObservers() {
        viewModel.schoolLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateUI.Loading -> {
                    binding.lyRecyclers.isRefreshing = true
                }
                is StateUI.Success<*> -> {
                    binding.apply {
                        val schoolModels = state.response as List<School>
                        schoolModels.let {
                            binding.rvSchoolList.adapter = SchoolsAdapter(schoolModels) {
                                onClick(it)
                            }
                        }
                    }
                }
                is StateUI.Error -> {
                    binding.apply {
                        binding.root.context.toast("Error: ${state.error.message}")
                    }
                }
                is StateUI.Message -> {
                    binding.root.context.toast(state.text)
                }
            }
            binding.lyRecyclers.isRefreshing = false
        }
    }

    private fun onClick(schoolModel: School) {
        viewModel.setSchool(schoolModel)
        viewModel.getSchoolsSat(schoolModel)
        parentFragmentManager.beginTransaction()
            .replace(R.id.frg_container, ScoreSchoolsFragment())
            .addToBackStack(null)
            .commit()

    }
}