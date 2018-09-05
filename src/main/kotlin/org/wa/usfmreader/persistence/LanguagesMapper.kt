package org.wa.usfmreader.persistence

import org.wa.usfmreader.api.models.LanguageResult
import org.wa.usfmreader.data.entities.LanguageData

class LanguagesMapper: Mapper<LanguageResult, LanguageData>() {
    override fun mapFrom(from: LanguageResult): LanguageData {
        val mapper = BooksMapper()
        return LanguageData(
            slug = from.identifier,
            name = from.title,
            direction = from.direction,
            books = from.resources
                    .filter { it.identifier == "ulb" }
                    .flatMap { recourceResult -> recourceResult.projects
                            .map { mapper.mapFrom(it) } }

        )
    }
}