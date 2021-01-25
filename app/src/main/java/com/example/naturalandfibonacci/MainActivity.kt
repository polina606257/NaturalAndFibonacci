package com.example.naturalandfibonacci

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    lateinit var adapter: PageAdapter
    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numbersRecyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = PageAdapter()
        adapter.submitList(makePageList(PrimeNumbersDataSource()))
        numbersRecyclerView.adapter = adapter
        primeButton.setBackgroundColor(resources.getColor(R.color.purple_200))

        primeButton.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            fibonacchiButton.setBackgroundColor(Color.parseColor("#FF6200EE"))
            adapter.submitList(makePageList(PrimeNumbersDataSource()))
        }
        fibonacchiButton.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            primeButton.setBackgroundColor(Color.parseColor("#FF6200EE"))
            adapter.submitList(makePageList(FibonacciDataSource()))

        }
    }
    private fun makePageList(dataSource: PositionalDataSource<Double>): PagedList<Double>{
        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(2)
            .setPageSize(50)
            .build()
        val pagedList = PagedList.Builder(dataSource, config)
            .setNotifyExecutor {
                runBlocking(Dispatchers.Main) { it.run() }
            }
            .setFetchExecutor {
                lifecycleScope.launch(Dispatchers.Default) {  it.run()}
            }
            .build()
        return pagedList
    }
}

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

class PrimeNumbersDataSource() :
    PositionalDataSource<Double>() {
    private val primeNumbersList = mutableListOf(2.0)
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Double>) {
        callback.onResult(primeNumbersList, 0)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Double>) {
        for (i in 0 until FibonacciRecyclerViewAdapter.COUNT) {
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