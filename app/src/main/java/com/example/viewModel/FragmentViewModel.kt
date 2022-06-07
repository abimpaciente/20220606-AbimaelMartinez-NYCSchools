package com.example.viewModel

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class FragmentViewModel : Fragment() {
    protected val viewModel: NycSchoolsViewModel by activityViewModels()
}