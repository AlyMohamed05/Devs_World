package com.silverbullet.devsworld.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silverbullet.devsworld.presentation.ui.theme.HintGray
import com.silverbullet.devsworld.presentation.ui.theme.PaddingSmall

@Composable
fun RowScope.BottomNavItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    enabled: Boolean = true,
    selected: Boolean = false,
    alertCount: Int? = null,
    onClick: () -> Unit,
    selectedColor: Color = MaterialTheme.colors.primary,
    unSelectedColor: Color = HintGray
) {
    alertCount?.let {
        require(alertCount >= 0)
    }
    BottomNavigationItem(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        selectedContentColor = selectedColor,
        unselectedContentColor = unSelectedColor,
        icon = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(PaddingSmall)
                    .drawBehind {
                        if (selected) {
                            drawLine(
                                color = selectedColor,
                                start = Offset(
                                    size.width / 2f - 15.dp.toPx(),
                                    size.height
                                ),
                                end = Offset(
                                    size.width / 2f + 15.dp.toPx(),
                                    size.height
                                ),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                    }
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.align(Alignment.Center)
                )
                if (alertCount != null) {
                    val alertText = if (alertCount > 99) "99+" else alertCount.toString()
                    Text(
                        text = alertText,
                        color = MaterialTheme.colors.onPrimary,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(10.dp)
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primary)
                    )
                }
            }
        }
    )
}