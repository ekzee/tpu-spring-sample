package ru.tpu.todo.push

import org.springframework.web.bind.annotation.*
import ru.tpu.todo.Response

@RestController
@RequestMapping("/push")
class PushController(
    private val fcmService: FCMService
) {
    @PostMapping("/")
    fun put(
        @RequestParam("deviceId") deviceId: String,
        @RequestParam("token") token: String
    ): Response<Unit?> {
        fcmService.setToken(deviceId, token)
        return Response(data = null)
    }

    @GetMapping("/all")
    fun sendAll(): Response<Unit?> {
        fcmService.sendToAllAlarmNotification()
        return Response(data = null)
    }
}