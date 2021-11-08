package ru.tpu.todo


interface TodoService {
    fun getById(id: Int): Todo?
    fun getAll(): List<Todo>
}

class InMemoryTodoService(
    private val repository: TodoRepository
) : TodoService {
    override fun getById(id: Int): Todo? {
        return repository.getTodoById(id)
    }

    override fun getAll(): List<Todo> {
        return repository.getTodos()
    }
}