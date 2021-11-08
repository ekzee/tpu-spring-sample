package ru.tpu.todo

import java.lang.RuntimeException

class LogicException(
    val httpCode: Int,
    message: String
) : RuntimeException(message)