package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tipPercentages = arrayOf("10%", "15%", "20%", "25%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinnerTipPercentage)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipPercentages)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val textViewResultRounded = findViewById<TextView>(R.id.textViewResultRounded)
        val editTextTotal = findViewById<EditText>(R.id.editTextTotal)

        calculateButton.setOnClickListener(View.OnClickListener {
            val totalText = editTextTotal.text.toString()
            if (totalText.isNotEmpty()) {
                val total = totalText.toDouble()
                val selectedPercentage = tipPercentages[spinner.selectedItemPosition].replace("[^0-9]".toRegex(), "").toInt()
                val tipAmount = (total * selectedPercentage) / 100.0

                val formattedTipAmount = String.format("%.2f", tipAmount)

                textViewResult.text = "Tip Amount: $$formattedTipAmount"

                // Calculate and display the rounded tip amount to the nearest dollar
                val roundedTipAmount = Math.round(tipAmount)
                textViewResultRounded.text = "Rounded Tip Amount: $$roundedTipAmount"
            } else {
                textViewResult.text = "Please enter the total bill amount."
                textViewResultRounded.text = ""
            }
        })
    }
}
