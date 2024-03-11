package com.bignerdranch.android.geoquiz

import Question
import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    init {
        Log.d(TAG, "ViewModel Created")
    }

    private val questionBank = listOf(
        Question(R.string.first_question, true),
        Question(R.string.second_question, true),
        Question(R.string.third_question, false),
        Question(R.string.fourth_question, false)
    )
    fun reset(){
        answeredQuestions = 0
        rightAnswers = 0
        curIndex = 0
        answerArray = arrayOfNulls(questionBank.size)
    }
    private var curIndex = 0
    fun getCurIndex() = curIndex  //returns curIndex
    var answerArray : Array<Boolean?> = arrayOfNulls(questionBank.size)
    private var answeredQuestions = 0
    private var rightAnswers = 0

    fun getRightAnswers() = rightAnswers
    fun getQuestionBank() = questionBank
    val currentQuestionAnswer : Boolean  get() = questionBank[curIndex].answer
    val currentQuestionText : Int get() = questionBank[curIndex].textResId

    fun moveToNext() {
        curIndex = (curIndex + 1) % questionBank.size
    }
    fun moveBackwards(){
        curIndex = (curIndex - 1) % questionBank.size
    }
    fun storeAnswer( ans : Boolean){
        answerArray[curIndex] = ans
    }

    fun increaseAnsweredCount(){
        if(answerArray[curIndex] == null)
            answeredQuestions++
    }
    fun getAnsweredQuestions() = answeredQuestions




    /**
     * The onCleared function is called right before the ViewModel is destroyed
     * This is where a lot of cleanup should be done!
     */
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance being destroyed")
    }
}