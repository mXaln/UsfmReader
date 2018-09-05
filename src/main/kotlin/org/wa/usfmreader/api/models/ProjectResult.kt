package org.wa.usfmreader.api.models

import com.squareup.moshi.Json

data class ProjectResult(
        @Json(name = "identifier") val identifier: String,
        @Json(name = "title") val title: String,
        @Json(name = "sort") val sort: Int,
        @Json(name = "formats") val formats: List<FormatResult>
)