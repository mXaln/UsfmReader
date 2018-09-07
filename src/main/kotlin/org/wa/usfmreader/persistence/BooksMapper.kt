package org.wa.usfmreader.persistence

import org.wa.usfmreader.api.models.ProjectResult
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData

class BooksMapper: Mapper<ProjectResult, BookData>() {
    override fun mapFrom(from: ProjectResult): BookData {
        return BookData(
            slug = from.identifier,
            name = from.title,
            sort = from.sort,
            usfmUrl = from.formats
                    .singleOrNull { it.format == "text/usfm" }!!.url,
            chapters = listOf<ChapterData>()
        )
    }
}