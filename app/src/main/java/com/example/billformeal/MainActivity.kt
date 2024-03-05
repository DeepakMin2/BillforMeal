package com.example.billformeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.billformeal.ui.theme.Purple80
import com.example.billformeal.ui.theme.PurpleGrey40
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.billformeal.ui.theme.PurpleGrey80

class MainActivity : ComponentActivity() {

// This is Kotlin program for the sam Bill for Meal Application
    private var perHeadVal by  mutableStateOf("$0.00")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var totalBill by remember { mutableStateOf("") }
            var diners by remember { mutableStateOf("") }

            Surface(border = BorderStroke(4.dp, Purple80), modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row() {
                        finalVal(perHeadVal)
                    }
                    //Thi is an added comment to JetPack Branch
                    Column {
                        numDinersEditText(diners = diners) {diners = it}
                        billEditText(bill = totalBill) {totalBill = it}
                    }
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                        button10(diners = diners, totalBill = totalBill)
                        button15(diners = diners,totalBill= totalBill)
                        button18(diners= diners, totalBill = totalBill)
                    }
                }
            }
        }

    }

    @Composable
    private fun finalVal(perHeadVal: String) {
        ElevatedCard(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.wrapContentSize(),
            colors = CardDefaults.cardColors(containerColor = PurpleGrey80)
        ) {
            Text(text = perHeadVal , fontSize = 50.sp )
        }
    }

    @Composable
    private fun button15(tip: Int = 15, diners: String, totalBill: String) {
        ElevatedButton(onClick = { setPriceValue(tip,diners,totalBill) },
            modifier = Modifier.wrapContentSize(),
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Purple80)
        ) {
            Text(text = "15%")
        }
    }

    @Composable
    private fun button18(tip: Int = 18, diners: String, totalBill: String) {
        FilledTonalButton(onClick = { setPriceValue(tip, diners, totalBill) },
            modifier = Modifier.wrapContentSize()
        ) {
            Text(text = "18%")
        }
    }

    @Composable
    private fun button10(tip: Int = 10, diners: String, totalBill: String){
        Button(onClick = { setPriceValue(tip,diners, totalBill) }, modifier = Modifier.wrapContentSize()) {
            Text(text = "10%")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun numDinersEditText(diners: String, onNumDinersChange: (String) -> Unit) {


        OutlinedTextField(
            value = diners,
            onValueChange = { newValue-> onNumDinersChange(newValue)},
            label = { Text(text = "Number of Diners") }
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun billEditText(bill: String, onBillChange: (String)->Unit) {


        OutlinedTextField(
            value = bill,
            onValueChange = { newValue -> onBillChange(newValue) },
            label = { Text("Total bill ") }
        )
    }


    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    private fun buttonPreview(){
        var bill by remember { mutableStateOf("") }
        var diners by remember { mutableStateOf("") }

        Surface(border = BorderStroke(4.dp, Purple80)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row() {
                    finalVal(perHeadVal)
                }
                Column {
                    numDinersEditText(diners = diners) {diners = it}
                    billEditText(bill = bill) {bill = it}
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    button10(diners = diners, totalBill = bill)
                    button15(diners = diners, totalBill = bill)
                    button18(diners = diners, totalBill = bill)
                }
            }
        }
    }

    private fun setPriceValue(tip: Int, diners: String, totalBill: String) {
        var headVal : Double = 0.0
        var dinersInt: Double? = diners.toDoubleOrNull()
        var totalBillInt: Double? = totalBill.toDoubleOrNull()

        if (totalBillInt != null && dinersInt!= null) {
            headVal = (totalBillInt + ((totalBillInt/100)*tip))/dinersInt
        }

       perHeadVal = String.format("\$%.2f",headVal)

    }

}



