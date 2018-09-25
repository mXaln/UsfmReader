package org.wa.usfmreader.data.entities

data class BookData(
        var slug: String = "",
        var name: String = "",
        var sort: Int = 0,
        var usfmUrl: String = "",
        var chapters: List<ChapterData> = listOf()
)