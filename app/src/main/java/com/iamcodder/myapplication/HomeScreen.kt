package com.iamcodder.myapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun HomeScreen() {
    HomeBackground()
}

@Composable
fun HomeBackground(modifier: Modifier = Modifier) {
    val isOkeyButtonClick = remember { mutableStateOf(false) }
    Image(
        painter = painterResource(id = R.drawable.dringo_background),
        stringResource(id = R.string.home_background_image),
        modifier = modifier.fillMaxSize(),
        alpha = if (!isOkeyButtonClick.value) 0.5f else 1.0f,
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (!isOkeyButtonClick.value) {
            Policy(onclick = {
                isOkeyButtonClick.value = true
            })
        }
    }
}

@Composable
fun Policy(modifier: Modifier = Modifier, onclick: () -> Unit) {
    Card(
        modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20)
    ) {
        Column(
            modifier = modifier
                .wrapContentHeight(Alignment.Bottom)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.danger),
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.policy),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(16.dp))
            OutlinedButton(
                onClick = onclick,
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(
                    text = stringResource(id = R.string.okey),
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(horizontal = 20.dp),
                    color = Color.Black
                )
            }
        }
    }
}

