package org.wa.usfmreader.data.mappers

import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.BookEntity
import org.wa.usfmreader.domain.entities.LanguageEntity

class LanguageDataEntityMapper: Mapper<LanguageData, LanguageEntity>() {
    override fun mapFrom(from: LanguageData): LanguageEntity {
        return LanguageEntity(
                slug = from.slug,
                name = from.name,
                books = from.books.map { it as BookEntity }
        )
    }

}