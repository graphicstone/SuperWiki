package com.nullbyte.superwiki.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nullbyte.superwiki.R
import com.nullbyte.superwiki.adapters.SearchListAdapter
import com.nullbyte.superwiki.base.BaseFragment
import com.nullbyte.superwiki.databinding.FragmentSearchResultBinding
import com.nullbyte.superwiki.models.Result
import com.nullbyte.superwiki.utilities.DisplayUtils
import com.nullbyte.superwiki.viewModels.SearchResultViewModel

class SearchResultFragment : BaseFragment() {

    private lateinit var viewModel: SearchResultViewModel
    private lateinit var searchResultBinding: FragmentSearchResultBinding
    private lateinit var mAlertDialog: AlertDialog

    override fun getRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchResultBinding = FragmentSearchResultBinding.inflate(layoutInflater)
        return searchResultBinding.root
    }

    override fun initializeViews(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        val searchedString = bundle!!.getString("searchedString")
        viewModel = ViewModelProvider(this).get(SearchResultViewModel::class.java)
        viewModel.getSuperheroByName(searchedString!!)
        mAlertDialog = DisplayUtils.createLoaderDialog(requireActivity())
        mAlertDialog.show()
        observerData()
    }

    private fun observerData() {
        viewModel.searchedResultDetail.observe(requireActivity(), Observer { searchResult ->
            if (searchResult.response == "error") {
                mAlertDialog.dismiss()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_result_found),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                searchResultBinding.tvSearchedName.text = searchResult.resultsFor
                initRecyclerView(searchResult.results)
            }
        })
    }

    private fun initRecyclerView(results: List<Result>) {
        searchResultBinding.rvSearchResults.layoutManager = LinearLayoutManager(requireContext())
        searchResultBinding.rvSearchResults.adapter = SearchListAdapter(results)
        mAlertDialog.dismiss()
    }

    override fun onFragmentStart() {
    }

    override fun onFragmentStop() {
    }

    override fun onFragmentResume() {
    }

    override fun onFragmentPause() {
    }

    override fun onFragmentDestroy() {
    }
}
