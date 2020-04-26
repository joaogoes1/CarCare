package br.com.joaogoes.ui

import androidx.compose.Composable
import androidx.ui.foundation.Text
import androidx.ui.material.Surface

@Composable
fun GenericErrorScreen(message: String) {
    Surface {
        Text(message)
    }
}