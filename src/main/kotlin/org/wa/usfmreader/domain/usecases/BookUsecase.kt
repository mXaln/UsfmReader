package org.wa.usfmreader.domain.usecases

import io.reactivex.Maybe
import org.wa.usfmreader.data.entities.BookData
import org.wa.usfmreader.data.entities.LanguageData
import org.wa.usfmreader.domain.LocalUsfmRepository
import org.wa.usfmreader.domain.UsfmParser
import org.wa.usfmreader.domain.UsfmRepository

class BookUsecase(private var localUsfmRepository: LocalUsfmRepository,
                  private var remoteUsfmRepository: UsfmRepository) {

    fun getBookWithChapters(book: BookData, language: LanguageData): Maybe<BookData> {
        return localUsfmRepository.getBookUsfm(book, language)
                .map {
                    UsfmParser().parse(it, book)
                }
                .onErrorResumeNext { _: Throwable ->
                    remoteUsfmRepository.getBookUsfm(book, language)
                            .flatMap {
                                val usfm = it
                                localUsfmRepository.saveBookUsfm(book, language, it)
                                        .flatMap {
                                            Maybe.just(UsfmParser().parse(usfm, book))
                                        }

                            }
                }
    }
}