package com.example.naturalandfibonacci

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*


class NumberDiffCallback : DiffUtil.ItemCallback<Double>() {
    override fun areItemsTheSame(oldItem: Double, newItem: Double): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Double, newItem: Double): Boolean {
        return oldItem == newItem
    }
}

class PageAdapter : PagedListAdapter<Double, NumberViewHolder>(NumberDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NumberViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(getItem(position)?:0.0, position)
    }
}

class NumberViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.number_item, parent, false)) {
    var number: Double? = null

    fun bind(number: Double, position: Int) {

        if (position % 4 == 0 || (position - 3) % 4 == 0)
            itemView.numberTextView.setBackgroundColor(Color.parseColor("#2F000000"))
        else
            itemView.numberTextView.setBackgroundColor(Color.parseColor("#ffffff"))
        this.number = number
        if (number<Long.MAX_VALUE)
            itemView.numberTextView.text = number.toLong().toString()
        else if (number<Double.MAX_VALUE)
            itemView.numberTextView.text = number.toString()
        else
            itemView.numberTextView.text = "слишком много"
    }
}