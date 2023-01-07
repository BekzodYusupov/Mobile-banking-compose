package uz.gita.mobilebankingcompose.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.gita.mobilebankingcompose.ui.theme.MobileBankingComposeTheme

/**
Created: Bekzod Yusupov
Project: Mobile Banking Compose
Date: 2023/01/07
Time: 14:30
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    label:String,
    onValueChange: (String) -> Unit,
    prefix:String = "+998 "

) {
    var hasFocus by rememberSaveable { mutableStateOf(false) }

    MobileBankingComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            OutlinedTextField(
                value = value,
                label = { if (value.isEmpty() && !hasFocus) Text(text = label, color = Color(16, 16, 16, 77)) },
                onValueChange = { onValueChange(it) },
                leadingIcon = { Text(text = prefix, modifier = Modifier.padding(start = 16.dp)) },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxWidth()
                    .padding(16.dp)
                    .onFocusChanged { hasFocus = it.isFocused },
                singleLine = true,
                isError = false,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(56, 98, 248),
                    containerColor = if (hasFocus) Color.Transparent else Color(244, 244, 244)

                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomOutlinedTextFieldPre() {
    CustomOutlinedTextField("","00 000 00 00",{})
}


@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomTextField() {
    var value by remember { mutableStateOf("") }

    MobileBankingComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {

            TextField(
                value = value,
                onValueChange = { value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = Color.Yellow)
                    .clip(RoundedCornerShape(12.dp)),
                singleLine = true,
                isError = false,
                shape = TextFieldDefaults.filledShape
            )
        }
    }
}

