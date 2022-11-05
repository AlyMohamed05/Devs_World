package com.silverbullet.devsworld.feature_auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
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
                text = viewModel.emailText.value,
                onValueChange = { email ->
                    viewModel.onEvent(
                        RegisterScreenEvent.EmailFieldChanged(email)
                    )
                },
                error = viewModel.usernameError.value,
                hint = stringResource(id = R.string.email_hint),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = viewModel.usernameText.value,
                onValueChange = { username ->
                    viewModel.onEvent(
                        RegisterScreenEvent.UserNameFieldChanged(username)
                    )
                },
                error = viewModel.usernameError.value,
                hint = stringResource(id = R.string.username_hint),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = viewModel.passwordText.value,
                onValueChange = { password ->
                    viewModel.onEvent(
                        RegisterScreenEvent.PasswordFieldChanged(password)
                    )
                },
                error = viewModel.passwordError.value,
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
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    color = MaterialTheme.colors.onPrimary
                )
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