package com.example.bodymasssss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bodymasssss.ui.theme.BodymasssssTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodymasssssTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Bmi(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Bmi(modifier: Modifier = Modifier) {
    var heightInput by remember { mutableStateOf(TextFieldValue("")) }
    var weightInput by remember { mutableStateOf(TextFieldValue("")) }

    val height = heightInput.text.replace(",", ".").toFloatOrNull() ?: 0f
    val weight = weightInput.text.toIntOrNull() ?: 0
    val bmi = if (height > 0f && weight > 0) weight / (height * height) else 0f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.bmi_calculator_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it },
            label = { Text(stringResource(id = R.string.enter_height)) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it },
            label = { Text(stringResource(id = R.string.enter_weight)) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Text(text = "body mass index is" + bmi)
        Text(
            text = stringResource(id = R.string.bmi_result).format(bmi),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BmiPreview() {
    BodymasssssTheme {
        Bmi()
    }
}
