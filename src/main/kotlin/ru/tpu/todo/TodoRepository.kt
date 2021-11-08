package ru.tpu.todo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class TodoRepository(
    private val jdbcTemplate: JdbcTemplate
) {

    fun getTodoById(id: Int): Todo? {
        return jdbcTemplate.query(
            "SELECT * FROM todo WHERE id = ?",
            RowMapper { rs: ResultSet, rowNum ->
                return@RowMapper Todo(
                    id = rs.getInt("id"),
                    name = rs.getString("name")
                )
            },
            id
        ).firstOrNull()
    }

    fun getTodos(): List<Todo> {
        return jdbcTemplate.query(
            "SELECT * FROM todo",
            RowMapper { rs: ResultSet, rowNum ->
                return@RowMapper Todo(
                    id = rs.getInt("id"),
                    name = rs.getString("name")
                )
            }
        )
    }
}