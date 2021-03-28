package com.iamcodder.myapplication.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.iamcodder.myapplication.ui.theme.CustomColors

@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    cardTitle: String,
    cardContent: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(0.8f)
            .padding(16.dp)
            .clip(RoundedCornerShape(percent = 10))
            .border(1.dp, CustomColors.Grey700, RoundedCornerShape(percent = 10))
            .shadow(6.dp),
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(CustomColors.Grey800),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cardTitle,
                    color = CustomColors.Green500,
                    style = MaterialTheme.typography.h5
                )
            }
            cardContent()
        }
    }
}
