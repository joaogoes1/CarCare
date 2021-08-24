package br.com.joaogoes.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import androidx.compose.foundation.Image
=======
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
<<<<<<< HEAD
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
=======
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.ui.GenericErrorScreen
import br.com.joaogoes.ui.LoadingScreen
import br.com.joaogoes.ui.R
import br.com.joaogoes.ui.home.dialog.ItemDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

<<<<<<< HEAD
    private val viewModel: HomeViewModel by viewModel()
=======
    private val viewModel: HomeViewModel by inject()
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
<<<<<<< HEAD
            Screen(viewModel = viewModel)
        }
    }


    @Composable
    fun Screen(viewModel: HomeViewModel) {
=======
            screen(viewModel = viewModel)
        }
    }

//    @Composable
//    private fun <T> observeState(data: LiveData<T>): T? {
//        val result = remember { mutableStateOf(data.value) }
//        val observer = remember { Observer<T> { result.value = it } }
//
//        onCommit(data) {
//            data.observeForever(observer)
//            onDispose { data.removeObserver(observer) }
//        }
//        return result.value
//    }

    @Composable
    fun screen(viewModel: HomeViewModel) {
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
        val screenState = viewModel.viewState.observeAsState()
        val dialogState = mutableStateOf(false)

        MaterialTheme(
<<<<<<< HEAD
            colors = lightColors(
                primary = Color(255, 23, 68),
                primaryVariant = Color(255, 97, 111),
                secondary = Color(196, 0, 29)
            )
        ) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            dialogState.value = true
                        }
                    ) {
                        Icon(ImageBitmap.imageResource(R.drawable.ic_plus), null)
                    }
                },
                topBar = {
                    TopAppBar(
                        title = {
                            Text(getString(R.string.app_name))
                        }
                    )
                },
            ) {
                if (dialogState.value) {
                    ItemDialog(
                        context = applicationContext,
                        dismiss = { dialogState.value = false },
                        action = { item -> viewModel.saveRevision(item) }
                    )
                }
                BuildLayout(viewState = screenState.value)
            }
=======
                colors = lightColors(
                        primary = Color(255, 23, 68),
                        primaryVariant = Color(255, 97, 111),
                        secondary = Color(196, 0, 29)
                )
        ) {
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
                    topBar = {
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
                                    action = { item -> viewModel.saveRevision(item) }
                            )
                        }
                        buildLayout(viewState = screenState.value)
                    }
            )
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
        }
    }

    @Composable
<<<<<<< HEAD
    private fun BuildLayout(viewState: HomeViewState?) =
        when (viewState) {
            is HomeViewState.Loading -> LoadingScreen()
            is HomeViewState.Success -> KilometersScreen(viewState.revisionItems)
            is HomeViewState.Error -> GenericErrorScreen(
                viewState.message ?: getString(R.string.generic_error_message)
            )
            else -> GenericErrorScreen(getString(R.string.generic_error_message))
        }

    @Composable
    fun KilometersScreen(revisionList: List<RevisionItemModel>) =
        Column {
            Column(
                modifier = Modifier.padding(32.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        getString(R.string.home_activity_maintenance),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    revisionList.map {
                        RevisionListItem(it)
=======
    private fun buildLayout(viewState: HomeViewState?) =
            when (viewState) {
                is HomeViewState.Loading -> LoadingScreen()
                is HomeViewState.Success -> KilometersScreen(viewState.revisionItems)
                is HomeViewState.Error -> GenericErrorScreen(
                        viewState.message ?: getString(R.string.generic_error_message)
                )
                else -> GenericErrorScreen(getString(R.string.generic_error_message))
            }

    @Composable
    fun KilometersScreen(revisionList: List<RevisionItemModel>) =
            Column {
                ScrollableColumn(
                        modifier = Modifier.padding(32.dp)
                ) {
                    Column(
                            verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                                getString(R.string.home_activity_maintenance),
                                modifier = Modifier.padding(bottom = 4.dp)
                        )
                        revisionList.map {
                            revisionListItem(it)
                        }
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
                    }
                }
            }

    @Composable
    fun RevisionListItem(revisionItem: RevisionItemModel) {
        Box(
//            modifier = Modifier.padding(
//                top = 4.dp,
//                bottom = 4.dp
//            ).fillMaxWidth(),
<<<<<<< HEAD
            Modifier.background(
                SolidColor(Color.White),
                shape = RoundedCornerShape(16.dp)
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
=======
                Modifier.background(
                        SolidColor(Color.White),
                        shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                    modifier = Modifier
                            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                            .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
            ) {
                with(revisionItem) {
                    Text(itemName)
                    Row {
                        Text(
<<<<<<< HEAD
                            getString(
                                R.string.home_activity_kilometer,
                                currentRevisionKilometer
                            )
                        )
                        Image(ImageBitmap.imageResource(R.drawable.ic_right_arrow), null)
=======
                                getString(
                                        R.string.home_activity_kilometer,
                                        currentRevisionKilometer
                                )
                        )
                        Image(imageResource(R.drawable.ic_right_arrow))
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
                        Text(getString(R.string.home_activity_kilometer, nextRevisionKilometer))
                    }
                }
            }
        }

    }

    @Preview
    @Composable
    fun preview() {
<<<<<<< HEAD
        RevisionListItem(
            revisionItem = RevisionItemModel(
                uid = 0,
                itemName = "Item teste",
                currentRevisionKilometer = 100.000.toLong(),
                nextRevisionKilometer = 100.000.toLong()
            )
=======
        revisionListItem(
                revisionItem = RevisionItemModel(
                        uid = 0,
                        itemName = "Item teste",
                        currentRevisionKilometer = 100.000.toLong(),
                        nextRevisionKilometer = 100.000.toLong()
                )
>>>>>>> 31fcd1b7be4a99ec294a42a24c1514f2750a735b
        )
    }
}
