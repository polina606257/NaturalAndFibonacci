package com.example.naturalandfibonacci

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class FibonacciRecyclerViewAdapter()
    : RecyclerView.Adapter<FibonacciRecyclerViewAdapter.FibonacciRecyclerViewHolder>() {
    companion object{
        const val COUNT = 50
        const val WHEN_TO_LOAD = 25
    }
    val scope = CoroutineScope(Job())
    var maxPos = 0
    private val fibonacciNumberList = mutableListOf<Double>()
    val mutex = Mutex()

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
        if (position > maxPos - WHEN_TO_LOAD) {
            addFibonacciToList()
        }
        scope.launch(Dispatchers.Main) {
            mutex.withLock {
                val number = fibonacciNumberList[position]
                holderFibonacci.bind(number, position)
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        scope.cancel()
    }

    private fun addFibonacciToList() {
        scope.launch(Dispatchers.Default) {
            mutex.withLock {
                for (i in 0 until COUNT) {
                    val sum = fibonacciNumberList[fibonacciNumberList.size - 1] + fibonacciNumberList[fibonacciNumberList.size - 2]
                    fibonacciNumberList.add(sum)
                }
                maxPos += COUNT
            }
        }
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
            if (number<Long.MAX_VALUE)
                itemView.numberTextView.text = number.toLong().toString()
            else if (number<Double.MAX_VALUE)
                itemView.numberTextView.text = number.toString()
            else
                itemView.numberTextView.text = "слишком много"
        }
    }
}



