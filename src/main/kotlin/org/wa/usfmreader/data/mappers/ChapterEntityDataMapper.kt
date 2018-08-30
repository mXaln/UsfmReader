package org.wa.usfmreader.data.mappers

import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.ChapterEntity

class ChapterEntityDataMapper: Mapper<ChapterEntity, ChapterData>() {
    override fun mapFrom(from: ChapterEntity): ChapterData {
        return ChapterData(
                number = from.number,
                text = from.text
        )
    }

}