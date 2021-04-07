package com.arny.metersreading.meteredit.presentation.edit

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.arny.androidutils.extentions.toast
import com.arny.metersreading.meteredit.R
import com.arny.metersreading.meteredit.databinding.FragmentMeterEditBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MetersEditFragment : Fragment(R.layout.fragment_meter_edit) {

    private val args by navArgs<MetersEditFragmentArgs>()

    @Inject
    lateinit var viewmodel: MetersEditViewModel

    private lateinit var binding: FragmentMeterEditBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
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
        viewmodel.setMeter(args.meter)
        initUI()
        observeData()
    }

    private fun observeData() {
        viewmodel.toast.observe(viewLifecycleOwner, { string ->
            string?.let {
                toast(string.toString(requireContext()))
            }
        })
        viewmodel.uiMeter.observe(viewLifecycleOwner, { meter ->
            binding.tvMeter.text = meter?.title
        })
        viewmodel.loading.observe(viewLifecycleOwner, { loading ->
            binding.pbLoading.isVisible = loading
            binding.btnSave.isEnabled = !loading
        })
        viewmodel.save.observe(viewLifecycleOwner, { save ->
            if (save) {
                requireActivity().onBackPressed()
            }
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
