package com.iamcodder.myapplication.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamcodder.myapplication.R
import com.iamcodder.myapplication.ui.theme.Green500
import com.iamcodder.myapplication.ui.theme.Grey600
import com.iamcodder.myapplication.ui.theme.Grey700
import com.iamcodder.myapplication.ui.theme.Grey800

@Preview
@Composable
fun LanguageSelectCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.wrapContentSize()
    ) {
        LineLanguages(
            imageRes = R.drawable.united_kingdom,
            contentDescription = stringResource(R.string.english)
        )
        LineLanguages(
            imageRes = R.drawable.turkey,
            contentDescription = stringResource(R.string.turkish)
        )
        LineLanguages(
            imageRes = R.drawable.germany,
            contentDescription = stringResource(R.string.german)
        )
    }
}

@Preview
@Composable
fun LanguageCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(300.dp)
            .height(400.dp)
            .clip(RoundedCornerShape(percent = 10))
            .border(1.dp, Grey700, RoundedCornerShape(percent = 10))
            .shadow(6.dp)
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Grey800),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.select_language),
                    color = Green500,
                    style = MaterialTheme.typography.h4
                )
            }
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LineLanguages(
                    imageRes = R.drawable.turkey,
                    contentDescription = stringResource(R.string.turkish)
                )
                LineLanguages(
                    imageRes = R.drawable.germany,
                    contentDescription = stringResource(R.string.german)
                )
                LineLanguages(
                    imageRes = R.drawable.united_kingdom,
                    contentDescription = stringResource(R.string.english)
                )
            }
        }

    }
}

@Composable
fun LineLanguages(modifier: Modifier = Modifier, imageRes: Int, contentDescription: String) {
    Row(
        modifier = modifier
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = modifier.weight(1f), contentAlignment = Alignment.Center) {
            CircleImage(imageRes = imageRes, contentDescription = contentDescription)
        }
        Box(modifier = modifier.weight(1f)) {
            Text(text = contentDescription, style = MaterialTheme.typography.h5)
        }
    }
}

@Composable
fun CircleImage(modifier: Modifier = Modifier, imageRes: Int, contentDescription: String) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription,
        contentScale = ContentScale.None,
        modifier = modifier
            .width(60.dp)
            .height(60.dp)
            .clip(CircleShape)
            .border(4.dp, Grey600, CircleShape),
    )

}