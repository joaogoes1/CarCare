package br.com.joaogoes.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.joaogoes.model.RevisionItemModel
import br.com.joaogoes.ui.GenericErrorScreen
import br.com.joaogoes.ui.LoadingScreen
import br.com.joaogoes.ui.R
import br.com.joaogoes.ui.home.dialog.ItemDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen(viewModel = viewModel)
        }
    }


    @Composable
    fun Screen(viewModel: HomeViewModel) {
        val screenState = viewModel.viewState.observeAsState()
        val dialogState = mutableStateOf(false)

        MaterialTheme(
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
        }
    }

    @Composable
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
                    }
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
            ) {
                with(revisionItem) {
                    Text(itemName)
                    Row {
                        Text(
                            getString(
                                R.string.home_activity_kilometer,
                                currentRevisionKilometer
                            )
                        )
                        Image(ImageBitmap.imageResource(R.drawable.ic_right_arrow), null)
                        Text(getString(R.string.home_activity_kilometer, nextRevisionKilometer))
                    }
                }
            }
        }

    }

    @Preview
    @Composable
    fun preview() {
        RevisionListItem(
            revisionItem = RevisionItemModel(
                uid = 0,
                itemName = "Item teste",
                currentRevisionKilometer = 100.000.toLong(),
                nextRevisionKilometer = 100.000.toLong()
            )
        )
    }
}
