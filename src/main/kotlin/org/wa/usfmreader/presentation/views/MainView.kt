package org.wa.usfmreader.presentation.views

import com.github.thomasnield.rxkotlinfx.toNullableObservable
import javafx.beans.binding.Bindings
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import org.wa.usfmreader.presentation.viewmodel.MainViewModel
import org.wa.usfmreader.presentation.views.components.BottomControls
import org.wa.usfmreader.presentation.views.components.ChapterContent
import org.wa.usfmreader.presentation.views.components.TopControls
import tornadofx.*

class MainView : View("Bible Reader") {
    override val root = BorderPane()

    private val topControls: TopControls by inject()
    private val chapterContent: ChapterContent by inject()
    private val bottomControls: BottomControls by inject()

    private val viewModel: MainViewModel by inject()

    init {
        with(root) {
            setMinSize(1024.0, 768.0)

            top = topControls.root.apply {
                alignment = Pos.CENTER
                borderpaneConstraints {
                    margin = Insets(20.0, 0.0, 20.0, 0.0)
                }
            }

            center = chapterContent.root.apply {
                alignment = Pos.TOP_CENTER
                borderpaneConstraints {
                    margin = Insets(0.0, 20.0, 0.0, 20.0)
                }
            }

            bottom = bottomControls.root.apply {
                alignment = Pos.CENTER
                borderpaneConstraints {
                    margin = Insets(20.0, 0.0, 20.0, 0.0)
                }
            }
        }

        // ------------ TopControls ------------- //

        topControls.imageLoader.visibleWhen {
            Bindings.isEmpty(viewModel.languages)
        }

        topControls.languageCombobox.items = viewModel.languages
        topControls.languageCombobox.bind(viewModel.languageProperty)
        topControls.languageCombobox.selectionModel.selectedItemProperty().onChange {
            if (it != null) {
                viewModel.handleLanguageSelected(it)
            }
        }
        topControls.languageCombobox.disableWhen {
            Bindings.isEmpty(viewModel.languages)
        }
        viewModel.languageProperty.onChange {
            if (it != null) {
                topControls.bookCombobox.items = it.books.observable()
            }
        }

        topControls.bookCombobox.bind(viewModel.bookProperty, true)
        topControls.bookCombobox.selectionModel.selectedItemProperty().onChange {
            if (it != null) {
                viewModel.handleBookSelected(it)
            }
        }
        topControls.bookCombobox.disableWhen {
            Bindings.isNull(viewModel.languageProperty)
        }
        viewModel.bookProperty.onChange {
            if (it != null) {
                topControls.chapterCombobox.items = it.chapters.observable()
                topControls.chapterCombobox.selectionModel.selectFirst()
            }
        }

        topControls.chapterCombobox.bind(viewModel.chapterProperty)
        topControls.chapterCombobox.disableWhen {
            Bindings.isNull(viewModel.bookProperty)
        }

        // --------- Chapter Content --------- //
        viewModel.bookProperty.onChange {
            chapterContent.bookLabel.text = it?.name
        }
        viewModel.chapterProperty.onChange {
            chapterContent.chapterLabel.text = it?.number.toString()
        }
        chapterContent.chapterLabel.hiddenWhen {
            viewModel.bookLoadingProperty
        }
        chapterContent.imageLoader.visibleWhen {
            viewModel.bookLoadingProperty
        }
        chapterContent.chapterText.hiddenWhen {
            viewModel.bookLoadingProperty
        }
        viewModel.chapterProperty.onChange {
            chapterContent.chapterText.text = it?.text
        }

        // ---------- Bottom Controls ----------- //
        bottomControls.prevButton.disableWhen {
            Bindings.isNull(viewModel.chapterProperty)
        }.action {
            viewModel.handlePreviousChapterClick()
        }
        bottomControls.nextButton.disableWhen {
            Bindings.isNull(viewModel.chapterProperty)
        }.action {
            viewModel.handleNextChapterClick()
        }
    }
}