package org.wa.usfmreader.domain

import io.reactivex.Observable
import org.wa.usfmreader.domain.entities.BookEntity
import org.wa.usfmreader.domain.entities.ChapterEntity
import org.wa.usfmreader.domain.entities.LanguageEntity

interface CatalogDataStore {

    fun getLanguages(): Observable<List<LanguageEntity>>

    fun getLanguage(langSlug: String): Observable<LanguageEntity>

    fun getBooks(langSlug: String): Observable<List<BookEntity>>

    fun getBook(bookSlug: String): Observable<BookEntity>

    fun getChapters(bookSlug: String): Observable<List<ChapterEntity>>

    fun getChapter(number: Int): Observable<ChapterEntity>

    fun getBookUsfm(bookSlug: String): Observable<String>
}