package com.example.spring.integration.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public MessageChannel fooChannel() {
        return new DirectChannel();
    }

    @Bean
	@InboundChannelAdapter(value = "fooChannel", poller = @Poller(fixedDelay = "3000"))
	public MessageSource<?> fooMessageSource() {
        String selectQuery = "SELECT * FROM foo where status = 0";
        String updateSQL = "UPDATE foo SET status=10 WHERE id in (:id)";
        JdbcPollingChannelAdapter channelAdapter = new JdbcPollingChannelAdapter(dataSource, selectQuery);
        channelAdapter.setUpdateSql(updateSQL);
        return channelAdapter;
    }
    
    @Bean
    @ServiceActivator(inputChannel = "fooChannel")
    public MessageHandler handler() {
        return message -> System.out.println(message.getPayload());
    }
}