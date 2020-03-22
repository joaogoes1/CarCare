package br.com.joaogoes.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.fragment.app.DialogFragment
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.core.setContent
import androidx.ui.foundation.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.Surface
import androidx.ui.text.TextFieldValue
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import br.com.joaogoes.model.RevisionItemModel

class CustomDialog : DialogFragment() {

    companion object {
        fun newInstance(item: RevisionItemModel): CustomDialog {
            val dialog = CustomDialog()
            val args = Bundle()
            args.putString("name", item.itemName)
            args.putLong("currentKilometer", item.currentRevisionKilometer)
            args.putSerializable("currentCalendar", item.currentRevisionDate)
            args.putLong("currentKilometer", item.nextRevisionKilometer)
            args.putSerializable("currentCalendar", item.nextRevisionDate)
            dialog.arguments = args
            return dialog
        }

        fun newInstance() = CustomDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.setContent {
            screen()
        }
        return container
    }

    @Composable
    fun screen() {
        val itemState: MutableState<TextFieldValue> = state { TextFieldValue() }
        val currentKilometerState: MutableState<TextFieldValue> = state { TextFieldValue() }
        val nextRevisionState: MutableState<TextFieldValue> = state { TextFieldValue() }

        Surface(
            LayoutAlign.BottomCenter,
            shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp),
            border = Border(0.2.dp, Color.Black)
        ) {
            Column(
                modifier = LayoutPadding(8.dp)
            ) {
                TitleText()
                ItemText("Item:")
                ItemTextField(itemState)
                ItemText("Current Kilometer:")
                ItemTextField(currentKilometerState)
                ItemText("Next revision:")
                ItemTextField(nextRevisionState)
                Container(
                    modifier = LayoutPadding(0.dp, 16.dp, 0.dp, 0.dp)
                ) {
                    Row(
                        modifier = LayoutWidth.Fill,
                        arrangement = Arrangement.SpaceAround
                    ) {
                        button("Cancel") {}
                        button("Confirmar") {
                            val newItem = RevisionItemModel(
                                uid = 1,
                                itemName = itemState.value.text,
                                currentRevisionKilometer = currentKilometerState.value.text.toLong(),
                                nextRevisionKilometer = nextRevisionState.value.text.toLong()
                            )
                            // action(newItem)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun button(text: String, onClick: () -> Unit) {
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
    fun TitleText() {
        Container(LayoutWidth.Fill) {
            Text(
                "Adicionar nova revisão",
                LayoutAlign.Center,
                TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )
            )
        }
    }

    @Composable
    fun ItemText(text: String) {
        Text(
            text,
            Modifier.None,
            TextStyle(
                Color.DarkGray,
                fontSize = 12.sp
            )
        )
    }

    @Composable
    fun ItemTextField(state: MutableState<TextFieldValue>) {
        Surface(
            LayoutAlign.BottomCenter,
            shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp),
            border = Border(0.2.dp, Color.Black)
        ) {
            TextField(
                value = state.value,
                onValueChange = { state.value = it }
            )
        }
    }

    @Preview
    @Composable
    fun preview() {
        screen()
    }
}
