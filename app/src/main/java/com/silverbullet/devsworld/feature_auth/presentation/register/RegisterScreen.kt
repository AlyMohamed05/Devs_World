package com.silverbullet.devsworld.feature_auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.components.StandardTextField
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.feature_auth.presentation.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is AuthViewModel.UiEvent.ShowToast -> {
                    Toast.makeText(
                        context,
                        event.message.asString(context),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AuthViewModel.UiEvent.Navigate -> {
                    navController.navigate(event.route) {
                        popUpTo(event.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = PaddingMedium)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.register),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h1,
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = viewModel.emailInput.value.text,
                onValueChange = { email ->
                    viewModel.onEvent(
                        RegisterScreenEvent.EmailFieldChanged(email)
                    )
                },
                error = viewModel.emailInput.value.error?.asString(),
                hint = stringResource(id = R.string.email_hint),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = viewModel.userInput.value.text,
                onValueChange = { username ->
                    viewModel.onEvent(
                        RegisterScreenEvent.UserNameFieldChanged(username)
                    )
                },
                error = viewModel.userInput.value.error?.asString(),
                hint = stringResource(id = R.string.username_hint),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = viewModel.passwordInput.value.text,
                onValueChange = { password ->
                    viewModel.onEvent(
                        RegisterScreenEvent.PasswordFieldChanged(password)
                    )
                },
                error = viewModel.passwordInput.value.error?.asString(),
                showPassword = viewModel.showPassword.value,
                setShowPasswordCallback = {
                    viewModel.onEvent(
                        RegisterScreenEvent.SetPasswordVisibility(it)
                    )
                },
                hint = stringResource(id = R.string.password_hint),
                keyboardType = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { viewModel.onEvent(RegisterScreenEvent.Register) },
                    enabled = !viewModel.isLoading.value,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = stringResource(id = R.string.register),
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                if (viewModel.isLoading.value) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.already_have_account))
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        append(stringResource(id = R.string.sign_in))
                    }
                },
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }
    }
}