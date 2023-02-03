package gamesis.mail;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import gamesis.mail.model.MailRequest;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 *
 */
@SpringBootApplication
public class GamesisMailTester {

    public static void main(String[] args) {
        SpringApplication.run(GamesisMailTester.class, args);
    }

    @Configuration
    public class JmsConfig {

        @Bean
        public DefaultJmsListenerContainerFactory jmsConnectioFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter() {
                @Override
                protected JavaType getJavaTypeForMessage(Message message) throws JMSException {
                    return TypeFactory.defaultInstance().constructType(MailRequest.class);
                }
            };
            converter.setTargetType(MessageType.TEXT);
            factory.setMessageConverter(converter);
            configurer.configure(factory, connectionFactory);
            return factory;
        }
    }
}
