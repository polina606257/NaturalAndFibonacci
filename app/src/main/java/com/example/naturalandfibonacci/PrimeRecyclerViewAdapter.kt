package com.example.naturalandfibonacci

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*


class PrimeRecyclerViewAdapter: NumbersRecyclerViewAdapter() {
    init {
        numbersList.addAll(listOf(2.0))
        addNumbersToList()
    }

    override fun addNumbersToList() {
        var counter = 0
        var currentNumber = numbersList.last() + 1
        while (counter < 20) {
            if (isPrimeNumber(currentNumber.toInt())) {
                numbersList.add(currentNumber)
                counter++
            }
            currentNumber++
        }
        maxPos += 20
    }

    private fun isPrimeNumber(x: Int): Boolean {
        for (i in 2..x / 2)
            if (x % i == 0)
                return false
        return true
    }

}








