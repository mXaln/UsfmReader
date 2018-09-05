package org.wa.usfmreader.api.models

import com.squareup.moshi.Json

data class RecourceResult(
        @Json(name = "identifier") val identifier: String,
        @Json(name = "projects") val projects: List<ProjectResult>
)