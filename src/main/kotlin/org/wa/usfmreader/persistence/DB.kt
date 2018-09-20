package org.wa.usfmreader.persistence

import org.jooq.*
import java.sql.Connection

interface DB {
    fun connection(): Connection
    fun DSL(): DSLContext
    fun close()
}