package org.wa.usfmreader.domain.db

import org.jooq.*
import java.sql.Connection

interface DB {
    fun connection(): Connection
    fun configuration(): Configuration
    fun dsl(): DSLContext
    fun createDatabase()
    fun close()
}