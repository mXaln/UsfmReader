package org.wa.usfmreader.persistence

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.UsfmRepository

class LocalUsfmRepository(private val cache: UsfmCache): UsfmRepository {
    override fun getBookUsfm(book: BookData, language: LanguageData): Observable<String> {
        return cache.getBookUsfm(book, language)
    }

    fun saveBookUsfm(book: BookData, language: LanguageData, usfm: String) {
        cache.saveBookUsfm(book, language, usfm)
    }
}