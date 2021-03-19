package com.arny.dataimporter.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arny.dataimporter.R
import com.arny.dataimporter.databinding.DataImportFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DataImportFragment : Fragment(R.layout.data_import_fragment) {

    companion object {
        fun newInstance() = DataImportFragment()
    }

    @Inject
    lateinit var viewModel: DataImportViewModel
    private lateinit var binding: DataImportFragmentBinding

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataImportFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
