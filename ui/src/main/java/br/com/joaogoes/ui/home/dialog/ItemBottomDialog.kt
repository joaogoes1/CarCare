package br.com.joaogoes.ui.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
<<<<<<< HEAD
=======
import androidx.compose.ui.platform.setContent
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.ui.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/*
    Use this dialog when find a solution for state being re-created always user digits something.
 */
class ItemBottomDialog(
    val saveRevisionItem: (RevisionItemModel) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.empty_layout, container, false)
//        (view as ViewGroup).setContent {
//            CustomDialogScreen()
//        }
        return view
    }

    @Composable
    fun CustomDialogScreen() {
        val itemState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
        val currentKilometerState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())
        val nextRevisionState: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue())

        Surface(
            border = BorderStroke(0.2.dp, Color.Black)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                TitleText()
                ItemText("Item:")
                ItemTextField(itemState)
                ItemText("Current Kilometer:")
                ItemTextField(currentKilometerState, KeyboardOptions(keyboardType = KeyboardType.Number))
                ItemText("Next revision:")
                ItemTextField(nextRevisionState, KeyboardOptions(keyboardType = KeyboardType.Number))
                Box(
                    modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        button("Cancel", onClick = { dismiss() })
                        button("Confirmar") {
                            val newItem = RevisionItemModel(
                                itemName = itemState.value.text,
                                currentRevisionKilometer = currentKilometerState.value.text.toLong(),
                                nextRevisionKilometer = nextRevisionState.value.text.toLong()
                            )
                            saveRevisionItem(newItem)
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun button(text: String, onClick: () -> Unit) {
        TextButton(
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
        keyboardType: KeyboardOptions = KeyboardOptions.Default
    ) {
        Box(
            modifier = Modifier.border(
                width = 0.2.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            TextField(
                value = state.value,
                onValueChange = { state.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                textStyle = TextStyle(
                    fontSize = 14.sp
                ),
                keyboardOptions = keyboardType
            )
        }
    }
}
