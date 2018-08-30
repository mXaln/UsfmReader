package org.wa.usfmreader.data.mappers

import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.BookEntity

class BookEntityDataMapper: Mapper<BookEntity, BookData>() {
    override fun mapFrom(from: BookEntity): BookData {
        return BookData(
                slug = from.slug,
                name = from.name,
                usfmUrl = from.usfmUrl,
                chapters = from.chapters.map { it as ChapterData }
        )
    }

}