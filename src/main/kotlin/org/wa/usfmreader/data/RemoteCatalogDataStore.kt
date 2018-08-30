package org.wa.usfmreader.data

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.data.mappers.LanguageEntityDataMapper
import org.wa.usfmreader.domain.CatalogDataStore
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.BookEntity
import org.wa.usfmreader.domain.entities.ChapterEntity
import org.wa.usfmreader.domain.entities.LanguageEntity

class RemoteCatalogDataStore(private val api: Api,
                             private val languageDataMapper: Mapper<LanguageData, LanguageEntity>): CatalogDataStore {

    override fun getLanguages(): Observable<List<LanguageEntity>> {
        return api.getLanguages().map { results ->
            results.languages.map { languageDataMapper.mapFrom(it) }
        }
    }

    override fun getLanguage(langSlug: String): Observable<LanguageEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBooks(langSlug: String): Observable<List<BookEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBook(bookSlug: String): Observable<BookEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChapters(bookSlug: String): Observable<List<ChapterEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChapter(number: Int): Observable<ChapterEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookUsfm(bookSlug: String): Observable<String> {
        return api.getBookUsfm()
    }

}