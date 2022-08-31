package com.luis.helpdesk.domain.exceptions

class ObjectNotFoundException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
}