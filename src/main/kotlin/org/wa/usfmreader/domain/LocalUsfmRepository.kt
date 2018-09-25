package org.wa.usfmreader.domain

import io.reactivex.Maybe
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData

interface LocalUsfmRepository: UsfmRepository {
    override fun getBookUsfm(book: BookData, language: LanguageData): Maybe<String>
    fun saveBookUsfm(book: BookData, language: LanguageData, usfm: String): Maybe<String>
}