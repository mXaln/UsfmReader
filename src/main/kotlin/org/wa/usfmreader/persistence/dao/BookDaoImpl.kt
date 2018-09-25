package org.wa.usfmreader.persistence.dao

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import jooq.Tables
import jooq.tables.daos.BookDao
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.dao.AppBookDao
import org.wa.usfmreader.domain.db.SqliteDB
import org.wa.usfmreader.persistence.mappers.BooksDaoMapper

class BookDaoImpl: AppBookDao {
    private val db = SqliteDB()
    private val bookDao = BookDao(db.configuration())
    private val mapper = BooksDaoMapper()

    override fun fetchOneBySlug(slug: String): Maybe<BookData> {
        return Maybe.fromCallable {
            mapper.mapFromBook(
                    bookDao.fetchBySlug(slug).first()
            )
        }.onErrorComplete()
    }

    /***
     * Probably it's not a good place for this func here
     */
    override fun fetchOneByBookAndLanguage(book: BookData, language: LanguageData): Maybe<BookData> {
//        val res = db.dsl().select()
//                .from(Tables.BOOK)
//                .join(Tables.LANGUAGE).on(Tables.LANGUAGE.ID.eq(Tables.BOOK.LANGUAGE_ID))
//                .where(Tables.BOOK.SLUG.eq(book.slug))
//                .and(Tables.LANGUAGE.SLUG.eq(language.slug))
//                .fetchOne()
////                .limit(1)
////                .fetch()
//
//        return Maybe.just(res.map {
//            mapper.mapFromBook(
//                    bookDao.mapper().map(it as BookRecord))
//        })

        return Maybe.fromCallable {
            val res = db.dsl().select()
                    .from(Tables.BOOK)
                    .join(Tables.LANGUAGE).on(Tables.LANGUAGE.ID.eq(Tables.BOOK.LANGUAGE_ID))
                    .where(Tables.BOOK.SLUG.eq(book.slug))
                    .and(Tables.LANGUAGE.SLUG.eq(language.slug))
                    .fetchOneInto(Tables.BOOK)

            mapper.mapFromBook(
                    bookDao.mapper().map(res)
            )
        }
    }

    override fun fetchAll(): Single<List<BookData>> {
        return Single.fromCallable {
            bookDao.findAll().map {
                mapper.mapFromBook(it)
            }
        }
    }

    override fun fetchBySlug(slug: String): Single<List<BookData>> {
        return Single.fromCallable {
            bookDao.fetchBySlug(slug).map {
                mapper.mapFromBook(it)
            }
        }
    }

    override fun insert(obj: BookData, languageId: Int, location: String): Single<Int> {
        return Single.fromCallable {
            try {
                val book = mapper.mapFromBookData(obj)
                book.languageId = languageId
                book.location = location
                bookDao.insert(book)
            } catch (e: Exception) {
                println(e.message)
            }
            bookDao.fetchBySlug(obj.slug).first().id
        }
    }

    override fun update(obj: BookData): Completable {
        return Completable.fromAction {
            bookDao.update(mapper.mapFromBookData(obj))
        }
    }

    override fun delete(obj: BookData): Completable {
        return Completable.fromAction {
            bookDao.delete(mapper.mapFromBookData(obj))
        }
    }
}