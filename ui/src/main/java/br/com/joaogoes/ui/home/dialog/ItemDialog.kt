package br.com.joaogoes.ui.home.dialog

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextAlign
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import br.com.joaogoes.model.RevisionItemModel

@Composable
fun ItemDialog(
    saveRevisionItem: (RevisionItemModel) -> Unit,
    dismiss: () -> Unit
) {
    val itemState: MutableState<TextFieldValue> = state { TextFieldValue() }
    val currentKilometerState: MutableState<TextFieldValue> = state { TextFieldValue() }
    val nextRevisionState: MutableState<TextFieldValue> = state { TextFieldValue() }
    val alertState = state { false }

    Dialog(onCloseRequest = { dismiss() }) {
        Surface(
            border = Border(0.2.dp, Color.Black)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                TitleText()
                ItemText("Item:")
                ItemTextField(itemState)
                ItemText("Current Kilometer:")
                ItemTextField(currentKilometerState, KeyboardType.Number)
                ItemText("Next revision:")
                ItemTextField(nextRevisionState, KeyboardType.Number)
                if (alertState.value) AlertText()
                Row(
                    modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp, 0.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    button("Cancel", onClick = { dismiss() })
                    button("Confirmar") {
                        alertState.value = false
                        val current = currentKilometerState.value.text.toLongOrNull()
                        val target = nextRevisionState.value.text.toLongOrNull()
                        if (current != null && target != null) {
                            val newItem = RevisionItemModel(
                                uid = -1,
                                itemName = itemState.value.text,
                                currentRevisionKilometer = current,
                                nextRevisionKilometer = target
                            )
                            saveRevisionItem(newItem)
                            dismiss()
                        } else {
                            alertState.value = true
                        }

                    }
                }
            }
        }
    }
}

@Composable
private fun button(text: String, onClick: () -> Unit) {
    Button(
        text = {
            Text(
                text = text,
                modifier = Modifier,
                style = TextStyle(
                    Color.White
                )
            )
        },
        onClick = onClick
    )
}

@Composable
private fun TitleText() {
    Text(
        text = "Adicionar nova revisão",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
private fun AlertText() {
    Text(
        text = "Por favor, digite apenas números.",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            color = Color.Red,
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
private fun ItemText(text: String) {
    Text(
        text = text,
        modifier = Modifier,
        style = TextStyle(
            Color.DarkGray,
            fontSize = 14.sp
        )
    )
}

@Composable
private fun ItemTextField(
    state: MutableState<TextFieldValue>,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .drawBorder(
                size = 0.2.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(8.dp)
            ),
        value = state.value,
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
        onValueChange = { state.value = it },
        keyboardType = keyboardType
    )
}
