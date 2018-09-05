package org.wa.usfmreader.api.models

import com.squareup.moshi.Json

data class LanguageResult(
        @Json(name = "identifier") val identifier: String,
        @Json(name = "title") val title: String,
        @Json(name = "direction") val direction: String,
        @Json(name = "resources") val resources: List<RecourceResult>
)