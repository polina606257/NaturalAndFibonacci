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



