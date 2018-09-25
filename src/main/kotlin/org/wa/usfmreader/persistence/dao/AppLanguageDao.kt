package org.wa.usfmreader.persistence.dao

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import org.wa.usfmreader.data.entities.LanguageData

interface AppLanguageDao {
    fun fetchOneBySlug(slug: String): Maybe<LanguageData>
    fun fetchAll(): Single<List<LanguageData>>
    fun fetchAllBySlug(slug: String): Single<List<LanguageData>>
    fun insert(obj: LanguageData): Single<Int>
    fun update(obj: LanguageData): Completable
    fun delete(obj: LanguageData): Completable
}