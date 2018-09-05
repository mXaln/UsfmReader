package org.wa.usfmreader.persistence

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData

interface CatalogRepository {

    fun getLanguages(): Observable<List<LanguageData>>

    fun getLanguage(langSlug: String): Observable<LanguageData>

    fun getBooks(langSlug: String): Observable<List<BookData>>

    fun getBook(bookSlug: String): Observable<BookData>

    fun getChapters(bookSlug: String): Observable<List<ChapterData>>

    fun getChapter(number: Int): Observable<ChapterData>

    fun getBookUsfm(usfmUrl: String): Observable<String>
}