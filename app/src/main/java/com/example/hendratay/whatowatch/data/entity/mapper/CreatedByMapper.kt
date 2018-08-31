package com.example.hendratay.whatowatch.data.entity.mapper

import com.example.hendratay.whatowatch.data.entity.CreatedByEntity
import com.example.hendratay.whatowatch.domain.model.CreatedBy
import javax.inject.Inject

class CreatedByMapper @Inject constructor(): Mapper<CreatedByEntity, CreatedBy> {

    override fun mapFromEntity(type: CreatedByEntity): CreatedBy {
        return CreatedBy(type.id, type.creditId, type.name, type.gender, type.profilePath)
    }

    override fun mapToEntity(type: CreatedBy): CreatedByEntity {
        return CreatedByEntity(type.id, type.creditId, type.name, type.gender, type.profilePath)
    }

    fun mapFromEntity(list: List<CreatedByEntity>): List<CreatedBy> {
        return list.map { mapFromEntity(it) }
    }

    fun mapToEntity(list: List<CreatedBy>): List<CreatedByEntity> {
        return list.map { mapToEntity(it) }
    }

}
