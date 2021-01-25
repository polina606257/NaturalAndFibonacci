package com.example.naturalandfibonacci

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil



class NumberDiffCallback : DiffUtil.ItemCallback<Double>() {
    override fun areItemsTheSame(oldItem: Double, newItem: Double): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Double, newItem: Double): Boolean {
        return oldItem == newItem
    }
}
class PageAdapter : PagedListAdapter<Double, FibonacciRecyclerViewAdapter.FibonacciRecyclerViewHolder>(
        NumberDiffCallback()
) {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): FibonacciRecyclerViewAdapter.FibonacciRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FibonacciRecyclerViewAdapter.FibonacciRecyclerViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(
            holder: FibonacciRecyclerViewAdapter.FibonacciRecyclerViewHolder,
            position: Int
    ) {
        holder.bind(getItem(position)?:0.0, position)
    }
}