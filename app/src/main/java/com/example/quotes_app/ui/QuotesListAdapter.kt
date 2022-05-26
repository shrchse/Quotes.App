package com.example.quotes_app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes_app.databinding.ListViewItemBinding
import com.example.quotes_app.network.Quotes

class QuotesListAdapter(private val clickListener: QuotesListener) :
    ListAdapter<Quotes, QuotesListAdapter.QuotesViewHolder>(DiffCallback) {

    class QuotesViewHolder(
        private var binding: ListViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: QuotesListener, quotes: Quotes) {
            binding.quotes = quotes
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Quotes>() {

        override fun areItemsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
            return oldItem.a == newItem.a
        }

        override fun areContentsTheSame(oldItem: Quotes, newItem: Quotes): Boolean {
            return oldItem.q == newItem.q
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return QuotesViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quotes = getItem(position)
        holder.bind(clickListener, quotes)
    }
}

class QuotesListener(val clickListener: (quotes: Quotes) -> Unit) {
    fun onClick(quotes: Quotes) = clickListener(quotes)
}
