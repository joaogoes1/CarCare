package br.com.joaogoes.database.mapper

import br.com.joaogoes.database.entity.RevisionItemEntity
import br.com.joaogoes.model.RevisionItemModel

class RevisionItemMapper {

    fun mapFromEntity(from: RevisionItemEntity): RevisionItemModel =
        with(from) {
            RevisionItemModel(
                uid = uid,
                itemName = name,
                currentRevisionKilometer = initialKilometer,
                currentRevisionDate = initialDate,
                nextRevisionKilometer = targetKilometer,
                nextRevisionDate = targetDate
            )
        }

    fun mapFromModel(from: RevisionItemModel): RevisionItemEntity =
        with(from) {
            RevisionItemEntity(
                uid = uid,
                name = itemName,
                initialKilometer = currentRevisionKilometer,
                initialDate = currentRevisionDate,
                targetKilometer = nextRevisionKilometer,
                targetDate = nextRevisionDate
            )
        }
}