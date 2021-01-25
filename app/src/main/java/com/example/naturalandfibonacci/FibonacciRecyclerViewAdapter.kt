package com.example.naturalandfibonacci

import kotlinx.android.synthetic.main.number_item.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.withLock

class FibonacciRecyclerViewAdapter: NumbersRecyclerViewAdapter() {
    init {
        numbersList.addAll(listOf(0.0, 1.0))
        addNumbersToList()
    }

    override fun addNumbersToList() {
        scope.launch(Dispatchers.Default) {
            mutex.withLock {
                for (i in 0 until COUNT) {
                    val first = numbersList[numbersList.size - 1]
                    val second = numbersList[numbersList.size - 2]
                    val sum = first + second
                    numbersList.add(sum)
                }
                maxPos += COUNT
            }
        }
    }
}



