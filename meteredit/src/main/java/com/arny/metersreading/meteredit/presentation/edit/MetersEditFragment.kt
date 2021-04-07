package com.arny.metersreading.meteredit.presentation.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.arny.androidutils.extentions.toast
import com.arny.metersreading.meteredit.R
import com.arny.metersreading.meteredit.databinding.FragmentMeterEditBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MetersEditFragment : Fragment(R.layout.fragment_meter_edit) {

    @Inject
    lateinit var viewmodel: MetersEditViewModel

    private lateinit var binding: FragmentMeterEditBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeterEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeData()
    }

    private fun observeData() {
        viewmodel.toast.observe(viewLifecycleOwner, { string ->
            string?.let {
                toast(string.toString(requireContext()))
            }
        })
        viewmodel.loading.observe(viewLifecycleOwner, { loading ->
            binding.pbLoading.isVisible = loading
        })
        viewmodel.save.observe(viewLifecycleOwner, {
            requireActivity().onBackPressed()
        })
    }

    private fun initUI() {
        binding.tvMonth.setOnClickListener {
            toast("Показываем диалог месяца")
        }
        binding.btnSave.setOnClickListener {
            viewmodel.saveData()
        }
    }
}
