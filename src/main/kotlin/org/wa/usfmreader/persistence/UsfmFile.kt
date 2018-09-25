package org.wa.usfmreader.persistence

import io.reactivex.Maybe
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.UsfmFileIO
import java.io.File

class UsfmFile: UsfmFileIO {
    private val homeDir = System.getProperty("user.home")
    private val usfmStoragePath = listOf(homeDir, ".UsfmReader").joinToString(File.separator)

    init {
        if (!File(usfmStoragePath).exists()) {
            File(usfmStoragePath).mkdir()
        }
    }

    override fun getUsfm(language: LanguageData, book: BookData): Maybe<String> {
        val location = listOf(usfmStoragePath, language.slug + "_" + book.slug + ".usfm")
                .joinToString(File.separator)
        try {
            return Maybe.fromCallable {
                File(location).bufferedReader()
                        .use { out ->
                            out.readText()
                        }
            }
        } catch (e: Exception) {
            println(e.message)
            return Maybe.empty()
        }
    }

    override fun saveUsfm(language: LanguageData, book: BookData, usfm: String): Maybe<String> {
        val location = listOf(usfmStoragePath, language.slug + "_" + book.slug + ".usfm")
                .joinToString(File.separator)
        return Maybe.fromCallable {
            File(location).bufferedWriter()
                    .use { out ->
                        out.write(usfm)
                    }
            location
        }
    }
}
