package org.wa.usfmreader.persistence

import org.jooq.*
import org.jooq.impl.DSL
import java.sql.Connection
import java.sql.DriverManager

class SqliteDB: DB {
    private val url: String = "jdbc:sqlite:ureader.sqlite"
    private lateinit var connection: Connection

    init {
        try {
            connection = DriverManager.getConnection(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun connection(): Connection {
        return connection
    }

    override fun DSL(): DSLContext {
        return DSL.using(connection)
    }

    override fun close() {
        connection.close()
    }
}