package org.wa.usfmreader.api.models

import com.squareup.moshi.Json

data class ProjectResult(
        val identifier: String,
        val title: String,
        val sort: Int,
        val formats: List<FormatResult>
)