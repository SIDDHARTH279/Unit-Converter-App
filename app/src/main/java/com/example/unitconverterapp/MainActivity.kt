package com.example.unitconverterapp

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverterapp.ui.theme.UNITCONVERTERAPPTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UNITCONVERTERAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var oconversionFactor = remember { mutableStateOf(1.00) }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Black
    )

    Surface(color = Color.White,
        modifier = Modifier.fillMaxSize()) {
        fun convertUnits(){

            //?: Elvis Operator
            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result = (inputValueDouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt() / 100.0
            outputValue = result.toString()
        }

        Column(modifier = modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            Text(text = "Unit Converter",
                style = customTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()

                    // HERE GOES WHAT SHOULD HAPPEN WHEN OUR VALUE OUTLINED TEXT FIELD CHANGES})
                },
                label = { Text(text = "Enter Value")})

            Spacer(modifier = Modifier.height(16.dp))


            Row {

                //Input Box
                Box{
                    //Input Button
                    Button(onClick = {iExpanded = true}) {
                        Text(inputUnit)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }

                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false} ) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                                convertUnits()
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Meters") },
                            onClick = {iExpanded = false
                                inputUnit = "Meters"
                                conversionFactor.value = 1.0
                                convertUnits()})

                        DropdownMenuItem(text = {
                            Text("Feet") },
                            onClick = {iExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.3048
                                convertUnits()})

                        DropdownMenuItem(text = {
                            Text("Millimeters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Millimeters"
                                conversionFactor.value = 0.001
                                convertUnits()
                            })
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                //Output Box
                Box{
                    //Output Button
                    Button(onClick = {oExpanded = true}) {
                        Text(outputUnit)
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false} ) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oconversionFactor.value = 0.01
                            convertUnits()})

                        DropdownMenuItem(text = { Text("Meters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oconversionFactor.value = 1.0
                            convertUnits()
                        })

                        DropdownMenuItem(text = { Text("Feet") }, onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oconversionFactor.value = 0.3048
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oconversionFactor.value = 0.001
                            convertUnits()
                        })
                    }

                }


            }

            Spacer(modifier = Modifier.height(16.dp))
            //Result Text
            Text(text = "Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
        }

    }













}




@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter(modifier = Modifier)


}