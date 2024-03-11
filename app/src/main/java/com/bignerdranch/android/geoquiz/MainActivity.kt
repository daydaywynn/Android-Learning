package com.bignerdranch.android.geoquiz

import Question
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Log modes: Error, Warning, Info, Debug, Verbose
 * Log.e(..) for Errors
 * Log.w(..) for Warnings
 * Log.i(..) for Info
 * Log.d(..) for Debugs
 * Log.v(..) for Verbose (Development only)
 */
private const val TAG : String = "MainActivity"
class MainActivity : AppCompatActivity(){

    //lateinit tells compiler you'll store something in the property later
    private lateinit var binding: ActivityMainBinding //From the binding being imported earlier
    private  val quizViewModel : QuizViewModel by viewModels() //Associating the activity with a ViewModel
    private val questionBank = listOf(
        Question(R.string.first_question, true),
        Question(R.string.second_question, true),
        Question(R.string.third_question, false),
        Question(R.string.fourth_question, false)
    )
    private var curIndex = 0
    private var answerArray : Array<Boolean?> = arrayOfNulls(questionBank.size)
    private var answeredQuestions = 0
    private var rightAnswers = 0
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)

        Log.d(TAG, "OnCreate(Bundle?) called")
        // Not necessary when binding is set --> setContentView(R.layout.activity_main) //sets the layout file
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.QuestionsTextView.setText(questionBank[curIndex].textResId) //to get the id of the string and set the string as the text for TextView
        Log.d(TAG, "Got a view model $quizViewModel")


        //Set listeners inside of the onCreate
        binding.ButtonTrue.setOnClickListener{view : View -> //'view' is the button that its listening to!
            /*
            * Snackbars and Toasts can be used to do the same thing, but snackbars have a bit more functionality.
            * This is important to keep in mind
            * */
            if(!questionBank[curIndex].checkAnswer(true)){
                Snackbar.make(
                    view,
                    R.string.toast_false,
                    Snackbar.LENGTH_LONG
                ).show() //Toast
            }
            else{
                Snackbar.make(
                    view,
                    R.string.toast_true,
                    Snackbar.LENGTH_LONG
                ).show() //Toast
            }
            answerArray[curIndex] = true
            binding.ButtonTrue.isClickable = false
            binding.ButtonFalse.isClickable = true
            answeredQuestions++
            checkIfFinished(view)
        }

        binding.ButtonFalse.setOnClickListener{view : View ->
            if(!questionBank[curIndex].checkAnswer(false)){
                Snackbar.make(
                    view,
                    R.string.toast_false,
                    Toast.LENGTH_LONG
                ).show() //Toast
            }
            else{
                Snackbar.make(
                    view,
                    R.string.toast_true,
                    Toast.LENGTH_LONG
                ).show() //Toast
            }
            binding.ButtonFalse.isClickable = false
            binding.ButtonTrue.isClickable = true

            answeredQuestion()
            answerArray[curIndex] = false
            answeredQuestions++
            checkIfFinished(view)
        }

        binding.QuestionsTextView.setOnClickListener { view: View->
            updateQuestion('+')
        }

        binding.ButtonNext.setOnClickListener{view : View ->
            updateQuestion('+')
        }

        binding.ButtonPrev.setOnClickListener{view : View ->
            updateQuestion('-')

        }

    }

    private fun checkIfFinished(view: View) {
        if (answeredQuestions.equals(questionBank.size)) {
            Snackbar.make(
                view,
                "Your score is ${(rightAnswers / questionBank.size).toDouble()}%",
                Snackbar.LENGTH_LONG
            ).show()
            resetAll()
        }
    }

    override fun onStart(){
        super.onStart()
        resetButtons()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }



    private fun updateQuestion(direction : Char){
        if(direction.equals('+')) {
            curIndex = (curIndex + 1) % questionBank.size
            binding.QuestionsTextView.setText(questionBank[curIndex].textResId)
        }
        else{
            if(curIndex.equals(0)){
                curIndex = questionBank.size-1
            }
            else{
                curIndex = (curIndex - 1) % questionBank.size
            }
            binding.QuestionsTextView.setText(questionBank[curIndex].textResId)

        }
        resetButtons()
        if(answerArray[curIndex] != null){ //question has an answer
            answeredQuestion()
            binding.ButtonTrue.isClickable = false
            binding.ButtonFalse.isClickable = false
        }
    }

    private fun resetButtons(){
        binding.ButtonNext.isClickable = false
        binding.ButtonPrev.isClickable = false
        binding.ButtonTrue.isClickable = true
        binding.ButtonFalse.isClickable = true
    }

    private fun answeredQuestion() {
        binding.ButtonNext.isClickable = true
        binding.ButtonPrev.isClickable = true
    }

    private fun resetAll(){
        resetButtons()
        answeredQuestions = 0
        rightAnswers = 0
        curIndex = 0
        binding.QuestionsTextView.setText(questionBank[curIndex].textResId)
        answerArray = arrayOfNulls(questionBank.size)
    }


}

