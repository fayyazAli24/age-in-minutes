package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    var textViewShow:TextView? = null
    var mins:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button:Button = findViewById(R.id.button);
        textViewShow = findViewById(R.id.tView)
        mins = findViewById(R.id.mins)
        button.setOnClickListener {
            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        val myCalander = Calendar.getInstance()
        val year= myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)

        val dpd= // on click listner on the button with lamda function passed with four arguments
            DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view ,selectedYear,selectedMonth,selectedDay->
                Toast.makeText(this ,
                    "the year is ${selectedYear} the month is ${selectedMonth+1} and the day is ${selectedDay}",Toast.LENGTH_LONG).show();

                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"

                textViewShow?.setText(selectedDate)


//            now we need the date in date format to calculate the time in minutes

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate= sdf.parse(selectedDate)

//                checking if the date is not empty
                theDate?.let {
                    //         gives the time between the selected date and 1st january 1970
                    val selectedDateInMinutes = theDate.time/60000

//          gives the time between now and the date of 1st jan 1970
                    val currentTime = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentTime?.let {

                        val currentTimeInMinutes= currentTime.time/60000

                        val differnceInMinutes = currentTimeInMinutes-selectedDateInMinutes
                        mins?.setText(differnceInMinutes.toString())
                    }
                }
            },year,month,day)

        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000
        dpd.show()
    }
}