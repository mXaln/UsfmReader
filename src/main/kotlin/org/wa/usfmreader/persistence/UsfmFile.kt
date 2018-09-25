package org.wa.usfmreader.persistence

import io.reactivex.Maybe
import java.io.File

class UsfmFile: UsfmFileIO {
    private val homeDir = System.getProperty("user.home")
    private val usfmStoragePath = listOf(homeDir, ".UsfmReader").joinToString(File.separator)

    init {
        if (!File(usfmStoragePath).exists()) {
            File(usfmStoragePath).mkdir()
        }
    }

    override fun getUsfm(filename: String): Maybe<String> {
        val location = listOf(usfmStoragePath, filename)
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
            return Maybe.just("")
        }
    }

    override fun saveUsfm(filename: String, usfm: String): Maybe<String> {
        val location = listOf(usfmStoragePath, filename)
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
