package com.example.naturalandfibonacci

import androidx.paging.PositionalDataSource

class PrimeNumbersDataSource: PositionalDataSource<Double>() {
    private val primeNumbersList = mutableListOf(2.0)

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Double>) {
        callback.onResult(primeNumbersList, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Double>) {
        repeat(params.loadSize) {
            var counter = 0
            var currentNumber = primeNumbersList.last() + 1
            while (counter < 20) {
                if (isPrimeNumber(currentNumber.toInt())) {
                    primeNumbersList.add(currentNumber)
                    counter++
                }
                currentNumber++
            }
        }
        callback.onResult(primeNumbersList.subList(params.startPosition, params.startPosition+params.loadSize))
    }
    private fun isPrimeNumber(x: Int): Boolean {
        for (i in 2..x / 2)
            if (x % i == 0)
                return false
        return true
    }
}