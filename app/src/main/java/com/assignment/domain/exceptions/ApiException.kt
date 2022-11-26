package com.assignment.domain.exceptions

class ApiException(
    error: String?
) : Exception(error)