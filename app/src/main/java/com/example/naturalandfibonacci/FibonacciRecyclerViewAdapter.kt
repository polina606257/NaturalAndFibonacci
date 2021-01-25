package com.example.naturalandfibonacci

import kotlinx.android.synthetic.main.number_item.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.withLock

class FibonacciRecyclerViewAdapter()
    : NumbersRecyclerViewAdapter() {
    init {
        numbersList.addAll(listOf(0, 1))
        addNumbersToList()
    }

    override fun addNumbersToList() {
        scope.launch(Dispatchers.Default) {
            mutex.withLock {
                for (i in 0 until COUNT) {
                    val first = numbersList[numbersList.size - 1]
                    val second = numbersList[numbersList.size - 2]
                    val sum = if (first<Long.MAX_VALUE||second<Long.MAX_VALUE)
                        first + second
                    else
                        -1
                    val sumTest = first.toDouble()+second.toDouble()
                    if (sumTest<Long.MAX_VALUE)
                        numbersList.add(sum)
                    else
                        numbersList.add(-1)
                }
                maxPos += COUNT
            }
        }
    }
}



