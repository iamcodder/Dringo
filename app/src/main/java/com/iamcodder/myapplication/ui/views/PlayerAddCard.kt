package com.iamcodder.myapplication.ui.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamcodder.myapplication.R
import com.iamcodder.myapplication.base.ui.BaseCard
import com.iamcodder.myapplication.data.local.enums.SaveEnums
import com.iamcodder.myapplication.data.local.model.PlayerCard
import com.iamcodder.myapplication.ui.theme.CustomColors
import com.iamcodder.myapplication.ui.theme.MyApplicationTheme

@Composable
@Preview
private fun deneme() {
    MyApplicationTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            PlayerAddCard()
        }
    }
}


@Composable()
fun PlayerAddCard(modifier: Modifier = Modifier) {
    val playerList = listOf<PlayerCard>(PlayerCard(0, ""))
    var players by remember { mutableStateOf(playerList) }
    BaseCard(cardTitle = "Oyuncu Ekle") {
        Column(
            modifier = modifier.background(Color.White)
        ) {
            PlayerRecycler(
                players = players,
                changeValue = { playerCard: PlayerCard, saveEnums: SaveEnums ->
                    val modelIndex = players.indexOf(playerCard)
                    if (modelIndex != -1) {
                        val tempList = players.toMutableList()
                        if (saveEnums == SaveEnums.SAVE) tempList[modelIndex] = playerCard
                        else if (saveEnums == SaveEnums.DELETE) tempList.removeAt(modelIndex)
                        players = tempList
                    }
                }
            )
            AddPlayer {
                val list = players.toMutableList()
                val lastItemId = if (list.isEmpty()) players.count() else players.last().id + 1
                val tempPlayerCard = PlayerCard(lastItemId, "")
                list.add(tempPlayerCard)
                players = list
            }

        }
    }
}

@Composable
private fun AddPlayer(modifier: Modifier = Modifier, addPlayer: () -> Unit) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Icon(
            painter =
            painterResource(id = R.drawable.ic_baseline_add_circle_24),
            contentDescription = "Add",
            tint = CustomColors.Orange500,
            modifier = modifier
                .size(24.dp)
                .clickable {
                    addPlayer()
                }
        )
    }
}

@Composable
private fun PlayerRecycler(
    modifier: Modifier = Modifier,
    players: List<PlayerCard>,
    changeValue: (PlayerCard, SaveEnums) -> Unit
) {
    LazyColumn(modifier = modifier.animateContentSize()) {
        items(players,
            key = {
                it.id
            }, itemContent = { item: PlayerCard ->
                PlayerController(player = item, changeValue = changeValue)
            })
    }
}

@Composable
private fun PlayerController(
    modifier: Modifier = Modifier,
    player: PlayerCard,
    changeValue: (PlayerCard, SaveEnums) -> Unit
) {

    var isEditing by remember { mutableStateOf(false) }
    val isEditingLogic = { bool: Boolean ->
        isEditing = bool
    }
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerName(
            isEditingLogic = isEditingLogic,
            player = player,
            changeValue = changeValue
        )
        PlayerIcon(
            player = player,
            changeValue = changeValue,
            isEditing = isEditing,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PlayerName(
    modifier: Modifier = Modifier,
    isEditingLogic: (Boolean) -> Unit,
    player: PlayerCard,
    changeValue: (PlayerCard, SaveEnums) -> Unit
) {
    var rememberedText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .onFocusEvent {
                when (it.name) {
                    FocusState.Active.name -> {
                        isEditingLogic(true)
                    }
                    FocusState.Inactive.name -> {
                        isEditingLogic(false)
                        changeValue(player, SaveEnums.SAVE)
                    }
                }
            },
        value = rememberedText,
        onValueChange = {
            isEditingLogic(true)
            rememberedText = it
        },
        placeholder = {
            Text(
                text = stringResource(R.string.input_name),
                style = MaterialTheme.typography.h6
            )
        },
        keyboardActions = KeyboardActions {
            keyboardController?.hideSoftwareKeyboard()
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 20,
        textStyle = MaterialTheme.typography.h6,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = CustomColors.Orange500,
            focusedLabelColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
private fun PlayerIcon(
    modifier: Modifier = Modifier,
    isEditing: Boolean,
    player: PlayerCard,
    changeValue: (PlayerCard, SaveEnums) -> Unit,
) {
    val iconId = R.drawable.ic_baseline_delete_24
    val iconColor = Color.Red
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!isEditing) {
            Icon(
                painter = painterResource(
                    id = iconId
                ),
                contentDescription = "Remove",
                modifier = modifier
                    .wrapContentSize()
                    .clickable {
                        changeValue(player, SaveEnums.DELETE)
                    },
                tint = iconColor
            )
        }
    }

}