package org.wa.usfmreader.domain.usecases

import io.reactivex.Observable
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.persistence.UsfmRepository

class BookUsecase(private val repository: UsfmRepository) {
    fun getBook(data: BookData): Observable<BookData> {
        return parse(data, repository.getBookUsfm(data.usfmUrl))
    }

    private fun parse(book: BookData, usfm: Observable<String>): Observable<BookData> {
        return usfm.map {
            book.chapters = getChapters(it)
            book
        }
    }

    private fun isChapterTag(text: String): Boolean {
        val regex = """\\c\s[0-9]+""".toRegex()
        return regex.matches(text)
    }

    private fun getChapterNumber(text: String): Int {
        val regex = """\\c\s([0-9]+)""".toRegex()
        val result = regex.find(text)

        if (result != null) {
            return result.groupValues[1].toInt()
        }

        return 0
    }

    private fun isVerseTag(text: String): Boolean {
        val regex = """\\v\s[0-9]+""".toRegex()
        return regex.containsMatchIn(text)
    }

    private fun getVerseNumber(text: String): Int {
        val regex = """\\v\s([0-9]+)""".toRegex()
        val result = regex.find(text)

        if (result != null) {
            return result.groupValues[1].toInt()
        }

        return 0
    }

    private fun getVerseText(text: String): String {
        val regex = """\\v\s[0-9]+(.*)""".toRegex()
        val result = regex.find(text)

        if (result != null) {
            return result.groupValues[1]
        }

        return ""
    }

    private fun processFootnotes(text: String): String {
        val regex = """\\f(?:\s\+\s\\f[a-z]+)?\s(.*)\s\\f\*""".toRegex()
        val regex2 = """\\fqa\s(.*)\s\\fqa\*""".toRegex()
        return regex.replace(text) {
            regex2.replace("«${it.groupValues[1]}»") {
                "\"${it.groupValues[1]}\""
            }
        }
    }

    private fun getChapters(usfm: String): List<ChapterData> {
        val regex = """\n\r|\n|\r""".toRegex()
        val lines = regex.split(usfm)
        val chapters = mutableListOf<ChapterData>()
        var chapter: ChapterData? = null

        for (line in lines) {
            if (isChapterTag(line)) {
                if (chapter != null) {
                    chapters.add(chapter)
                }

                chapter = ChapterData(
                        number = getChapterNumber(line),
                        text = ""
                )
                continue
            }

            if (isVerseTag(line)) {
                if (chapter != null) {
                    var verse = "${getVerseNumber(line)}. ${getVerseText(line)} "
                    verse = processFootnotes(verse)

                    chapter.text += verse
                }
            }
        }

        if (chapter != null) {
            chapters.add(chapter)
        }

        return chapters
    }
}