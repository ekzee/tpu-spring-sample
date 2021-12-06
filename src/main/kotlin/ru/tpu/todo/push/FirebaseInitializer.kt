package ru.tpu.todo.push

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class FirebaseInitializer {
    @Value("\${app.firebase-config-file}")
    lateinit var firebaseConfigPath: String

    @PostConstruct
    fun initialize() {
        val credentials = GoogleCredentials.fromStream(
            ClassPathResource(firebaseConfigPath).inputStream
        )
        val options = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build()
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options)
        }
    }
}