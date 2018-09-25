package org.wa.usfmreader.persistence.mappers

import jooq.tables.pojos.Book
import org.wa.usfmreader.data.entities.BookData

class BooksDaoMapper {
    fun mapFromBook(from: Book): BookData {
        return BookData(
            slug = from.slug,
            name = from.name,
            sort = from.sort,
            usfmUrl = "",
            chapters = listOf()
        )
    }

    fun mapFromBookData(from: BookData): Book {
        val book = Book()
        book.slug = from.slug
        book.name = from.name
        book.sort = from.sort
        return book
    }
}