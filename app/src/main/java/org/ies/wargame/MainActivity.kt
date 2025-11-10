package org.ies.wargame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ies.wargame.ui.theme.LightningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LightningTheme {
                Login()
            }
        }
    }
}

@Composable
fun Login() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 140.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Image(
                    painter = painterResource(
                        id = R.drawable.rsm
                    ),
                    contentDescription = "logo de rsm",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(200.dp),
                )
            }
            Text("Login", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        emailError.ifEmpty { "Email" },
                        color = if (emailError.isNotEmpty()) Red else Unspecified
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Email,
                        contentDescription = ""
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent
                )
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        passwordError.ifEmpty { "Password" },
                        color = if (passwordError.isNotEmpty()) Red else Unspecified
                    )
                },
                leadingIcon = {
                    Icon(Icons.Rounded.Lock, "")
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
//                    It doesn't work properly because i am using a drawable
//                    TODO ask the teacher how add dependencies without breaking the project
//                    IconToggleButton(
//                        checked = passwordVisible,
//                        onCheckedChange = { passwordVisible = it }
//                    ) {
//                        Icon(
//                            imageVector = if (passwordVisible) R.drawable.visibility_off_24dp else R.drawable.visibility_24dp,
//                            contentDescription = "Icon of visibility of the password",
//                        )
//                    }
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
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    emailError = if (email.isBlank()) "Email is required" else ""
                    password = if (password.isBlank()) "Password is required" else ""
                    if (emailError.isEmpty() && passwordError.isEmpty()) {
                        //sent the user to the next screen of the app
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 90.dp)
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Did you forgot the password?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {//sent the user to the forgotten password screen}
                }
            )
            Spacer(modifier = Modifier.height(25.dp))

            Row {
                Text("Not a studient yet? ")

                Text(
                    "Enroll now!",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { //Sent the user to the enrollment screen}))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin(){
    Login()
}