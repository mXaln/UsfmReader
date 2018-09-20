package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.UsfmParser
import org.wa.usfmreader.persistence.UsfmRepositoryImpl

class BookUsecase(private val repository: UsfmRepositoryImpl) {
    fun getBookWithChapters(book: BookData, language: LanguageData): Observable<BookData> {
        val usfm = repository.getBookUsfm(book, language)
        return UsfmParser().parse(usfm, book)
    }


}