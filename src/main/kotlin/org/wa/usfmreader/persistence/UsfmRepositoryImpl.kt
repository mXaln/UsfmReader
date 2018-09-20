package org.wa.usfmreader.persistence

import io.reactivex.Observable
import org.wa.usfmreader.api.RemoteUsfmRepository
import org.wa.usfmreader.api.UsfmApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData

class UsfmRepositoryImpl {
    private val localUsfmRepository = LocalUsfmRepository(UsfmCache())
    private val remoteUsfmRepository = RemoteUsfmRepository(UsfmApi())

    fun getBookUsfm(book: BookData, language: LanguageData): Observable<String> {
        return localUsfmRepository.getBookUsfm(book, language)
                .flatMap {
                    println(it)
                    if (it.isNotEmpty()) {
                        Observable.just(it)
                    } else {
                        remoteUsfmRepository.getBookUsfm(book, language)
                            .doOnNext {
                                localUsfmRepository.saveBookUsfm(book, language, it)
                            }
                    }
                }
    }
}

