package org.wa.usfmreader.api.models

import com.squareup.moshi.Json

data class FormatResult(
        @Json(name = "format") val format: String,
        @Json(name = "url") val url: String
)