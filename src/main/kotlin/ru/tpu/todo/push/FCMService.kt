package ru.tpu.todo.push

import com.google.firebase.messaging.*
import org.springframework.stereotype.Service

@Service
class FCMService(
    private val repository: PushTokenRepository
) {

    fun setToken(deviceId: String, token: String) {
        repository.setToken(deviceId, token)
    }

    fun sendToAllAlarmNotification() {
        val messages = repository.getActiveTokens().map { token: String ->
            buildAlarmMessage(token)
        }
        if (messages.isNotEmpty()) {
            val batchResponse: BatchResponse = FirebaseMessaging.getInstance().sendAll(messages)
            batchResponse.responses.forEach { response: SendResponse ->
                if (response.isSuccessful) {
                    println("success!")
                } else {
                    println("error ${response.exception}")
                }
            }
        }
    }

    private fun buildAlarmMessage(token: String): Message {
        return Message.builder()
            .setAndroidConfig(
                AndroidConfig.builder()
                    .putData("type", "cats_alarm")
                    .putData("title", "Время смотреть котиков!")
                    .build()
            )
            .setToken(token)
            .build()
    }
}