package com.example.loginjetpack.ui.theme.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginjetpack.R
import com.example.loginjetpack.ui.theme.PinkLogin

@Composable
fun LoginText() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 120.dp)
    ) {
        Text(
            text = "SIGN IN",
            color = PinkLogin,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InputField(labelText: String, @DrawableRes leadingIcon: Int) {
    val email = remember {
        mutableStateOf(TextFieldValue())
    }
    val isError = remember {
        mutableStateOf(false)
    }

    val clicked = remember {
        mutableStateOf(false)
    }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    val passwordVisualTransformation =
        if (passwordVisibility.value) VisualTransformation.None
        else PasswordVisualTransformation()

    fun validate(text: String) {
        println(text)
        isError.value = text.isEmpty()
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 30.dp)
    ) {
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                validate(email.value.text)
                clicked.value = true
            },
            label = {
                Text(
                    text = labelText,
                    color = Color.White
                )
            },
            placeholder = {
                Text(
                    text = "Enter your ${labelText.lowercase()}"
                )
            },
            textStyle = TextStyle(
                color = Color.White
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = labelText.lowercase(),
                    tint = Color.White
                )

            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor =
                if (isError.value) Color.Red
                else PinkLogin,
                unfocusedBorderColor =
                if (email.value.text.isEmpty() && clicked.value) Color.Red
                else PinkLogin,

                ),
            singleLine = true,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            trailingIcon = {
                if (isError.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error),
                        contentDescription = labelText.lowercase(),
                        tint = Color.Red
                    )
                }

                IconButton(
                    onClick = { passwordVisibility.value = !passwordVisibility.value },
                    modifier = Modifier
                        .padding(start = 4.dp),
                ) {
                    if (labelText == "Password" && !isError.value) {
                        val toggleIcon: Int = if (passwordVisibility.value) {
                            R.drawable.ic_visibility
                        } else {
                            R.drawable.ic_visibility_off
                        }
                        Icon(
                            painter = painterResource(id = toggleIcon),
                            contentDescription = ""
                        )
                    }
                }

            },
            supportingText = {
                if (isError.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "The filed can not be empty",
                        color = Color.Red
                    )
                }
            },
            visualTransformation = passwordVisualTransformation
        )
    }
}

@Composable
fun LoginButton() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 45.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, Color.White)
        ) {
            Text(
                text = "SIGN IN",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun HaveAccount(regiser: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    ) {
        Text(
            text = "Do you have an account?",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "SIGN UP",
            color = Color.Red,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable {
                    regiser()
                }
        )
    }
}