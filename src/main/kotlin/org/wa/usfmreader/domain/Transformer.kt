package org.wa.usfmreader.domain

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>