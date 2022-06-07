package com.example.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common.toast
import com.example.model.SchoolListResponse
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

        return binding.root
    }


    private fun initObservers() {
        viewModel.schoolLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is StateUI.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is StateUI.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        val schools = state.response as? List<SchoolListResponse>
                        schools?.let {
                            binding.rvSchoolList.adapter = SchoolsAdapter(schools) {
                                onClick(it)
                            }
                        }
                        binding.root.context.toast("Schools ${schools?.size}")
                    }
                }
                is StateUI.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        binding.root.context.toast("Error: ${state.error.message}")
                    }
                }
            }
        }
    }

    private fun onClick(school: SchoolListResponse) {
        viewModel.setSchool(school)
        parentFragmentManager.beginTransaction()
            .replace(R.id.frg_container, ScoreSchoolsFragment())
            .addToBackStack(null)
            .commit()

    }
}