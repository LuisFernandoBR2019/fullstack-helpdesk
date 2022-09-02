package com.luis.helpdesk.resources.exceptions

class ValidationError(
    timestamp: Long? = null,
    status: Int? = null,
    error: String? = null,
    message: String? = null,
    path: String? = null,
) : StandardError(timestamp, status, error, message, path) {
    val errors: MutableList<FieldMessage> = ArrayList()

    fun addError(fieldName: String, message: String) {
        this.errors.add(FieldMessage(fieldName, message))
    }
}