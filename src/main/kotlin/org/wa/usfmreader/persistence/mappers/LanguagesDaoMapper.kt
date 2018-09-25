package org.wa.usfmreader.persistence.mappers

import jooq.tables.pojos.Language
import org.wa.usfmreader.data.entities.LanguageData

class LanguagesDaoMapper {
   fun mapFromLanguage(from: Language): LanguageData {
        return LanguageData(
            slug = from.slug,
            name = from.name,
            direction = from.direction,
            books = listOf()

        )
    }

    fun mapFromLanguageData(from: LanguageData): Language {
        val lang = Language()
        lang.slug = from.slug
        lang.name = from.name
        lang.direction = from.direction
        return lang
    }
}