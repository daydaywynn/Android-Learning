package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    init {
        Log.d(TAG, "ViewModel Created")
    }

    /**
     * The onCleared function is called right before the ViewModel is destroyed
     * This is where a lot of cleanup should be done!
     */
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance being destroyed")
    }
}