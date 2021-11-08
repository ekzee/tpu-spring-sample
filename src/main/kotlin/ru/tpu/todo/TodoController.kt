package ru.tpu.todo

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер с эндпоинтами для ресурса /todo
 */
@RestController
@RequestMapping("/todo")
class TodoController(
    private val service: TodoService,
) {

    @GetMapping("/")
    @Operation(
        summary = "Вернуть список всех дел"
    )
    fun getAll(): Response<List<Todo>> {
        return Response(data = service.getAll())
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): Response<Todo> {
        return Response(data = (service.getById(id) ?: throw LogicException(404, "not_found")))
    }
}