package org.wa.usfmreader.persistence.db

import org.wa.usfmreader.domain.db.AppDatabase
import org.wa.usfmreader.domain.dao.AppBookDao
import org.wa.usfmreader.domain.dao.AppLanguageDao
import org.wa.usfmreader.persistence.dao.BookDaoImpl
import org.wa.usfmreader.persistence.dao.LanguageDaoImpl

class AppDatabaseImpl: AppDatabase {

    private val bookDao = BookDaoImpl()
    private val languageDao = LanguageDaoImpl()

    override fun getBookDao(): AppBookDao {
        return bookDao
    }

    override fun getLanguageDao(): AppLanguageDao {
        return languageDao
    }

}