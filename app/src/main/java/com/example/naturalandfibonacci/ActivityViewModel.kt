package com.example.naturalandfibonacci

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ActivityViewModel : ViewModel() {
    private val listNumbers = mutableListOf<Long>()
    private val _numbersLiveData = MutableLiveData<MutableList<Long>>()
    val numbersLiveData: LiveData<MutableList<Long>> = _numbersLiveData
    //f(n)=f(n-1)+f(n-2)  f(2) = f(1)+f(0) = 1 + 0



    fun generateFibonacciSequence() = sequence<Int> {
        listNumbers.addAll(listOf(0, 1))
        var t1: Long = 0
        var t2: Long = 1

        while (true) {
            val sum = t1 + t2
            t1 = t2
            t2 = sum
            listNumbers.add(t2)
            _numbersLiveData.value = listNumbers
        }
    }

//    fun fibonacci() = sequence<Int> {
//        var terms = Pair(0, 1)
//
//        while (true) {
//            GlobalScope.launch {
//                generateSequence(
//                    Pair(0, 1),
//                    { Pair(it.second, it.first + it.second) }).map { it.first }
//                listNumbers.add(terms.second.toLong())
//                _numbersLiveData.value = listNumbers
//            }
//        }
//        listNumbers.addAll(listOf(0, 1))
//        var terms = Pair(0, 1)
//
//        while (true) {
//            yield(terms.first)
//            terms = Pair(terms.second, terms.first + terms.second)
//            listNumbers.add(terms.second.toLong())
//            _numbersLiveData.value = listNumbers
//        }


        fun generateNaturalSequence() {

            listNumbers.clear()
            listNumbers.addAll(listOf(2, 3))
            var t1: Long = 4

                viewModelScope.launch {
                while (true) {
                    if ((t1 % 2).toInt() == 0 || (t1 % 3).toInt() == 0)
                        t1 += 1
                    else {
                        listNumbers.add(t1)
                        _numbersLiveData.value = listNumbers
                        t1 += 1
                    }
                }
            }
        }
    }
