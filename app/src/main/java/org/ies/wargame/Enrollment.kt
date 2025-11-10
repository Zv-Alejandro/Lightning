package org.ies.wargame

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Register() {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.rsm),
                    contentDescription = "logo de rsm",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(200.dp),
                )
            }
            Text("Register", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        emailError.ifEmpty { "Email" },
                        color = if (emailError.isNotEmpty()) Color.Red else Unspecified
                    )
                },
                leadingIcon = {
                    Icon(Icons.Rounded.Email, contentDescription = null)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(
                        nameError.ifEmpty { "Name" },
                        color = if (nameError.isNotEmpty()) Color.Red else Unspecified
                    )
                },
                leadingIcon = {
                    Icon(Icons.Rounded.AccountCircle, contentDescription = null)
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        passwordError.ifEmpty { "Password" },
                        color = if (passwordError.isNotEmpty()) Color.Red else Unspecified
                    )
                },
                leadingIcon = {
                    Icon(Icons.Rounded.Lock, contentDescription = null)
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        painterResource(id = R.drawable.visibility_72dp)
                    else painterResource(id = R.drawable.visibility_off_72dp)

                    Icon(
                        painter = image,
                        contentDescription = "View Password Status",
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Text("By creating an account, you agree to our ")
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                "Terms and Conditions",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    // Sent the user to the Terms and Conditions website or pop up window
                    // of this screen the Terms and Conditions
                }
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Button(
                onClick = {
                    emailError = if (email.isBlank()) "Email is required" else ""
                    nameError = if (name.isBlank()) "Name is required" else ""
                    passwordError = if (password.isBlank()) "Password is required" else ""
                    if (emailError.isEmpty() && nameError.isEmpty() && passwordError.isEmpty()) {
                        // Proceed to the payment screen, 'cause the user is enrolling to an academy or something like that.
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 90.dp)
            ) {
                Text(text = "Register")
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Already have an account?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    // Sent the user to the Login screen
                }
            )

        }
    }
}

@Preview
@Composable
fun PreviewRegister() {
    Register()
}

