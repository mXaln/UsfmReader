package org.wa.usfmreader.domain.usecases

import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.wa.usfmreader.api.RemoteUsfmRepository
import org.wa.usfmreader.api.UsfmApi
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.UsfmParser
import org.wa.usfmreader.persistence.LocalUsfmRepository
import org.wa.usfmreader.persistence.UsfmFile
import org.wa.usfmreader.persistence.db.AppDatabaseImpl

class BookUsecase {
    private val localUsfmRepository = LocalUsfmRepository(
            UsfmFile(),
            AppDatabaseImpl()
    )
    private val remoteUsfmRepository = RemoteUsfmRepository(UsfmApi())

    fun getBookWithChapters(book: BookData, language: LanguageData): Maybe<BookData> {
        return localUsfmRepository.getBookUsfm(book, language)
                .flatMap {
                    Maybe.fromCallable {
                        UsfmParser().parse(it, book)
                    }
                }
                .onErrorResumeNext { _: Throwable ->
                    remoteUsfmRepository.getBookUsfm(book, language)
                            .flatMap {
                                localUsfmRepository.saveBookUsfm(book, language, it)
                                        .subscribe()
                                Maybe.just(UsfmParser().parse(it, book))
                            }
                }
    }
}