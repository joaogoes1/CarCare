package br.com.joaogoes.model

import java.util.*

data class RevisionItemModel(
    val uid: Int? = null,
    val itemName: String,
    val currentRevisionKilometer: Long,
    val currentRevisionDate: Calendar? = null,
    val nextRevisionKilometer: Long,
    val nextRevisionDate: Calendar? = null
)