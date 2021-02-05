package br.com.joaogoes.ui

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun GenericErrorScreen(message: String) {
    Surface {
        Text(message)
    }
}