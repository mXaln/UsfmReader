package org.wa.usfmreader.persistence.db

import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement


class SqliteDB: DB {
    private val homeDir = System.getProperty("user.home")
    private val dbPath = listOf(homeDir, ".UsfmReader", "ureader.sqlite").joinToString(File.separator)
    private val url: String = "jdbc:sqlite:$dbPath"
    private lateinit var connection: Connection

    init {
        try {
            connection = DriverManager.getConnection(url)
            createDatabase()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override fun connection(): Connection {
        return connection
    }

    override fun configuration(): Configuration {
        return DefaultConfiguration().set(connection).set(SQLDialect.SQLITE)
    }

    override fun dsl(): DSLContext {
        return DSL.using(connection)
    }

    override fun createDatabase() {
        val sql = this::class.java.classLoader.getResource("ureader.sql").readText()
        val sqlcmds: List<String> = sql.split(";")
        sqlcmds.map { it.trim() }.filter { !it.isEmpty() && !it.startsWith("--")}.forEach {
            val stmt: Statement = connection.createStatement()
            stmt.execute(it)
        }
    }

    override fun close() {
        connection.close()
    }
}