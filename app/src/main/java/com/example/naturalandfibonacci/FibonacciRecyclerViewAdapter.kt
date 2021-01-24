package com.example.naturalandfibonacci

import android.annotation.SuppressLint
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
        var t1 = 0.0
        var t2 = 1.0

        for (i in 2..20) {
            val sum = t1 + t2
            t1 = t2
            t2 = sum
            fibonacciNumberList.add(t2)
        }
        maxPos = 20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FibonacciRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FibonacciRecyclerViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holderFibonacci: FibonacciRecyclerViewHolder, position: Int) {
            if (position > maxPos - 10) {
                addFibonacciToList()
            }
            if (!isInProcess) {
                val number = fibonacciNumberList[position]
                holderFibonacci.bind(number, position)
            }
        }

    private fun addFibonacciToList() {
        isInProcess = true
        GlobalScope.launch(Dispatchers.Default) {
            for (i in 0 until 20) {
                val sum = fibonacciNumberList[fibonacciNumberList.size - 1].toDouble() + fibonacciNumberList[fibonacciNumberList.size - 2].toDouble()
                fibonacciNumberList.add(sum)
            }
            maxPos += 20
            isInProcess = false
        }
    }


    class FibonacciRecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.number_item, parent, false)) {
        var number: Double? = null

        @SuppressLint("ResourceAsColor")
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



