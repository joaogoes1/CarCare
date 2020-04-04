package br.com.joaogoes.ui.home.dialog

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Border
import androidx.ui.foundation.DrawBorder
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Surface
import androidx.ui.text.TextFieldValue
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.ui.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomDialog(
    val saveRevisionItem: (RevisionItemModel) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.empty_layout, container, false)
        (view as ViewGroup).setContent {
            CustomDialogMethod()
        }
        return view
    }

    @Composable
    fun CustomDialogMethod() {
        val itemState: MutableState<TextFieldValue> = state { TextFieldValue() }
        val currentKilometerState: MutableState<TextFieldValue> = state { TextFieldValue() }
        val nextRevisionState: MutableState<TextFieldValue> = state { TextFieldValue() }

        Surface(
            border = Border(0.2.dp, Color.Black)
        ) {
            Column(
                modifier = LayoutPadding(8.dp)
            ) {
                TitleText()
                ItemText("Item:")
                ItemTextField(itemState)
                ItemText("Current Kilometer:")
                ItemTextField(currentKilometerState, KeyboardType.Number)
                ItemText("Next revision:")
                ItemTextField(nextRevisionState, KeyboardType.Number)
                Container(
                    modifier = LayoutPadding(0.dp, 16.dp, 0.dp, 0.dp)
                ) {
                    Row(
                        modifier = LayoutWidth.Fill,
                        arrangement = Arrangement.SpaceAround
                    ) {
                        button("Cancel", onClick = { dismiss() })
                        button("Confirmar") {
                            val newItem = RevisionItemModel(
                                uid = -1,
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
            children = {
                Text(
                    text,
                    Modifier.None,
                    TextStyle(
                        Color.White
                    )
                )
            },
            onClick = onClick
        )
    }

    @Composable
    private fun TitleText() {
        Container(LayoutWidth.Fill) {
            Text(
                "Adicionar nova revisão",
                LayoutAlign.TopCenter,
                TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
            )
        }
    }


    @Composable
    private fun ItemText(text: String) {
        Text(
            text,
            Modifier.None,
            TextStyle(
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
        Container(
            modifier = DrawBorder(
                size = 0.2.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            TextField(
                modifier = LayoutPadding(4.dp),
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
