package com.assignment.acronym.ui.fragment.landingfragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.assignment.acronym.R
import com.assignment.acronym.databinding.FragmentLandingBinding
import com.assignment.acronym.ui.fragment.BaseFragment
import com.assignment.acronym.ui.fragment.meaningfragment.MeaningFragment.Companion.PARAM_RESPONSE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : BaseFragment() {

    private val viewModel by viewModels<LandingViewModel>()
    private var binding: FragmentLandingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initListeners() {
        binding?.btnSearch?.setOnClickListener {
            binding?.etSearch?.text?.toString()?.let {
                if (binding?.radioShort?.isChecked == true) {
                    viewModel.fetchByShortForm(it)
                } else if (binding?.radioLong?.isChecked == true) {
                    viewModel.fetchByLongForm(it)
                } else {
                    // no-op
                }
            }
        }
        binding?.etSearch?.addTextChangedListener {
            updateButtonState()
        }
    }

    private fun initObservers() {
        with(viewModel) {
            response.observe(viewLifecycleOwner) {
                if (it?.isNotEmpty() == true) {
                    findNavController().navigate(
                        R.id.action_navigation_landing_to_navigation_meaning, bundleOf(
                            PARAM_RESPONSE to it
                        )
                    )
                } else {
                    showErrorAlertDialog(getString(R.string.not_found))
                }
            }

            loading.observe(viewLifecycleOwner) {
                binding?.progress?.visibility = it
                binding?.etSearch?.isEnabled = it == View.GONE
                binding?.radioLong?.isEnabled = it == View.GONE
                binding?.radioShort?.isEnabled = it == View.GONE
                updateButtonState()
            }
        }
    }

    private fun showErrorAlertDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setPositiveButton(
                getString(R.string.ok)
            ) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun updateButtonState() {
        binding?.btnSearch?.isEnabled =
            viewModel.loading.value == View.GONE && !binding?.etSearch?.text?.toString()
                .isNullOrBlank()
    }

    companion object {
        fun newInstance() = LandingFragment()
    }
}