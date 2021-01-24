package com.example.naturalandfibonacci

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*


class PrimeRecyclerViewAdapter()
    : RecyclerView.Adapter<PrimeRecyclerViewAdapter.PrimeRecyclerViewHolder>() {
    var maxPos = 0
    private val primeNumbersList = mutableListOf<Int>(2)
    var isInProcess: Boolean = false

    private fun addPrimeToList() {
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

    private fun isPrimeNumber(x: Int): Boolean {
        for (i in 2..x / 2)
            if (x % i == 0)
                return false
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrimeRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PrimeRecyclerViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holderPrime: PrimeRecyclerViewHolder, position: Int) {
        if (position > maxPos - 10) {
          addPrimeToList()
        }
        if (!isInProcess) {
            val number = primeNumbersList[position]
            holderPrime.bind(number, position)
        }
    }


    class PrimeRecyclerViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.number_item, parent, false)) {
        var number: Int? = null

        fun bind(number: Int, position: Int) {

            if (position % 4 == 0 || (position - 3) % 4 == 0)
                itemView.numberTextView.setBackgroundColor(Color.parseColor("#2F000000"))
            else
                itemView.numberTextView.setBackgroundColor(Color.parseColor("#ffffff"))
            this.number = number
            itemView.numberTextView.text = number.toString()
        }
    }
}








