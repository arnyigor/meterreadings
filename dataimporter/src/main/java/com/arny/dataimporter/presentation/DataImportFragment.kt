package com.arny.dataimporter.presentation

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.arny.androidutils.extentions.requestPermission
import com.arny.androidutils.extentions.toast
import com.arny.dataimporter.R
import com.arny.dataimporter.databinding.DataImportFragmentBinding
import com.arny.metersreading.core.models.DataResult
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class DataImportFragment : Fragment(R.layout.data_import_fragment) {

    @Inject
    lateinit var viewModel: DataImportViewModel
    private lateinit var binding: DataImportFragmentBinding

    private fun launchGetFile() = getFile.launch("*/*")

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                launchGetFile()
            }
        }

    private val getFile =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            viewModel.readFile(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

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
        binding.btnChooseFile.setOnClickListener {
            requestPermission(
                requestPermissionLauncher,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                ::launchGetFile
            )
        }
        viewModel.data.observe(viewLifecycleOwner, { result ->
            when (result) {
                is DataResult.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.tvProgress.isVisible = false
                    binding.tvResult.text = result.data
                }
                is DataResult.ERROR -> {
                    binding.progressBar.isVisible = false
                    binding.tvProgress.isVisible = false
                    toast(result.exception.toString(requireContext()))
                }
                DataResult.NOTHING -> {
                    binding.progressBar.isVisible = false
                    binding.tvProgress.isVisible = false
                }
                is DataResult.PROGRESS -> {
                    binding.progressBar.isVisible = true
                    val message = result.toString(requireContext())
                    binding.tvProgress.isVisible = message.isNullOrBlank().not()
                    binding.tvProgress.text = message
                }
            }
        })
    }
}
