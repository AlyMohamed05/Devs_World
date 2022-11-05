package com.silverbullet.devsworld.core.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingSmall

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean = false,
    selectedColor: Color = MaterialTheme.colors.primary,
    unSelectedColor: Color = MaterialTheme.colors.onSurface,
    onChipClick: () -> Unit = {}
) {
    val dominantColor = if (selected)
        selectedColor
    else
        unSelectedColor
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = dominantColor,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable { onChipClick() }
            .padding(PaddingSmall),
        text = text,
        textAlign = TextAlign.Center,
        color = dominantColor
    )
}