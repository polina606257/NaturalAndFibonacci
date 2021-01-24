package com.example.naturalandfibonacci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(ActivityViewModel::class.java)}
    //val viewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numbersRecyclerView.layoutManager = GridLayoutManager(this, 2)

        fibonacchiButton.setOnClickListener { viewModel.generateFibonacciSequence() }
        naturalButton.setOnClickListener { viewModel.generateNaturalSequence() }
        viewModel.numbersLiveData.observe(this) { numbers ->
            numbers?.let {
                numbersRecyclerView.adapter = viewModel.numbersLiveData.value?.let { listNumbers ->
                    RecyclerViewAdapter(
                        listNumbers
                    )
                }
            }
        }
    }
}