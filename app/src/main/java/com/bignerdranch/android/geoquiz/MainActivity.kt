package com.bignerdranch.android.geoquiz

import Question
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(){

    //lateinit tells compiler you'll store something in the property later
    private lateinit var binding: ActivityMainBinding //From the binding being imported earlier

    private val questionBank = listOf(
        Question(R.string.first_question, true),
        Question(R.string.second_question, true),
        Question(R.string.third_question, false),
        Question(R.string.fourth_question, false)
    )
    private var curIndex = 0
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        // Not necessary when binding is set --> setContentView(R.layout.activity_main) //sets the layout file
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.QuestionsTextView.setText(questionBank[curIndex].textResId) //to get the id of the string and set the string as the text for TextView
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
        }

        binding.QuestionsTextView.setOnClickListener { view: View->
            updateQuestion()
        }

        binding.ButtonNext.setOnClickListener{view : View ->
            updateQuestion()
        }

    }

    private fun updateQuestion(){
        curIndex  = (curIndex + 1) % questionBank.size
        binding.QuestionsTextView.setText(questionBank[curIndex].textResId)
    }


}

