package com.arny.metersreading.meteredit.presentation.edit

import androidx.fragment.app.Fragment
import com.arny.metersreading.meteredit.R
import javax.inject.Inject

class MetersEditFragment : Fragment(R.layout.fragment_meter_edit) {

    companion object {
        fun newInstance() = MetersEditFragment()
    }

    @Inject
    lateinit var viewmodel: MetersEditViewModel
}
