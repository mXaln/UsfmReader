package org.wa.usfmreader.data.mappers

import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.ChapterEntity

class ChapterDataEntityMapper: Mapper<ChapterData, ChapterEntity>() {
    override fun mapFrom(from: ChapterData): ChapterEntity {
        return ChapterEntity(
                number = from.number,
                text = from.text
        )
    }

}