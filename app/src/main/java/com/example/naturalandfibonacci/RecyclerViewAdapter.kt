package com.example.naturalandfibonacci

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun fibonacci(n: Int): Long {
    return if (n > 1)
        fibonacci(n - 1) + fibonacci(n - 2)
    else if (n == 1)
        1
    else
        0
}


class RecyclerViewAdapter()
    : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    var maxPos = 0
    val list = mutableListOf<Double>()
    val primeNumbersList = mutableListOf<Int>(1)
    var isInProcess: Boolean = false

    init {
        list.addAll(listOf(0.0, 1.0))
        var t1 = 0.0
        var t2 = 1.0

        for (i in 2..200) {
            val sum = t1 + t2
            t1 = t2
            t2 = sum
            list.add(t2)
        }
        maxPos = 20
    }

    fun addFibonaciToList() {
        isInProcess = true
        GlobalScope.launch(Dispatchers.Default) {
            for (i in 0 until 20) {
                val sum = list[list.size - 1] + list[list.size - 2]
                list.add(sum)
            }
            maxPos += 20
            isInProcess = false
        }
    }

    fun addPrimeToList() {
        isInProcess = true

        var counter = 0
        var currentNumber = primeNumbersList.last() + 1
        while (counter < 20) {
            if (isPrimeNumber(currentNumber)) {
                primeNumbersList.add(currentNumber)
                counter++
            }
            currentNumber++
        }
        maxPos += 20
        isInProcess = false

    }

    fun isPrimeNumber(x: Int): Boolean {
        for (i in 2..x / 2)
            if (x % i == 0)
                return false
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = RecyclerViewHolder(inflater, parent)
        return holder
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        if (position > maxPos - 10) {
            addFibonaciToList()
        }
        if (!isInProcess) {
            val number = list[position]
            holder.bind(number, position)
        }
    }


    class RecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.number_item, parent, false)) {
        var number: Double? = null

        @SuppressLint("ResourceAsColor")
        fun bind(number: Double, position: Int) {

            if (position % 4 == 0 || (position - 3) % 4 == 0)
                itemView.numberTextView.setBackgroundColor(R.color.black)
            else
                itemView.numberTextView.setBackgroundColor(R.color.white)
            this.number = number
            itemView.numberTextView.text = number.toString()
        }
    }
}








