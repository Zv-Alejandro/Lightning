package org.ies.wargame.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.ies.wargame.R
import org.ies.wargame.presentation.navigation.Screen
import org.ies.wargame.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 140.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.rsm),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(200.dp),
            )

            Text("Login", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = state.email,
                onValueChange = viewModel::setEmail,
                label = {
                    Text(
                        state.emailError.ifEmpty { "Email" },
                        color = if (state.emailError.isNotEmpty()) Red else Unspecified
                    )
                },
                leadingIcon = { Icon(Icons.Rounded.Email, "") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent
                )
            )

            TextField(
                value = state.password,
                onValueChange = viewModel::setPassword,
                label = {
                    Text(
                        state.passwordError.ifEmpty { "Password" },
                        color = if (state.passwordError.isNotEmpty()) Red else Unspecified
                    )
                },
                leadingIcon = { Icon(Icons.Rounded.Lock, "") },
                visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (state.passwordVisible)
                        painterResource(id = R.drawable.visibility_72dp)
                    else painterResource(id = R.drawable.visibility_off_72dp)

                    Icon(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier.clickable { viewModel.togglePasswordVisibility() }
                    )
                },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.login() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 90.dp)
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text("Not a student yet? ")
                Text(
                    "Enroll now!",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Register.route)
                    }
                )
            }
        }
    }
}

