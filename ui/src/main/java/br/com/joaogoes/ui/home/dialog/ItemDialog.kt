package br.com.joaogoes.ui.home.dialog

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.ui.R

@Composable
internal fun ItemDialog(
    context: Context,
    action: (RevisionItemModel) -> Unit,
    dismiss: () -> Unit
) {
    val itemState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    val currentKilometerState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    val nextRevisionState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
    val alertState = mutableStateOf(false)

    Dialog(onDismissRequest = { dismiss() }) {
        Surface(
            border = BorderStroke(0.2.dp, Color.Black)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                TitleText(context.getString(R.string.item_dialog_title))
                ItemText(context.getString(R.string.item_dialog_item_name))
                ItemTextField(itemState)
                ItemText(context.getString(R.string.item_dialog_current_kilometer))
                ItemTextField(currentKilometerState, KeyboardType.Number)
                ItemText(context.getString(R.string.item_dialog_target_kilometer))
                ItemTextField(nextRevisionState, KeyboardType.Number)
                if (alertState.value) AlertText(context.getString(R.string.item_dialog_alert))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp, 0.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    button(context.getString(R.string.cancel), onClick = { dismiss() })
                    button(context.getString(R.string.confirm)) {
                        alertState.value = false
                        val current = currentKilometerState.value.text.toLongOrNull()
                        val target = nextRevisionState.value.text.toLongOrNull()
                        if (current != null && target != null) {
                            val newItem = RevisionItemModel(
                                itemName = itemState.value.text,
                                currentRevisionKilometer = current,
                                nextRevisionKilometer = target
                            )
                            dismiss()
                            action(newItem)
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
        content = {
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
private fun TitleText(title: String) {
    Text(
        text = title,
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
private fun AlertText(text: String) {
    Text(
        text = text,
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
            .border(
                width = 0.2.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(8.dp)
            ),
        value = state.value,
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
        onValueChange = { state.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}
