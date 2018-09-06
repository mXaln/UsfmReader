package org.wa.usfmreader.api.models

data class LanguageResult(
        val identifier: String,
        val title: String,
        val direction: String,
        val resources: List<ResourceResult>
)