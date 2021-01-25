package com.example.naturalandfibonacci

import androidx.paging.PositionalDataSource

class FibonacciDataSource() :
    PositionalDataSource<Double>() {
    private val fibonacciNumberList = mutableListOf(0.0, 1.0)
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Double>) {
        callback.onResult(fibonacciNumberList, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Double>) {
        repeat(params.loadSize) {
            val sum = fibonacciNumberList[fibonacciNumberList.size - 1] + fibonacciNumberList[fibonacciNumberList.size - 2]
            fibonacciNumberList.add(sum)
        }
        callback.onResult(fibonacciNumberList.subList(params.startPosition, params.startPosition+params.loadSize))
    }
}