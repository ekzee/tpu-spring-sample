package ru.tpu.todo

class Response<T>(
    val code: Int = 200,
    val data: T
)