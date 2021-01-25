package com.example.naturalandfibonacci

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.number_item.view.*


class PrimeRecyclerViewAdapter()
    : NumbersRecyclerViewAdapter() {
    init {
        numbersList.addAll(listOf(2))
        addNumbersToList()
    }

    override fun addNumbersToList() {
        var counter = 0
        var currentNumber = numbersList.last() + 1
        while (counter < 20) {
            if (isPrimeNumber(currentNumber)) {
                numbersList.add(currentNumber)
                counter++
            }
            currentNumber++
        }
        maxPos += 20
    }

    private fun isPrimeNumber(x: Long): Boolean {
        for (i in 2..x / 2)
            if (x % i == 0L)
                return false
        return true
    }

}








