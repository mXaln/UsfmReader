package org.wa.usfmreader.data.mappers

import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.BookEntity
import org.wa.usfmreader.domain.entities.ChapterEntity

class BookDataEntityMapper: Mapper<BookData, BookEntity>() {
    override fun mapFrom(from: BookData): BookEntity {
        return BookEntity(
                slug = from.slug,
                name = from.name,
                usfmUrl = from.usfmUrl,
                chapters = from.chapters.map { it as ChapterEntity }
        )
    }

}