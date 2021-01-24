package com.example.naturalandfibonacci

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numbersRecyclerView.layoutManager = GridLayoutManager(this, 2)
        numbersRecyclerView.adapter = FibonacciRecyclerViewAdapter()
        fibonacchiButton.setBackgroundColor(resources.getColor(R.color.purple_200))

        primeButton.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            fibonacchiButton.setBackgroundColor(Color.parseColor("#FF6200EE"))
            numbersRecyclerView.adapter = PrimeRecyclerViewAdapter()
        }
        fibonacchiButton.setOnClickListener {
            it.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            primeButton.setBackgroundColor(Color.parseColor("#FF6200EE"))
            numbersRecyclerView.adapter = FibonacciRecyclerViewAdapter()

        }


    }
}