package org.wa.usfmreader.persistence

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

interface UsfmFileIO {
    fun getUsfm(filename: String): Maybe<String>
    fun saveUsfm(filename: String, usfm: String): Maybe<String>
}