package ru.tpu.todo.push

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class PushTokenRepository(
    private val jdbcTemplate: JdbcTemplate
) {
    fun getActiveTokens(): List<String> {
        return jdbcTemplate.query(
            "SELECT token FROM push_token",
            RowMapper { rs: ResultSet, _ ->
                return@RowMapper rs.getString("token")
            },
        )
    }

    fun setToken(deviceId: String, token: String) {
        jdbcTemplate.update(
            "INSERT INTO push_token(deviceId, token) VALUES (?, ?) ON CONFLICT (deviceId) DO UPDATE SET token = ?",
            deviceId,
            token,
            token
        )
    }
}