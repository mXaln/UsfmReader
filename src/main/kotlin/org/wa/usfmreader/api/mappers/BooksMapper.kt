package org.wa.usfmreader.api.mappers

import org.wa.usfmreader.api.models.ProjectResult
import org.wa.usfmreader.data.entities.BookData

class BooksMapper: Mapper<ProjectResult, BookData>() {
    override fun mapFrom(from: ProjectResult): BookData {
        return BookData(
            slug = from.identifier,
            name = from.title.trim(),
            sort = from.sort,
            usfmUrl = from.formats
                    .singleOrNull { it.format == "chapterText/usfm" }?.url ?: "",
            chapters = listOf()
        )
    }
}