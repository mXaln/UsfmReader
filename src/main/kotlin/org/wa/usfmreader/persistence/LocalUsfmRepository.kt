package org.wa.usfmreader.persistence

import io.reactivex.Maybe
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.UsfmRepository
import org.wa.usfmreader.persistence.db.AppDatabaseImpl

class LocalUsfmRepository(private val usfmFile: UsfmFile,
                          private val database: AppDatabaseImpl): UsfmRepository {

    override fun getBookUsfm(book: BookData, language: LanguageData): Maybe<String> {
        return database.getBookDao().fetchOneByBookAndLanguage(book, language)
                .flatMap {
                    usfmFile.getUsfm(language.slug + "_" + book.slug + ".usfm")
                }
    }

    fun saveBookUsfm(book: BookData, language: LanguageData, usfm: String): Maybe<String> {
        return usfmFile.saveUsfm(language.slug + "_" + book.slug + ".usfm", usfm)
                .flatMap {
                    val location = it
                    database.getLanguageDao().insert(language)
                            .flatMapMaybe {
                                val languageId = it
                                database.getBookDao().insert(book, languageId, location)
                                        .flatMapMaybe {
                                            Maybe.just(location)
                                        }
                            }
                }
    }
}