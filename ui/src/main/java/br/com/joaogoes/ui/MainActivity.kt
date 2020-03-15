package br.com.joaogoes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.WithDensity
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import br.com.joaogoes.model.RevisionItemModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val revisionItemList = viewModel.revisionItems()

        setContent {
            MaterialTheme {
                kilometersScreen(revisionItemList ?: emptyList())
            }
        }
    }

    @Composable
    fun kilometersScreen(revisionList: List<RevisionItemModel>) {
        Surface {
            Column {
                TopAppBar(
                    title = {
                        Text("CarCare")
                    }
                )
                VerticalScroller(
                    modifier = Spacing(32.dp)
                ) {
                    Column(
                        arrangement = Arrangement.Center
                    ) {
                        Text("Manutenção:")
                        revisionList.map {
                            revisionListItem(it)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun revisionListItem(revisionItem: RevisionItemModel) {
        Surface(
            modifier = Spacing(
                top = 4.dp,
                bottom = 4.dp
            ),
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            borderWidth = 1.dp,
            borderBrush = SolidColor(Color.Gray)
        ) {
            Surface(
                modifier = Spacing(
                    left = 8.dp,
                    top = 8.dp,
                    right = 8.dp,
                    bottom = 8.dp
                ),
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            ) {
                Row(
                    arrangement = Arrangement.SpaceBetween
                ) {
                    with(revisionItem) {
                        Text("$itemName ")
                        Text("$currentRevisionKilometer km")
                        VectorImage(R.drawable.ic_arrow_right, Color.Black)
                        Text("$nextRevisionKilometer km")
                    }
                }
            }
        }
    }

    @Composable
    fun VectorImage(@DrawableRes id: Int, tint: Color = Color.Transparent) {
        val vector = vectorResource(id)
        WithDensity {
            Container(
                width = vector.defaultWidth,
                height = vector.defaultHeight
            ) {
                DrawVector(vectorImage = vector, tintColor = tint)
            }
        }
    }

}