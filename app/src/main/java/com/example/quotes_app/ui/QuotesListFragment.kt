package com.example.quotes_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quotes_app.R
import com.example.quotes_app.databinding.FragmentQuotesListBinding


class QuotesListFragment : Fragment() {

    private val viewModel: QuotesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentQuotesListBinding.inflate(inflater)
        viewModel.getQuotesList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = QuotesListAdapter(QuotesListener { quotes ->
            viewModel.onQuotesClicked(quotes)
            findNavController()
                .navigate(R.id.action_quotesListFragment_to_quotesDetailFragment)
        })
        return binding.root
    }
}
