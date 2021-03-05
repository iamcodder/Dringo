package com.iamcodder.myapplication.ui.views

import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamcodder.myapplication.R
import com.iamcodder.myapplication.base.ui.BaseCard
import com.iamcodder.myapplication.data.local.enum.LanguageEnums
import com.iamcodder.myapplication.ui.theme.Grey600

@Preview
@Composable
fun LanguageCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BaseCard(cardTitle = stringResource(id = R.string.select_language)) {
            LanguageSelectCard {
                Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
private fun LanguageSelectCard(
    modifier: Modifier = Modifier,
    onClick: (LanguageEnums) -> Unit
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LineLanguages(
            imageRes = R.drawable.turkey,
            contentDescription = stringResource(R.string.turkish),
            languages = LanguageEnums.TURKISH,
            onClick = onClick
        )
        LineLanguages(
            imageRes = R.drawable.germany,
            contentDescription = stringResource(R.string.german),
            languages = LanguageEnums.GERMAN,
            onClick = onClick
        )
        LineLanguages(
            imageRes = R.drawable.united_kingdom,
            contentDescription = stringResource(R.string.english),
            languages = LanguageEnums.ENGLISH,
            onClick = onClick
        )
    }
}

@Composable
private fun LineLanguages(
    modifier: Modifier = Modifier,
    imageRes: Int,
    contentDescription: String,
    languages: LanguageEnums,
    onClick: (LanguageEnums) -> Unit
) {
    val initBrush = Brush.horizontalGradient(listOf(Color.White, Color.White))
    var brush by remember { mutableStateOf(initBrush) }
    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(vertical = 8.dp)
            .background(brush = brush)
            .pointerInteropFilter {
                val colorList = when (languages) {
                    LanguageEnums.TURKISH -> listOf(Color.Red, Color.White)
                    LanguageEnums.GERMAN -> listOf(Color.Black, Color.Red, Color.Yellow)
                    LanguageEnums.ENGLISH -> listOf(Color.Red, Color.White, Color.Blue)
                }
                brush = when (it.action) {
                    MotionEvent.ACTION_UP -> Brush.horizontalGradient(
                        listOf(
                            Color.White,
                            Color.White
                        )
                    )
                    MotionEvent.ACTION_DOWN -> Brush.horizontalGradient(colorList)
                    else -> Brush.horizontalGradient(listOf(Color.White, Color.White))
                }
                onClick(languages)
                true
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = modifier.weight(1f), contentAlignment = Alignment.Center) {
            CircleImage(imageRes = imageRes, contentDescription = contentDescription)
        }
        Box(modifier = modifier.weight(1f)) {
            Text(text = contentDescription, style = MaterialTheme.typography.subtitle2)
        }
    }
}

@Composable
fun CircleImage(modifier: Modifier = Modifier, imageRes: Int, contentDescription: String) {
    Image(
        modifier = modifier
            .size(50.dp)
            .clip(CircleShape)
            .border(1.dp, Grey600, CircleShape),
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription,
        contentScale = ContentScale.None
    )

}