package br.com.joaogoes.ui.home.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
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
        (view as ViewGroup).setContent {
            CustomDialogScreen()
        }
        return view
    }

    @Composable
    fun CustomDialogScreen() {
        val itemState: MutableState<TextFieldValue> = state { TextFieldValue() }
        val currentKilometerState: MutableState<TextFieldValue> = state { TextFieldValue() }
        val nextRevisionState: MutableState<TextFieldValue> = state { TextFieldValue() }

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
        Box(
            modifier = Modifier.drawBorder(
                size = 0.2.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                value = state.value,
                textStyle = TextStyle(
                    fontSize = 14.sp
                ),
                onValueChange = { state.value = it },
                keyboardType = keyboardType
            )
        }
    }
}
