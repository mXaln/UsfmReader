package org.wa.usfmreader.persistence

import io.reactivex.Observable
import org.wa.usfmreader.api.UsfmApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.ChapterData
import org.wa.usfmreader.data.entities.LanguageData

class RemoteUsfmRepository(private val api: UsfmApi): UsfmRepository {
    override fun getBookUsfm(usfmUrl: String): Observable<String> {
        return api.getBookUsfm(usfmUrl)
    }
}