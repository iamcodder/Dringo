package com.iamcodder.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.iamcodder.myapplication.ui.theme.CustomColors

@Composable
fun SplashScreen() {
    Background()
}

@Preview
@Composable
fun Background(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = CustomColors.PurpleBackground),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.dringo_logo),
            "dringoLogo",
            modifier = modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
    }

}