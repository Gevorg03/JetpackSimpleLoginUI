package com.example.loginjetpack

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.loginjetpack.ui.theme.LoginJetpackTheme
import com.example.loginjetpack.ui.theme.screens.HaveAccount
import com.example.loginjetpack.ui.theme.screens.InputField
import com.example.loginjetpack.ui.theme.screens.LoginButton
import com.example.loginjetpack.ui.theme.screens.LoginText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginJetpackTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    Column {
                        LoginText()
                        InputField("Email", R.drawable.ic_email)
                        InputField("Password", R.drawable.ic_password)
                        LoginButton()
                        HaveAccount { register(this@MainActivity) }
                    }
                }
            }
        }
    }
}

fun register(context: Context) {
    Toast.makeText(context, "Register", Toast.LENGTH_SHORT).show()
}