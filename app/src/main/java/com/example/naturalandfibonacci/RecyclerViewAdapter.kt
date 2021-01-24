package com.example.naturalandfibonacci

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*

fun fibonacci(n: Int): Long{
    return if (n > 1 )
        fibonacci(n-1)+fibonacci(n-2)
    else if(n==1)
        1
    else
        0
}

class RecyclerViewAdapter ()
    : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = RecyclerViewHolder(inflater, parent)
        return holder
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val number = fibonacci(position)
        holder.bind(number, position)
    }


    class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.number_item, parent, false)) {
        var number: Long? = null

        @SuppressLint("ResourceAsColor")
        fun bind(number: Long, position: Int) {

            if (position % 4 == 0 || (position - 3) % 4 == 0)
                itemView.numberTextView.setBackgroundColor(R.color.design_default_color_on_primary)

            this.number = number
            itemView.numberTextView.text = number.toString()
        }
    }
}








