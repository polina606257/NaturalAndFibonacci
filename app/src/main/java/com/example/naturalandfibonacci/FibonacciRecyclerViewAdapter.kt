package com.example.naturalandfibonacci

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class FibonacciRecyclerViewAdapter()
    : RecyclerView.Adapter<FibonacciRecyclerViewAdapter.FibonacciRecyclerViewHolder>() {
    var maxPos = 0
    private val fibonacciNumberList = mutableListOf<Double>()
    var isInProcess: Boolean = false
    var format = DecimalFormat("#,###")

    init {
        fibonacciNumberList.addAll(listOf(0.0, 1.0))
        addFibonacciToList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FibonacciRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FibonacciRecyclerViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holderFibonacci: FibonacciRecyclerViewHolder, position: Int) {
            if (position > maxPos - 10) {
                addFibonacciSync()
            }
            if (!isInProcess) {
                val number = fibonacciNumberList[position]
                holderFibonacci.bind(number, position)
            }
        }

    private fun addFibonacciSync() {
        isInProcess = true
        GlobalScope.launch(Dispatchers.Default) {
           addFibonacciToList()
            isInProcess = false
        }
    }

    private fun addFibonacciToList() {
        for (i in 0 until 20) {
            val sum = fibonacciNumberList[fibonacciNumberList.size - 1] + fibonacciNumberList[fibonacciNumberList.size - 2]
            fibonacciNumberList.add(sum)
        }
        maxPos += 20
    }


    class FibonacciRecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.number_item, parent, false)) {
        var number: Double? = null

        fun bind(number: Double, position: Int) {

            if (position % 4 == 0 || (position - 3) % 4 == 0)
                itemView.numberTextView.setBackgroundColor(Color.parseColor("#2F000000"))
            else
                itemView.numberTextView.setBackgroundColor(Color.parseColor("#ffffff"))
            this.number = number
            itemView.numberTextView.text = number.toString()
        }
    }
}



