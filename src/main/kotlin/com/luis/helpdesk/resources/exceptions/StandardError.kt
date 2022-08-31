package com.luis.helpdesk.resources.exceptions

import java.io.Serializable

class StandardError(
    val timestamp: Long? = null,
    val status: Int? = null,
    val error: String? = null,
    val message: String? = null,
    val path: String? = null,
) : Serializable {
}