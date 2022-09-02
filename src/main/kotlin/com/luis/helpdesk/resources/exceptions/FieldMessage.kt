package com.luis.helpdesk.resources.exceptions

import java.io.Serializable

open class FieldMessage(
    val fieldName: String?,
    val message: String?
) : Serializable {
}