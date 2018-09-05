package org.wa.usfmreader.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.wa.usfmreader.data.entities.LanguageData

data class LanguagesListResult(
        @Json(name = "languages") val languages: List<LanguageResult>
)