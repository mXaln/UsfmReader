package org.wa.usfmreader.domain

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData

interface UsfmRepository {
    fun getBookUsfm(book: BookData): Observable<String>
}