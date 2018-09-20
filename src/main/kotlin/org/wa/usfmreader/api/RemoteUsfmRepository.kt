package org.wa.usfmreader.api

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.UsfmRepository

class RemoteUsfmRepository(private val api: UsfmApi): UsfmRepository {
    override fun getBookUsfm(book: BookData, language: LanguageData): Observable<String> {
        return api.getBookUsfm(book.usfmUrl)
    }
}