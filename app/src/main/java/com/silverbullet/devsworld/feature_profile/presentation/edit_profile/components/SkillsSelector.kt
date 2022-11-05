package com.silverbullet.devsworld.feature_profile.presentation.edit_profile.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.components.Chip
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium
import kotlin.random.Random

@Composable
fun SkillsSelector(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = R.string.select_top_3_skills),
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(PaddingMedium))
    FlowRow(
        modifier = modifier,
        mainAxisAlignment = MainAxisAlignment.Center,
        mainAxisSpacing = PaddingMedium,
        crossAxisSpacing = PaddingMedium
    ) {
        listOf(
            "Kotlin",
            "C++",
            "Python",
            "Assembly",
            "JavaScript",
            "Java",
            "Ruby",
            "R",
            "TypeScript",
            "Dart"
        ).forEach {
            Chip(
                text = it,
                selected = Random.nextBoolean()
            )
        }
    }
}