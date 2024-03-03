package com.example.billformeal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val billEditText: EditText = findViewById(R.id.editTextText)
        val dinersEditText: EditText = findViewById(R.id.editTextText2)
        val total: TextView = findViewById(R.id.textView)

        val btn10: Button = findViewById(R.id.button)
        val btn15: Button = findViewById(R.id.button2)
        val btn18: Button = findViewById(R.id.button3)




        btn10.setOnClickListener { setValue(10, billEditText, dinersEditText, total) }
        btn15.setOnClickListener { setValue(15, billEditText, dinersEditText, total) }
        btn18.setOnClickListener { setValue(18, billEditText, dinersEditText, total) }
    }

    private fun setValue(tip: Int, billEditText: EditText, dinersEditText: EditText, total: TextView) {

        val totalBill = billEditText.text.toString().toDoubleOrNull()?: return
        val totalDiners = dinersEditText.text.toString().toDoubleOrNull()?: return
        val perHeadVal = (totalBill + ((totalBill*tip)/100))/totalDiners


        total.text = "\$${String.format("%.2f", perHeadVal)}"
    }
}