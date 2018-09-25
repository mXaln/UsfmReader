package org.wa.usfmreader.persistence.db

import org.wa.usfmreader.persistence.dao.AppBookDao
import org.wa.usfmreader.persistence.dao.AppLanguageDao
import org.wa.usfmreader.persistence.dao.BookDaoImpl
import org.wa.usfmreader.persistence.dao.LanguageDaoImpl

class AppDatabaseImpl: AppDatabase {
    override fun getBookDao(): AppBookDao {
        return BookDaoImpl()
    }

    override fun getLanguageDao(): AppLanguageDao {
        return LanguageDaoImpl()
    }

}