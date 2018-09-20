package org.wa.usfmreader.persistence

import io.reactivex.Observable
import jooq.Tables.BOOKS
import jooq.Tables.LANGUAGES
import jooq.tables.records.BooksRecord
import jooq.tables.records.LanguagesRecord
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import java.io.File

const val USFM_STORAGE_PATH = "."

class UsfmCache {
    private val db = SqliteDB()

    fun getBookUsfm(book: BookData, language: LanguageData): Observable<String> {
        val result = db.DSL().select()
                .from(BOOKS)
                .join(LANGUAGES).on(LANGUAGES.ID.eq(BOOKS.LANGUAGE_ID))
                .where(BOOKS.SLUG.eq(book.slug))
                .and(LANGUAGES.SLUG.eq(language.slug))
                .fetchOne()

        if (result != null) {
            val location = USFM_STORAGE_PATH + File.separator + language.slug + "_" + book.slug + ".usfm"
            File(location).bufferedReader()
                    .use { out ->
                        val usfm = out.readText()
                        return Observable.just(usfm)
                    }
        }
        return Observable.just("")
    }

    fun saveBookUsfm(book: BookData, language: LanguageData, usfm: String) {
        val location = USFM_STORAGE_PATH + File.separator + language.slug + "_" + book.slug + ".usfm"
        File(location).bufferedWriter()
                .use { out ->
                    out.write(usfm)
                }

        if (File(location).exists()) {
            val languageResult = saveLanguageToDB(language)
            println(languageResult)
            if (languageResult != null) {
                val booksResult = saveBookToDB(book, languageResult.getValue(LANGUAGES.ID), location)

                if (booksResult != null) {
                    println("Book has been saved to the cache")
                }
            }
        }
    }

    private fun saveLanguageToDB(language: LanguageData): LanguagesRecord? {
        val existent = db.DSL().fetchOne(LANGUAGES, LANGUAGES.SLUG.eq(language.slug))
        return existent ?: db.DSL()
                .insertInto(LANGUAGES, LANGUAGES.SLUG, LANGUAGES.NAME, LANGUAGES.DIRECTION)
                .values(language.slug, language.name, language.direction)
                .returning(LANGUAGES.ID)
                .fetchOne()
    }

    private fun saveBookToDB(book: BookData, language_id: Int, location: String): BooksRecord? {
        val existent = db.DSL().fetchOne(BOOKS, BOOKS.SLUG.eq(book.slug))
        return existent ?: db.DSL()
                .insertInto(BOOKS, BOOKS.LANGUAGE_ID, BOOKS.SLUG, BOOKS.NAME, BOOKS.LOCATION)
                .values(language_id, book.slug, book.name, location)
                .returning(BOOKS.ID)
                .fetchOne()
    }
}