package org.wa.usfmreader.domain.db

import org.wa.usfmreader.domain.dao.AppBookDao
import org.wa.usfmreader.domain.dao.AppLanguageDao

interface AppDatabase {
    fun getBookDao(): AppBookDao
    fun getLanguageDao(): AppLanguageDao
}