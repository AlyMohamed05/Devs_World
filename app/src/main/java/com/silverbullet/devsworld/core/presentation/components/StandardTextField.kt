package com.silverbullet.devsworld.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.silverbullet.devsworld.core.presentation.ui.theme.IconSizeMedium

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    hint: String = "",
    leadingIcon: ImageVector? = null,
    singleLine: Boolean = false,
    maxLength: Int = 40,
    maxLines: Int = 1,
    error: String? = null,
    showPassword: Boolean = false,
    setShowPasswordCallback: (Boolean) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit = {}
) {

    val isPasswordToggleDisplayed by remember {
        mutableStateOf(keyboardType == KeyboardType.Password)
    }

    Column(
        modifier = modifier
    ) {
        TextField(
            value = text,
            textStyle = textStyle,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            placeholder = {
                Text(text = hint, style = MaterialTheme.typography.body1)
            },
            isError = error != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = if (!showPassword && isPasswordToggleDisplayed)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            leadingIcon = if (leadingIcon != null) {
                @Composable {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(IconSizeMedium)
                    )
                }
            } else
                null,
            trailingIcon =
            if (isPasswordToggleDisplayed) {
                @Composable {
                    IconButton(onClick = { setShowPasswordCallback(!showPassword) }) {
                        Icon(
                            imageVector = if (showPassword)
                                Icons.Filled.VisibilityOff
                            else
                                Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                }
            } else null,
            singleLine = singleLine,
            maxLines = maxLines,
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    testTag = "standard_text_field"
                }
        )
        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error
            )
        }
    }
}