package ru.tpu.todo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

/**
 * Конфигурации нужны для явного задания зависимостей в IoC контейнере
 */
@Configuration
class TodoConfig {

    @Bean
    fun todoService(todoRepository: TodoRepository): TodoService {
        return InMemoryTodoService(todoRepository)
    }

    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            // В документацию включаем только наши контроллеры
            .apis(RequestHandlerSelectors.basePackage("ru.tpu"))
            .build()
    }
}