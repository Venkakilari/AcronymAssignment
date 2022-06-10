package com.assignment.acronym.ui.fragment.meaningfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.assignment.acronym.adapter.AcronymMeaningRecyclerViewAdapter
import com.assignment.acronym.databinding.FragmentMeaningBinding
import com.assignment.acronym.ui.fragment.BaseFragment
import com.assignment.data.model.networkmodel.AcronymResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeaningFragment : BaseFragment() {

    private var binding: FragmentMeaningBinding? = null

    private val acronymMeaningRecyclerViewAdapter= AcronymMeaningRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeaningBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initViews() {
        binding?.rvMeanings?.adapter = acronymMeaningRecyclerViewAdapter

        (requireArguments().getSerializable(PARAM_RESPONSE) as? List<String>)?.let {
            acronymMeaningRecyclerViewAdapter.setData(it)
        }
    }

    companion object {
        const val PARAM_RESPONSE = "response"
    }
}