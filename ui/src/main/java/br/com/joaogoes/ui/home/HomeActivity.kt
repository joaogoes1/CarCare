package br.com.joaogoes.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.padding
import androidx.ui.material.*
import androidx.ui.res.imageResource
import androidx.ui.unit.dp
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.ui.GenericErrorScreen
import br.com.joaogoes.ui.LoadingScreen
import br.com.joaogoes.ui.R
import br.com.joaogoes.ui.home.dialog.ItemDialog
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by inject()
    private var state: HomeViewState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val dialogState = state { false }
            MaterialTheme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                dialogState.value = true
                            }
                        ) {
                            Icon(imageResource(R.drawable.ic_plus))
                        }
                    },
                    topAppBar = {
                        TopAppBar(
                            title = {
                                Text(getString(R.string.app_name))
                            }
                        )
                    },
                    bodyContent = {
                        if (dialogState.value) {
                            ItemDialog(
                                context = applicationContext,
                                dismiss = { dialogState.value = false },
                                saveRevisionItem = { item ->
                                    viewModel.saveRevision(item)

                                }
                            )
                        }
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
                modifier = Modifier.padding(32.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(getString(R.string.home_activity_maintenance))
                    revisionList.map {
                        revisionListItem(it)
                    }
                }
            }
        }

    @Composable
    fun revisionListItem(revisionItem: RevisionItemModel) {
        Surface(
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 4.dp
            ),
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            border = Border(1.dp, SolidColor(Color.Gray))
        ) {
            Surface(
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
                color = Color.White,
                shape = RoundedCornerShape(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    with(revisionItem) {
                        Text(itemName)
                        Text(getString(R.string.home_activity_kilometer, currentRevisionKilometer))
                        Image(imageResource(R.drawable.ic_right_arrow))
                        Text(getString(R.string.home_activity_kilometer, nextRevisionKilometer))
                    }
                }
            }
        }
    }
}
