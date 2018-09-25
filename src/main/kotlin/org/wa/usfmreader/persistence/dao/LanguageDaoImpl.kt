package org.wa.usfmreader.persistence.dao

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import jooq.tables.daos.LanguageDao
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.dao.AppLanguageDao
import org.wa.usfmreader.domain.db.SqliteDB
import org.wa.usfmreader.persistence.mappers.LanguagesDaoMapper
import java.lang.Exception

class LanguageDaoImpl: AppLanguageDao {
    private val db = SqliteDB()
    private val languageDao = LanguageDao(db.configuration())
    private val mapper = LanguagesDaoMapper()

    override fun fetchOneBySlug(slug: String): Maybe<LanguageData> {
        return Maybe.fromCallable {
            mapper.mapFromLanguage(
                    languageDao.fetchOneBySlug(slug)
            )
        }
    }

    override fun fetchAll(): Single<List<LanguageData>> {
        return Single.fromCallable {
            languageDao.findAll().map {
                mapper.mapFromLanguage(it)
            }
        }
    }

    override fun fetchBySlug(slug: String): Single<List<LanguageData>> {
        return Single.fromCallable {
            languageDao.fetchBySlug(slug)
                    .map { mapper.mapFromLanguage(it) }
        }
    }

    override fun insert(obj: LanguageData): Single<Int> {
        return Single.fromCallable {
            try {
                languageDao.insert(mapper.mapFromLanguageData(obj))
            } catch (e: Exception) {
                println(e.message)
            }
            languageDao.fetchOneBySlug(obj.slug).id
        }
    }

    override fun update(obj: LanguageData): Completable {
        return Completable.fromAction {
            languageDao.update(mapper.mapFromLanguageData(obj))
        }
    }

    override fun delete(obj: LanguageData): Completable {
        return Completable.fromAction {
            languageDao.delete(mapper.mapFromLanguageData(obj))
        }
    }
}