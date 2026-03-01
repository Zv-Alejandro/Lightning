package org.ies.wargame.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import org.ies.wargame.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = koinViewModel()) {
    val email by viewModel.email.collectAsState()
    val name by viewModel.name.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordVisible by viewModel.passwordVisible.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val nameError by viewModel.nameError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    Scaffold(
        modifier =
            Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.rsm),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(200.dp),
            )
            Text("Register", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = email,
                onValueChange = viewModel::setEmail,
                isError = emailError != null,
                label = {
                    Text(
                        emailError ?: "Email"
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Email,
                        null
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
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value =
                    name,
                onValueChange = viewModel::setName,
                isError = nameError != null,
                label = {
                    Text(
                        nameError ?: "Nombre"
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.AccountCircle,
                        null
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
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                value =
                    password,
                onValueChange = viewModel::setPassword,
                isError = passwordError != null,
                label = {
                    Text(
                        passwordError ?: "Contraseña"
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Lock,
                        null
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (passwordVisible) painterResource(id = R.drawable.visibility_72dp) else painterResource(
                            id = R.drawable.visibility_off_72dp
                        )
                    Icon(
                        painter =
                            image,
                        contentDescription = "",
                        modifier = Modifier.clickable { viewModel.togglePasswordVisibility() })
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
                onClick = {
                    viewModel.register {
                        navController.navigate(
                            Screen.Login.route
                        ) { popUpTo(Screen.Register.route) { inclusive = true } }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text("Register")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text =
                    "Already have an account?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate(
                        Screen.Login.route
                    )
                })
        }
    }
}