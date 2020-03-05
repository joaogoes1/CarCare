package br.com.joaogoes.carcare.ui.mainactivity

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.WithDensity
import androidx.ui.core.dp
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import br.com.joaogoes.carcare.R
import br.com.joaogoes.carcare.data.model.RevisionItemModel

@Composable
fun revisionListItem(revisionItem: RevisionItemModel) {
    Surface(
        modifier = Spacing(
            top = 4.dp,
            bottom = 4.dp
        ),
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        border = Border(Color.Gray, 1.dp)
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
                modifier = ExpandedWidth,
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
    val vector = +vectorResource(id)
    WithDensity {
        Container(
            width = vector.defaultWidth.toDp(),
            height = vector.defaultHeight.toDp()
        ) {
            DrawVector(vectorImage = vector, tintColor = tint)
        }
    }
}
