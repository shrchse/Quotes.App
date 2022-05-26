package com.example.quotes_app

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes_app.network.Quotes
import com.example.quotes_app.ui.QuotesApiStatus
import com.example.quotes_app.ui.QuotesListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Quotes>?) {
    val adapter = recyclerView.adapter as QuotesListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: QuotesApiStatus?) {
    when(status) {
        QuotesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        QuotesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        QuotesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}
