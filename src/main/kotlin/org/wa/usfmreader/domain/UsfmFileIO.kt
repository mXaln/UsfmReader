package org.wa.usfmreader.domain

import io.reactivex.Maybe
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData

interface UsfmFileIO {
    fun getUsfm(language: LanguageData, book: BookData): Maybe<String>
    fun saveUsfm(language: LanguageData, book: BookData, usfm: String): Maybe<String>
}