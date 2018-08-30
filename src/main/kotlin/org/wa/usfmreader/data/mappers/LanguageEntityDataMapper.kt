package org.wa.usfmreader.data.mappers

import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.LanguageEntity

class LanguageEntityDataMapper: Mapper<LanguageEntity, LanguageData>() {

    override fun mapFrom(from: LanguageEntity): LanguageData {
        return LanguageData(
                slug = from.slug,
                name = from.name,
                books = from.books.map { it as BookData }
        )
    }

}