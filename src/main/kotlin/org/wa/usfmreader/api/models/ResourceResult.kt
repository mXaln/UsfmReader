package org.wa.usfmreader.api.models

data class ResourceResult(
        val identifier: String,
        val projects: List<ProjectResult>
)