package br.com.joaogoes.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.Border
import androidx.ui.foundation.Image
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.material.*
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.ui.GenericErrorScreen
import br.com.joaogoes.ui.LoadingScreen
import br.com.joaogoes.ui.R
import br.com.joaogoes.ui.home.dialog.CustomDialog
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by inject()
    private var state: HomeViewState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            icon = imageResource(R.drawable.ic_plus),
                            onClick = {
                                val saveItemDialog = CustomDialog { item ->
                                    viewModel.saveRevision(item)
                                }
                                saveItemDialog.show(supportFragmentManager, "HomeActivity")
                            }
                        )
                    },
                    topAppBar = {
                        TopAppBar(
                            title = {
                                Text("CarCare")
                            }
                        )
                    },
                    bodyContent = {
                        state = observeState<HomeViewState>(viewModel.viewState)
                        buildLayout(viewState = state)
                    }
                )
            }
        }
    }

    @Composable
    private fun <T> observeState(data: LiveData<T>): T? {
        val result = state { data.value }
        val observer = remember { Observer<T> { result.value = it } }

        onCommit(data) {
            data.observeForever(observer)
            onDispose { data.removeObserver(observer) }
        }
        return result.value
    }

    @Composable
    private fun buildLayout(viewState: HomeViewState?) =
        when (viewState) {
            is HomeViewState.Loading -> LoadingScreen()
            is HomeViewState.Success -> KilometersScreen(viewState.revisionItems)
            else -> GenericErrorScreen()
        }

    @Composable
    fun KilometersScreen(revisionList: List<RevisionItemModel>) =
        Column {
            VerticalScroller(
                modifier = LayoutPadding(32.dp)
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

    @Composable
    fun revisionListItem(revisionItem: RevisionItemModel) {
        Surface(
            modifier = LayoutPadding(
                top = 4.dp,
                bottom = 4.dp
            ),
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = Border(1.dp, SolidColor(Color.Gray))
        ) {
            Surface(
                modifier = LayoutPadding(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp,
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
                        Image(imageResource(R.drawable.ic_right_arrow))
                        Text("$nextRevisionKilometer km")
                    }
                }
            }
        }
    }
}