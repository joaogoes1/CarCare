package br.com.joaogoes.carcare.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import br.com.joaogoes.carcare.data.model.RevisionItemModel

val listaTeste = listOf(
    RevisionItemModel(
        itemName = "Oléo do Motor",
        currentRevisionKilometer = 93300,
        nextRevisionKilometer = 103300
    ),
    RevisionItemModel(
        itemName = "Oléo do Motor",
        currentRevisionKilometer = 93300,
        nextRevisionKilometer = 103300
    ),
    RevisionItemModel(
        itemName = "Oléo do Motor",
        currentRevisionKilometer = 93300,
        nextRevisionKilometer = 103300
    )
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                kilometersScreen(listaTeste)
            }
        }
    }

    @Preview
    @Composable
    fun preview() {
        kilometersScreen(revisionList = listaTeste)
    }
}
