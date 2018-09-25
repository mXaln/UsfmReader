package org.wa.usfmreader.persistence.db

import org.wa.usfmreader.persistence.dao.AppBookDao
import org.wa.usfmreader.persistence.dao.AppLanguageDao

interface AppDatabase {
    fun getBookDao(): AppBookDao
    fun getLanguageDao(): AppLanguageDao
}