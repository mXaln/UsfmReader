package org.wa.usfmreader.api.mappers

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
                    .filter { listOf("ulb","avd").contains(it.identifier) } // filter resources that don't have these projects
                    .flatMap { resourceResult -> resourceResult.projects
                            .map { mapper.mapFrom(it) } }

        )
    }
}