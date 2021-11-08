package ru.tpu.todo

import io.swagger.v3.oas.annotations.media.Schema

data class Todo(
    @Schema(
        description = "Идентификатор дела"
    )
    val id: Int,
    val name: String
)