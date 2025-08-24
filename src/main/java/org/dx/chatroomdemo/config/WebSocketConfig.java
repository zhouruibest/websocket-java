package org.dx.chatroomdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import interceptor.CustomHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .addInterceptors(new CustomHandshakeInterceptor())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");

        registry.enableStompBrokerRelay("/topic/", "/queue/", "/exchange/")
                .setRelayHost("mydevcvm")
                .setRelayPort(61613)
                .setVirtualHost("/")
                .setSystemLogin("admin")
                .setSystemPasscode("admin@1991")
                .setClientLogin("admin")
                .setClientPasscode("admin@1991");
    }
}
