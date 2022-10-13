package com.silverbullet.devsworld.presentation.login

import androidx.compose.foundation.layout.*
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
import com.silverbullet.devsworld.presentation.components.StandardTextField
import com.silverbullet.devsworld.presentation.ui.theme.PaddingMedium

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
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
                text = stringResource(id = R.string.login),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h1,
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = viewModel.usernameText.value,
                onValueChange = { username ->
                    viewModel.onEvent(LoginScreenEvent.UserNameFieldChanged(username))
                },
                hint = stringResource(id = R.string.login_hint),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                text = viewModel.passwordText.value,
                onValueChange = { password ->
                    viewModel.onEvent(LoginScreenEvent.PasswordFieldChanged(password))
                },
                hint = stringResource(id = R.string.password_hint),
                keyboardType = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.dont_have_account))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append(stringResource(id = R.string.sign_up))
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 50.dp)
        )
    }
}