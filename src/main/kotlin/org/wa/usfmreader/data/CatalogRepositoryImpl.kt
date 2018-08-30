package org.wa.usfmreader.data

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.CatalogDataStore
import org.wa.usfmreader.domain.CatalogRepository
import org.wa.usfmreader.domain.Mapper
import org.wa.usfmreader.domain.entities.BookEntity
import org.wa.usfmreader.domain.entities.ChapterEntity
import org.wa.usfmreader.domain.entities.LanguageEntity

class CatalogRepositoryImpl(api: Api,
                            languageDataMapper: Mapper<LanguageData, LanguageEntity>): CatalogRepository {

    private val remoteDataStore: CatalogDataStore

    init {
        remoteDataStore = RemoteCatalogDataStore(api, languageDataMapper)
    }

    override fun getLanguages(): Observable<List<LanguageEntity>> {
        return remoteDataStore.getLanguages()
    }

    override fun getLanguage(langSlug: String): Observable<LanguageEntity> {
        return remoteDataStore.getLanguage(langSlug)
    }

    override fun getBooks(langSlug: String): Observable<List<BookEntity>> {
        return remoteDataStore.getBooks(langSlug)
    }

    override fun getBook(bookSlug: String): Observable<BookEntity> {
        return remoteDataStore.getBook(bookSlug)
    }

    override fun getChapters(bookSlug: String): Observable<List<ChapterEntity>> {
        return remoteDataStore.getChapters(bookSlug)
    }

    override fun getChapter(number: Int): Observable<ChapterEntity> {
        return remoteDataStore.getChapter(number)
    }

    override fun getBookUsfm(bookSlug: String): Observable<String> {
        return remoteDataStore.getBookUsfm(bookSlug)
    }

}