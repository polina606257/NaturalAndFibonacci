package com.example.naturalandfibonacci

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class NumbersRecyclerViewAdapter :
    RecyclerView.Adapter<NumbersRecyclerViewAdapter.NumberViewHolder>() {
    companion object{
        const val COUNT = 50
        const val WHEN_TO_LOAD = 25
    }
    val scope = CoroutineScope(Job())
    var maxPos = 0
    protected val numbersList = mutableListOf<Double>()
    val mutex = Mutex()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return NumberViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holderFibonacci: NumberViewHolder, position: Int) {
        if (position > maxPos - WHEN_TO_LOAD) {
            addNumbersToList()
        }
        scope.launch(Dispatchers.Main) {
            mutex.withLock {
                val number = numbersList[position]
                holderFibonacci.bind(number, position)
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        scope.cancel()
    }

    protected abstract fun addNumbersToList()

    class NumberViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.number_item, parent, false)) {
        var number: Double? = null

        fun bind(number: Double, position: Int) {

            if (position % 4 == 0 || (position - 3) % 4 == 0)
                itemView.numberTextView.setBackgroundColor(Color.parseColor("#2F000000"))
            else
                itemView.numberTextView.setBackgroundColor(Color.parseColor("#ffffff"))
            this.number = number
            if (number < Long.MAX_VALUE && number >= 0)
                itemView.numberTextView.text = number.toLong().toString()
            else if (number < Double.MAX_VALUE)
                itemView.numberTextView.text = number.toString()
            else
                itemView.numberTextView.text = "слишком много"
        }
    }
}