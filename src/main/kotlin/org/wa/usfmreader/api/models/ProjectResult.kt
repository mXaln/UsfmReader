package org.wa.usfmreader.api.models

data class ProjectResult(
        val identifier: String,
        val title: String,
        val sort: Int,
        val formats: List<FormatResult>
)