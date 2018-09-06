package org.wa.usfmreader.persistence

import io.reactivex.Observable

interface UsfmRepository {
    fun getBookUsfm(usfmUrl: String): Observable<String>
}