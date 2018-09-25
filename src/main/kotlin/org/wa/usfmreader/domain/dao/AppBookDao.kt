package org.wa.usfmreader.domain.dao

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData

interface AppBookDao {
    fun fetchOneBySlug(slug: String): Maybe<BookData>
    fun fetchOneByBookAndLanguage(book: BookData, language: LanguageData): Maybe<BookData>
    fun fetchBySlug(slug: String): Single<List<BookData>>
    fun fetchAll(): Single<List<BookData>>
    fun insert(obj: BookData, languageId: Int, location: String): Single<Int>
    fun update(obj: BookData): Completable
    fun delete(obj: BookData): Completable
}