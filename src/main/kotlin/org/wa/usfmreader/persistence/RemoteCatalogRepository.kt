package org.wa.usfmreader.persistence

import io.reactivex.Observable
import org.wa.usfmreader.api.CatalogApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData

class RemoteCatalogRepository(private val api: CatalogApi): CatalogRepository {

    override fun getLanguages(): Observable<List<LanguageData>> {
        val mapper = LanguagesMapper()
        return api.getLanguages().map { results ->
            results.languages.map { mapper.mapFrom(it) }
        }
    }

    override fun getLanguage(langSlug: String): Observable<LanguageData> {
        TODO("not implemented")
    }

    override fun getBooks(langSlug: String): Observable<List<BookData>> {
        TODO("not implemented")
    }

    override fun getBook(bookSlug: String): Observable<BookData> {
        TODO("not implemented")
    }

    override fun getChapters(bookSlug: String): Observable<List<ChapterData>> {
        TODO("not implemented")
    }

    override fun getChapter(number: Int): Observable<ChapterData> {
        TODO("not implemented")
    }

    override fun getBookUsfm(usfmUrl: String): Observable<String> {
        TODO("not implemented")
    }

}