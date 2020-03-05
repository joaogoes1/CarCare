package br.com.joaogoes.carcare.ui.mainactivity

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Expanded
import androidx.ui.layout.Spacing
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Surface
import br.com.joaogoes.carcare.data.model.RevisionItemModel

@Composable
fun kilometersScreen(revisionList: List<RevisionItemModel>) {
    Surface {
        Column(modifier = Expanded) {
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
