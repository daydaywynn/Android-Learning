package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(){

    //lateinit tells compiler you'll store something in the property later
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //sets the layout file

        /*
        * Get the reference to the buttons
        * and store them in the above variables
        */
        trueButton = findViewById(R.id.ButtonTrue) //True Button
        falseButton = findViewById(R.id.ButtonFalse) //False Button

        //Set listeners inside of the onCreate
        trueButton.setOnClickListener{view : View -> //'view' is the button that its listening to!
            /*
            * Snackbars and Toasts can be used to do the same thing, but snackbars have a bit more functionality.
            * This is important to keep in mind
            * */
            Snackbar.make(view,R.string.toast_true, Snackbar.LENGTH_LONG).show() //Snackbar!
            println(view.id.toString())
            println(R.id.ButtonTrue)
        }

        falseButton.setOnClickListener{view : View ->
            Toast.makeText(
                this,
                R.string.toast_false,
                Toast.LENGTH_LONG
            ).show() //Toast
        }
    }


}

