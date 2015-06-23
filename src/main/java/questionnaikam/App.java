package questionnaikam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Configuration
    @EnableWebSocketMessageBroker
    static class StompConfig extends AbstractWebSocketMessageBrokerConfigurer {

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("endpoint");
        }

        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
            registry.setApplicationDestinationPrefixes("/app");
            registry.enableSimpleBroker("/topic");
        }
    }

    @Bean
    @Order(1)
    CommandLineRunner runner(QuestionnairesRepository questionnairesRepository) {
        return (args) -> {
            Questionnaire q1 = Questionnaire.builder()
                    .title("参加回数は？")
                    .items(Arrays.asList(
                            QuestionnaireItem.builder().label("初めて").value(1L).build(),
                            QuestionnaireItem.builder().label("2回目").value(2L).build(),
                            QuestionnaireItem.builder().label("3回目").value(3L).build(),
                            QuestionnaireItem.builder().label("4回目以上").value(4L).build()
                    )).build();
            Questionnaire q2 = Questionnaire.builder()
                    .title("普段最もよく使用している言語は？")
                    .items(Arrays.asList(
                            QuestionnaireItem.builder().label("Java").value(5L).build(),
                            QuestionnaireItem.builder().label("Scala").value(3L).build(),
                            QuestionnaireItem.builder().label("Groovy").value(3L).build(),
                            QuestionnaireItem.builder().label("Kotlin").value(1L).build(),
                            QuestionnaireItem.builder().label("その他").value(3L).build()
                    )).build();
            Questionnaire q3 = Questionnaire.builder()
                    .title("test？")
                    .items(Arrays.asList(
                            QuestionnaireItem.builder().label("test1").value(0L).build(),
                            QuestionnaireItem.builder().label("test2").value(0L).build(),
                            QuestionnaireItem.builder().label("test3").value(0L).build()
                    )).build();

            Questionnaires questionnaires = Questionnaires.builder()
                    .title("hoge")
                    .values(Arrays.asList(q1, q2, q3))
                    .createdAt(new Date())
                    .expiredAt(new Date())
                    .build();
            questionnairesRepository.save(questionnaires);
        };
    }
}
