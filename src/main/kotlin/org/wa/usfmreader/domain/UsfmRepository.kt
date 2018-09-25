package org.wa.usfmreader.domain

import io.reactivex.Maybe
import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData

interface UsfmRepository {
    fun getBookUsfm(book: BookData, language: LanguageData): Maybe<String>
}